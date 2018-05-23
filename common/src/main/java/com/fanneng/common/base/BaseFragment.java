package com.fanneng.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fanneng.common.utils.AppContextUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * @author ：王文彬 on 2018/5/23 12：33
 * @describe：
 * @email：wwb199055@126.com
 */
public class BaseFragment extends RxFragment {

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    RxAppCompatActivity mContext = (RxAppCompatActivity) getActivity();
    AppContextUtils.init(mContext);
  }


}
