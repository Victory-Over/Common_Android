package com.fanneng.common.base;

import android.app.Application;
import android.os.Handler;
import android.os.Process;

import com.fanneng.common.net.ApiConfig;
import com.fanneng.common.utils.AppContextUtils;

/**
 * @author ：王文彬 on 2018/5/22 14：35
 * @describe：
 * @email：wwb199055@126.com
 */
public abstract class BaseApplication extends Application {

  private static BaseApplication application = null;
  private static Handler mMainThreadHandler;
  private static int mMainThreadId = -1;

  @Override
  public void onCreate() {
    super.onCreate();
    application = this;
    AppContextUtils.init(this);
    mMainThreadId = Process.myPid();
    mMainThreadHandler = new Handler();
    ApiConfig.getInstance().setServerUrl(setBaseUrl());
  }

  public static BaseApplication getApplication() {
    return application;
  }


  public static Handler getMainThreadHandler() {
    return mMainThreadHandler;
  }

  public static int getMainThreadId() {
    return mMainThreadId;
  }

  public abstract String setBaseUrl();
}
