package com.fanneng.common.demo;

import com.fanneng.common.base.CommonApplication;

/**
 * @author ：king9999 on 2018/7/11 10：10
 * @describe
 * @email：wangwenbinc@enn.cn
 */
public class app extends CommonApplication {
  @Override
  public String setBaseUrl() {
    return "http://energy-consumer-app-fnw-dev.topaas.enncloud.cn/";
  }

  @Override
  public void onCreate() {
    super.onCreate();

  }
}
