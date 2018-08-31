package com.fanneng.common.net;

import java.io.Serializable;

/**
 * @author ：王文彬 on 2018/5/22 13：37
 * @describe：网络请求返回值
 * @email：wwb199055@126.com
 */
public class BaseResponseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  private int code;

  private String msg;


  public boolean isSuccess() {
    return 200 == code;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public int getTokenInvalid() {
    return 401;
  }

  public String getLoginFailure() {
    return "406";
  }
}
