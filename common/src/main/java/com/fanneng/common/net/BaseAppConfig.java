package com.fanneng.common.net;

import android.content.Intent;



/**
 * @author luyufeng on 2018/4/20.
 */

public class BaseAppConfig {

  public static final int DEFAULT_TIMEOUT = 5000;

  private static final String APP_TOKEN = "";

  private static BaseAppConfig instance = null;

  public static BaseAppConfig getInstance() {
    if (instance == null) {
      instance = new BaseAppConfig();
    }
    return instance;
  }

  /**
   * url配置
   */
  private String baseUrl = "";

  public void setServerUrl(String url) {
    this.baseUrl = url;
  }


  /**
   * 获取URl接口
   */
  public String getServerUrl() {
    return baseUrl;
  }

  public String getAppToken() {
    return SpUtil.getString(APP_TOKEN);
  }

  private Class<?> clazz = null;

  public Class<?> getClazz() {
    return clazz;
  }

  public void setClazz(Class<?> clazz) {
    this.clazz = clazz;
  }

  public void gotoActivity( boolean isFinish) {
    AppContextUtils.getContext().startActivity(new Intent(AppContextUtils.getActivity(), getClazz()));
    if (isFinish) {
      AppContextUtils.getActivity().finish();
    }
  }
}
