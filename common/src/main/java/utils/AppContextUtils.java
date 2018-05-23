package utils;

import android.app.Activity;
import android.content.Context;

/**
 * describe：app级别的上下文
 * author ：王文彬 on 2018/5/22 11：21
 * email：wwb199055@126.com
 */
public class AppContextUtils {

  private static Context context;

  private static Activity activity;

  private AppContextUtils() {
    throw new UnsupportedOperationException("u can't instantiate me...");
  }

  /**
   * 初始化工具类
   *
   * @param context 上下文
   */
  public static void init(Context context) {
    AppContextUtils.context = context.getApplicationContext();
  }

  /**
   * 初始化工具类
   *
   * @param context 上下文
   */
  public static void init(Activity context) {
    AppContextUtils.activity = context;
  }


  /**
   * 获取ApplicationContext
   *
   * @return ApplicationContext
   */
  public static Context getContext() {
    if (context != null) {
      return context;
    } else {
      throw new NullPointerException("u should init first");
    }
  }

  /**
   * 获取ApplicationContext
   *
   * @return ApplicationContext
   */
  public static Activity getActivity() {
    if (context != null) {
      return activity;
    } else {
      throw new NullPointerException("u should init first");
    }
  }
}