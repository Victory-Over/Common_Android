package com.fanneng.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import com.fanneng.common.base.BaseApplication;
import io.reactivex.annotations.NonNull;


/**
 * @author ：王文彬 on 2018/5/22 14：35
 * @describe：ToastUtils工具类
 * @email：wwb199055@126.com
 */
public class ToastUtils {

  @SuppressLint("StaticFieldLeak")
  private static Context context = AppContextUtils.getContext();

  private static Toast toast;


  /**
   * 对toast的封装。线程安全，可以在非UI线程调用。
   * Toast内容，支持String、资源ID（int）、CharSequence
   *
   * @param obj String、int、CharSequence
   */
  public static void showToastSafe(@NonNull Object obj) {
    if (isRunInMainThread()) {
      show(obj);
    } else {
      post(() -> show(obj));
    }
  }

  /**
   * 对toast的简易封装。线程安全，可以在非UI线程调用。
   * Toast内容，支持String、资源ID（int）、CharSequence
   *
   * @param obj      String、int、CharSequence
   * @param duration Toast时间。eg:Toast.LENGTH_SHORT
   */
  public static void showToastSafe(@NonNull Object obj, int duration) {
    if (isRunInMainThread()) {
      show(obj, duration);
    } else {
      post(() -> show(obj, duration));
    }
  }

  /**
   * 对toast的简易封装。线程安全，可以在非UI线程调用。
   * Toast内容，支持String、资源ID（int）、CharSequence
   *
   * @param obj      String、int、CharSequence
   * @param duration Toast时间。eg:Toast.LENGTH_SHORT
   * @param args     任意类型参数
   */
  public static void showToastSafe(@NonNull Object obj, int duration, Object... args) {
    if (isRunInMainThread()) {
      show(obj, duration, args);
    } else {
      post(() -> show(obj, duration, args));
    }
  }


  /**
   * 对toast的简易封装。线程安全，可以在非UI线程调用。
   * Toast内容，支持String、资源ID（int）、CharSequence
   *
   * @param obj  String、int、CharSequence
   * @param args 任意类型参数
   */
  public static void showToastSafe(@NonNull Object obj, Object... args) {
    if (isRunInMainThread()) {
      show(obj, args);
    } else {
      post(() -> show(obj, args));
    }
  }


  private static void show(Object obj) {
    show(obj, Toast.LENGTH_SHORT);
  }

  private static void show(Object obj, int duration) {
    if (obj instanceof Integer) {
      show(context.getResources().getText((Integer) obj), duration);
    } else if (obj instanceof CharSequence) {
      show(obj.toString(), duration);
    }
  }


  private static void show(Object obj, Object... args) {
    if (obj instanceof Integer) {
      show(String.format(context.getResources().getString((Integer) obj), args),
          Toast.LENGTH_SHORT);
    } else if (obj instanceof CharSequence) {
      show(String.format(obj.toString(), args), Toast.LENGTH_SHORT);
    }
  }

  private static void show(Object obj, int duration, Object... args) {
    if (obj instanceof Integer) {
      show(String.format(context.getResources().getString((Integer) obj), args),
          duration);
    } else if (obj instanceof CharSequence) {
      show(String.format(obj.toString(), args), duration);
    }
  }


  @SuppressLint("ShowToast")
  private static void show(CharSequence text, int duration) {

    if (toast == null) {
      toast = Toast.makeText(context, text, duration);
    } else {
      toast.setText(text);
    }
    toast.show();
  }


  /**
   * 在主线程执行runnable
   */
  private static void post(Runnable runnable) {
    BaseApplication.getMainThreadHandler().post(runnable);
  }

  //判断当前的线程是不是在主线程
  private static boolean isRunInMainThread() {
    return android.os.Process.myTid() == BaseApplication.getMainThreadId();
  }

}
