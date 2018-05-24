package com.fanneng.common.customview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanneng.common.R;

/**
 * @author ：王文彬 on 2018/5/22 13：52
 * @describe：进度条Dialog封装
 * @email：wwb199055@126.com
 */
public class CustomProgressDialog extends Dialog {

  private View mDialogView;
  private boolean cancelTouchOutside;
  private AnimationDrawable animationDrawable;

  public CustomProgressDialog(Builder builder) {
    super(builder.context);
    mDialogView = builder.mDialogView;
    cancelTouchOutside = builder.cancelTouchOutside;
  }

  private CustomProgressDialog(Builder builder, int themeResId) {
    super(builder.context, themeResId);
    mDialogView = builder.mDialogView;
    cancelTouchOutside = builder.cancelTouchOutside;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(mDialogView);
    setCanceledOnTouchOutside(cancelTouchOutside);
  }

  @Override
  public void onWindowFocusChanged(boolean hasFocus) {
    if (mDialogView == null) {
      return;
    }
    //添加控件  执行帧动画
    ImageView imageView = mDialogView.findViewById(R.id.iv_loading_img);
    animationDrawable = (AnimationDrawable) imageView.getBackground();
    animationDrawable.start();
  }

  @Override
  protected void onStop() {
    super.onStop();
    animationDrawable.stop();
  }

  public static final class Builder {
    Context context;
    private int resStyle = -1;
    private View mDialogView;
    private boolean cancelTouchOutside;

    public Builder(Context context) {
      this.context = context;
      mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_progress, null);
    }

    /**
     * 设置主题
     *
     * @param resStyle style id
     * @return CustomProgressDialog.Builder
     */
    public Builder setTheme(int resStyle) {
      this.resStyle = resStyle;
      return this;
    }

    public Builder setMessage(String message) {
      TextView tvMessage = mDialogView.findViewById(R.id.tv_loading_msg);
      if (tvMessage != null) {
        tvMessage.setText(message);
      }
      return this;
    }

    /**
     * 设置点击dialog外部是否取消dialog
     *
     * @param val 点击外部是否取消dialog
     */
    public Builder cancelTouchOutside(boolean val) {
      cancelTouchOutside = val;
      return this;
    }

    public CustomProgressDialog build() {
      if (resStyle != -1) {
        return new CustomProgressDialog(this, resStyle);
      } else {
        return new CustomProgressDialog(this);
      }
    }
  }
}
