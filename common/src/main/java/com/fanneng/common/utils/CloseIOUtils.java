package com.fanneng.common.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * describe：Io关闭工具类
 * author ：王文彬 on 2018/5/22 11：21
 * email：wwb199055@126.com
 */
public class CloseIOUtils {

  private CloseIOUtils() {
    throw new UnsupportedOperationException("u can't instantiate me...");
  }

  /**
   * 关闭IO
   *
   * @param closeables closeable
   */
  public static void closeIO(Closeable... closeables) {
    if (closeables == null) {
      return;
    }
    for (Closeable closeable : closeables) {
      if (closeable != null) {
        try {
          closeable.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

}
