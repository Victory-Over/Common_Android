package com.fanneng.common.demo.entity;


import com.fanneng.common.net.BaseResponseEntity;

public class UserInfo extends BaseResponseEntity {

  public String token;
  public String contact;

  public CusInfoBean cusInfo;

  public class CusInfoBean {
    public String cusId;
    public String cusName;
  }

}
