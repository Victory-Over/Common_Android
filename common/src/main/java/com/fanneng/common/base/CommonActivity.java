package com.fanneng.common.base;

import android.os.Bundle;

import com.fanneng.common.utils.AppContextUtils;
import com.fanneng.common.utils.StatusBarUtils;
import com.fanneng.common.utils.ViewManagerUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


/**
 * @author ：鲁宇峰 on 2018/5/22 13：52
 * @describe：CommonActivity，定义了常见方法如：创建文件、初始化配置信息等；所有子类应该尽量继承这个类；
 * @email：wwb199055@126.com
 */
public class CommonActivity extends RxAppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initStatusBar();
    AppContextUtils.init(this);
    ViewManagerUtils.getInstance().addActivity(this);
    EventBus.getDefault().register(this);
  }

  /**
   * 初始化状态栏
   */
  protected void initStatusBar() {
    StatusBarUtils.setLightMode(this);
  }

  @Subscribe
  public void onEvent(String event){
  }
  @Override
  protected void onDestroy() {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
    EventBus.getDefault().removeAllStickyEvents();
  }
}
