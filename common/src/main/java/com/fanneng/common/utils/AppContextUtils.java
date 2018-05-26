package com.fanneng.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import com.fanneng.common.base.CommonActivity;

/**
 * describe：app的上下文
 * author ：王文彬 on 2018/5/22 11：21
 * email：wwb199055@126.com
 */
public class AppContextUtils {

  @SuppressLint("StaticFieldLeak")
  private static Context context = null;

  @SuppressLint("StaticFieldLeak")
  private static CommonActivity activity = null;

  private AppContextUtils() {
    throw new UnsupportedOperationException("u can't instantiate me...");
  }

  /**
   * 在Application中进行初始化
   *
   * @param context 上下文
   */
  public static void init(Context context) {
    AppContextUtils.context = context.getApplicationContext();
  }

  /**
   * 在CommonActivity中进行初始化
   *
   * @param act 上下文
   */
  public static void init(CommonActivity act) {
    AppContextUtils.activity = act;
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
   * 获取Activity
   *
   * @return Activity
   */
  public static CommonActivity getActivity() {
    if (context != null) {
      return activity;
    } else {
      throw new NullPointerException("u should init first");
    }
  }
}