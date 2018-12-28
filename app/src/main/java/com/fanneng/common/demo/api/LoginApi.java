package com.fanneng.common.demo.api;


import com.fanneng.common.demo.entity.UserInfo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author ：mp5a5 on 2018/9/12 20：02
 * @describe
 * @email：wwb199055@126.com
 */
public interface LoginApi {

  /**
   * 登陆接口
   */
  @POST("user/login")
  Observable<UserInfo> postLogin(@Body Map<String, Object> map);
}
