package com.fanneng.common.base;


import com.squareup.leakcanary.LeakCanary;

/**
 * @author ：刘建广 on 2018/5/23 14：00
 * @describe：app需要验证内存是否溢出的时候，AndroidManifest中的Application的类应该以此类为父类
 * @email：ljgnice@gmail.com
 */
public abstract class CommonLeakApplication extends CommonApplication {


  @Override
  public void onCreate() {
    super.onCreate();
    if (LeakCanary.isInAnalyzerProcess(this)) {
      return;
    }
    LeakCanary.install(this);
  }


}
