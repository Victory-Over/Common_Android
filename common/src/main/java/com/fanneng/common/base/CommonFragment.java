package com.fanneng.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fanneng.common.utils.AppContextUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * @author ：鲁宇峰 on 2018/5/22 13：52
 * @describe：CommonFragment定义了常见方法如：创建文件、初始化配置信息等；所有子类应该尽量继承这个类；
 * @email：wwb199055@126.com
 */
public class CommonFragment extends RxFragment {

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    RxAppCompatActivity mContext = (RxAppCompatActivity) getActivity();
    AppContextUtils.init(mContext);
  }


}
