package com.fanneng.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 全局捕获异常
 * 当程序发生Uncaught异常的时候,有该类来接管程序,并记录错误日志
 *
 * 使用:
 * 在Application中初始化
 * CrashHandler.getInstance().init(this);
 *
 * @author ms
 */
public class CrashHandlerUtils implements UncaughtExceptionHandler {

    public static String TAG = "CrashHandler";
    private static final String FILE_FORMAT = "yyyy-MM-dd_HH-mm-ss-sss";

    private UncaughtExceptionHandler mDefaultHandler;

    private static final CrashHandlerUtils INSTANCE = new CrashHandlerUtils();
    private Context mContext;
    private FinishCallback finishCallback;

    private int autoClearDay = 5;

    /**
     * 用来存储设备信息和异常信息
     * */
    private Map<String, String> infos = new HashMap<>();

    /** 保证只有一个CrashHandler实例 */
    private CrashHandlerUtils() {
    }

    /**
     * 程序崩溃 退出时的回调 做一些后续操作
     * */
    public void setFinishCallback(FinishCallback finishCallback) {
        this.finishCallback = finishCallback;
    }

    /** 获取CrashHandler实例 ,单例模式 */
    public static CrashHandlerUtils getInstance() {
        return INSTANCE;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 设置日志保留的天数
     * @param autoClearDay =0 不删除
     * */
    public void setDeleteFileDate(int autoClearDay) {
        this.autoClearDay = autoClearDay;
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            SystemClock.sleep(1000);
            // 退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息; 否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }

        try {
            autoClear();
            // 收集设备参数信息
            collectDeviceInfo(mContext);
            // 打印日志
            Log.e("ERR", Log.getStackTraceString(ex));
            // 保存日志文件
            saveCrashInfoFile(ex);
            SystemClock.sleep(1000);

            if(finishCallback != null) {
                finishCallback.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    private void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName + "";
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }

    /**
     * 保存错误信息到文件中
     * @param ex
     */
    private void saveCrashInfoFile(Throwable ex) throws Exception {
        StringBuilder sb = new StringBuilder();
        try {
            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss sss", Locale.CHINA);
            String date = sDateFormat.format(new Date());
            sb.append("\r\n");
            sb.append(date);
            sb.append("\n");
            for (Map.Entry<String, String> entry : infos.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                sb.append(key);
                sb.append("=");
                sb.append(value);
                sb.append("\n");
            }

            String result = getStackTrace(ex.getCause());
            sb.append(result);

            writeFile(sb.toString());
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
            sb.append("an error occured while writing file...\r\n");
            writeFile(sb.toString());
        }
    }

    /**
     * 获取错误日志
     * */
    private String getStackTrace(Throwable ex){
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        printWriter.flush();
        printWriter.close();
        return writer.toString();
    }

    private void writeFile(String sb) throws Exception {
        DateFormat formatter = new SimpleDateFormat(FILE_FORMAT, Locale.CHINA);
        String time = formatter.format(new Date());
        String fileName = "crash-" + time + ".log";
        if (FileUtils.hasSdcard()) {
            String path = getGlobalpath();
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            FileOutputStream fos = new FileOutputStream(path + fileName, true);
            fos.write(sb.getBytes());
            fos.flush();
            fos.close();
        }
    }

    /**
     * 获取目录 基于包名
     *
     * @return 返回目录绝对路径
     * */
    private String getGlobalpath() {
        PackageManager pm = mContext.getPackageManager();
        String appName = mContext.getApplicationInfo().loadLabel(pm).toString();
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + appName + File.separator + "crash" + File.separator;
    }

    /**
     * 文件删除
     */
    private void autoClear() {
        if(autoClearDay != 0) {
            FileUtils.delete(getGlobalpath(), (File file, String filename) -> {
                DateFormat formatter = new SimpleDateFormat(FILE_FORMAT, Locale.CHINA);
                String time = formatter.format(DateUtils.addDay(new Date(), -Math.abs(autoClearDay)));
                String date = "crash-" + time + ".log";
                return date.compareTo(filename) > 0;
            });
        }
    }

    public interface FinishCallback {
        public void finish();
    }
}
