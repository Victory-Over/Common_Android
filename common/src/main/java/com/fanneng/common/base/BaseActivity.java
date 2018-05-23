package com.fanneng.common.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fanneng.common.R;
import com.fanneng.common.utils.AppContextUtils;
import com.fanneng.common.utils.StatusBarUtil;
import com.fanneng.common.utils.ToastUtils;
import com.fanneng.common.utils.ViewManager;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;


/**
 * 继承自RxAppCompatActivity，定义了常见方法如：创建文件、初始化数据库、初始化配置信息等；
 */
public abstract class BaseActivity extends RxAppCompatActivity {
  private TextView rightBtnTv;
  private TextView midTitleTv;
  protected TextView leftBtnTv;
  private TextView midTitleRightTv;
  protected RelativeLayout titleRl;
  private View noNetWork;
  private View baseContainer;
  private LinearLayout titleBarLl;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initStatusBar();
    setContentView(getMergerView());
    ButterKnife.bind(this);
    AppContextUtils.init(this);
    ViewManager.getInstance().addActivity(this);
    initView();
  }

  /**
   * 初始化状态栏
   */
  protected void initStatusBar() {
    StatusBarUtil.setLightMode(this);
  }

  /**
   * 初始化布局
   */
  protected abstract @LayoutRes
  int initLayout();

  /**
   * 初始化ToolBar
   */
  protected boolean needHeader() {
    return true;
  }

  /**
   * 初始化控件
   */
  protected abstract void initView();


  private View getMergerView() {
    View rootView = View.inflate(this, R.layout.title_bar, null);

    leftBtnTv = rootView.findViewById(R.id.tv_left_btn);
    rightBtnTv = rootView.findViewById(R.id.tv_right_btn);
    midTitleTv = rootView.findViewById(R.id.tv_mid_title);
    midTitleRightTv = rootView.findViewById(R.id.tv_mid_title_right);
    titleRl = rootView.findViewById(R.id.rl_title);
    titleBarLl = rootView.findViewById(R.id.ll_title_bar);
    setNoNetworkView();
    baseContainer = LayoutInflater.from(this).inflate(initLayout(), null);
    titleBarLl.addView(baseContainer);

    leftBtnClickListener();
    if (isMidTitleRightTvVisible()) {
      midTitleRightTv.setVisibility(View.VISIBLE);
      setMidTitleTvListener();
    }

    if (needHeader()) {
      titleRl.setVisibility(View.VISIBLE);
    } else {
      titleRl.setVisibility(View.GONE);
    }
    return rootView;
  }

  private void setNoNetworkView() {
    noNetWork = LayoutInflater.from(this).inflate(R.layout.no_network, null);
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.MATCH_PARENT);
    noNetWork.setLayoutParams(params);
    setNoNetworkRefreshListener();
    titleBarLl.addView(noNetWork);
  }

  protected void showNoNetworkView(boolean isShow) {
    if (isShow) {
      baseContainer.setVisibility(View.GONE);
      noNetWork.setClickable(true);
      noNetWork.setVisibility(View.VISIBLE);
      ToastUtils.showToastSafe("网络开小差了！");
    } else {
      baseContainer.setVisibility(View.VISIBLE);
      noNetWork.setVisibility(View.GONE);
    }
  }

  private void setNoNetworkRefreshListener() {
    noNetWork.setOnClickListener(
        v -> {
          noNetWork.setClickable(false);
          doNoNetworkRefresh();
        });
  }

  protected void doNoNetworkRefresh() {

  }

  /**
   * 左边按钮点击事件
   */
  private void leftBtnClickListener() {
    leftBtnTv.setOnClickListener(
        v -> finish());
  }

  /**
   * 中间选择被点击
   */
  private void setMidTitleTvListener() {
    View.OnClickListener listener = v -> doSomething();
    midTitleTv.setOnClickListener(listener);
    midTitleRightTv.setOnClickListener(listener);
  }

  protected void doSomething() {

  }

  protected boolean isMidTitleRightTvVisible() {
    return false;
  }

  @Override
  public void setTitle(CharSequence title) {
    if (midTitleTv != null) {
      midTitleTv.setText(title);
    }
  }

  /**
   * 左边按钮是否显示
   */
  public void setLeftTvVisible(boolean visible) {
    if (null != leftBtnTv) {
      if (visible) {
        leftBtnTv.setVisibility(View.VISIBLE);
      } else {
        leftBtnTv.setVisibility(View.GONE);
      }
    }
  }

  /**
   * 右侧按钮是否显示
   */
  public void setRightTvVisible(boolean visible, String txt) {
    if (null != rightBtnTv) {
      if (visible) {
        rightBtnTv.setVisibility(View.VISIBLE);
        rightBtnTv.setText(txt);
      } else {
        rightBtnTv.setVisibility(View.GONE);
      }
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

  @Override
  public void onTrimMemory(int level) {
    super.onTrimMemory(level);
  }
}
