package com.fanneng.common.net;

import com.fanneng.common.utils.SpUtils;


/**
 * @author ：鲁宇峰 on 2018/5/22 13：52
 * @describe： BaseUrl和Token处理的地方，在子类的application{@link android.app.Application }设置setServerUrl(url)、在子类登陆成功后设置
 * setAppToken(token)
 * @email：wwb199055@126.com
 */

public class ApiConfig {

  public static final int DEFAULT_TIMEOUT = 5000;

  private static final String APP_TOKEN = "app_token";

  private static ApiConfig instance = null;

  public static ApiConfig getInstance() {
    if (instance == null) {
      instance = new ApiConfig();
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

  public void setAppToken(String token) {
    SpUtils.setString(APP_TOKEN, token);
  }

  public String getAppToken() {
    return SpUtils.getString(APP_TOKEN);
  }


}
