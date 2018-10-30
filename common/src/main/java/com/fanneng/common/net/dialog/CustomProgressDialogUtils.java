package com.fanneng.common.net.dialog;

import android.content.Context;

import com.fanneng.common.R;


/**
 * @author ：王文彬 on 2018/5/22 13：52
 * @describe：进度条Dialog封装成工具类
 * @email：wwb199055@126.com
 */

public class CustomProgressDialogUtils {


  private CustomLoadingDialog mProgressDialog;

  /**
   * 显示ProgressDialog
   */
  public void showProgress(Context context, String msg) {
    if (mProgressDialog == null) {
      mProgressDialog = new CustomLoadingDialog.Builder(context)
          .setTheme(R.style.LoadingDialogStyle)
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
      mProgressDialog = new CustomLoadingDialog.Builder(context)
          .setTheme(R.style.LoadingDialogStyle)
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
      mProgressDialog=null;
    }
  }
}
