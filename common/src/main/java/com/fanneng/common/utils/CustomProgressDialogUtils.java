package com.fanneng.common.utils;

import android.content.Context;

import com.fanneng.common.R;
import com.fanneng.common.customview.CustomProgressDialog;


/**
 * @author ：王文彬 on 2018/5/22 13：52
 * @describe：进度条Dialog封装成工具类
 * @email：wwb199055@126.com
 */

public class CustomProgressDialogUtils {


  private CustomProgressDialog mProgressDialog;

  /**
   * 显示ProgressDialog
   */
  public void showProgress(Context context, String msg) {
    if (mProgressDialog == null) {
      mProgressDialog = new CustomProgressDialog.Builder(context)
          .setTheme(R.style.ProgressDialogStyle)
          .setMessage(msg)
          .build();
    }
    if (!mProgressDialog.isShowing()) {
      mProgressDialog.show();
    }
  }

  /**
   * 显示ProgressDialog
   */
  public void showProgress(Context context) {
    if (mProgressDialog == null) {
      mProgressDialog = new CustomProgressDialog.Builder(context)
          .setTheme(R.style.ProgressDialogStyle)
          .build();
    }
    if (!mProgressDialog.isShowing()) {
      mProgressDialog.show();
    }
  }

  /**
   * 取消ProgressDialog
   */
  public void dismissProgress() {
    if (mProgressDialog != null && mProgressDialog.isShowing()) {
      mProgressDialog.dismiss();
      mProgressDialog.cancel();
    }
  }
}
