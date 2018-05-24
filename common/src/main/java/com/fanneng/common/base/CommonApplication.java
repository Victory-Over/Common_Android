package com.fanneng.common.base;

import android.app.Application;

import com.fanneng.common.utils.CrashHandlerUtils;
import com.fanneng.common.net.ApiConfig;
import com.fanneng.common.utils.AppContextUtils;

/**
 * @author ：王文彬 on 2018/5/22 14：35
 * @describe：CommonApplication，APP启动时候做的操作，AndroidManifest中的Application的类都应该以此类为父类
 * @email：wwb199055@126.com
 */
public abstract class CommonApplication extends Application {


  @Override
  public void onCreate() {
    super.onCreate();
    AppContextUtils.init(this);
    CrashHandlerUtils.getInstance().init(this);
    ApiConfig.getInstance().setServerUrl(setBaseUrl());
  }


  /**
   * 在子类初始化application的时候，必须重写该防范然后获取到BaseUrl
   *
   * @return 返回app的BaseUrl
   */
  public abstract String setBaseUrl();
}
