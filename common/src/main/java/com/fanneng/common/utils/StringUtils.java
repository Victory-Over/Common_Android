package com.fanneng.common.utils;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 操作字符串
 */
public class StringUtils {

  private StringUtils() {
    throw new UnsupportedOperationException("u can't instantiate me...");
  }

  /**
   * 字符串拼接,线程安全
   */
  public static String buffer(String... array) {
    StringBuffer s = new StringBuffer();
    for (String str : array) {
      s.append(str);
    }
    return s.toString();
  }

  /**
   * 字符串拼接,线程不安全,效率高
   */
  public static String builder(String... array) {
    StringBuilder s = new StringBuilder();
    for (String str : array) {
      s.append(str);
    }
    return s.toString();
  }

  /**
   * 判断字符串是否为null或长度为0
   *
   * @param s 待校验字符串
   * @return {@code true}: 空<br> {@code false}: 不为空
   */
  public static boolean isEmpty(String s) {
    return s == null || s.trim().length() == 0;
  }


  /**
   * 判断字符串是否为空
   *
   * @return true：不为空， false：为空
   */
  public static boolean isNotNull(String str) {
    return str != null && !"".equals(str) && !"null".equals(str) && str.trim().length() > 0;
  }


  public static String getEditTextString(EditText mEditext) {
    return mEditext.getText().toString().trim();
  }

  public static String getString(TextView tv) {
    return tv.getText().toString().trim();
  }


  public static boolean isEmpty(TextView tv) {
    return TextUtils.isEmpty(getString(tv));
  }

  private static long lastClickTime = 0;

  /**
   * 返回true 可以点击
   */
  public static boolean isFastDoubleClick() {
    long time = System.currentTimeMillis();
    long endTime = time - lastClickTime;
    lastClickTime = time;
    return endTime > 2000;
  }
}
