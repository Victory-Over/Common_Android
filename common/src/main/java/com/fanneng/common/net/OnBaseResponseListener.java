package com.fanneng.common.net;

/**
 * @author ：王文彬 on 2018/5/22 13：30
 * @describe：网络返回参数回调
 * @email：wwb199055@126.com
 */
public interface OnBaseResponseListener<R extends BaseResponseEntity> {

  /**
   * 成功
   *
   * @param response 成功参数
   */
  void onSuccess(R response);

  /**
   * 失败
   *
   * @param response 失败参数
   */
  default void onFailing(R response) {
  }

  /**
   * 错误
   */
  default void onError() {
  }
}
