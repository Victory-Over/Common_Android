package com.fanneng.common.demo.api;

import com.fanneng.common.demo.entity.UserInfo;
import com.fanneng.common.net.RetrofitFactory;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * @author ：mp5a5 on 2018/9/12 19：53
 * @describe
 * @email：wwb199055@126.com
 */
public class LoginService {

  private final LoginApi mLoginApi;
  private final LoginApi mLoginApi1;

  private LoginService() {

    mLoginApi= RetrofitFactory.getInstance().create("http://www.baidu.com/",LoginApi.class);
    mLoginApi1= RetrofitFactory.getInstance().create(LoginApi.class);
  }

  public static LoginService getInstance() {
    return LoginServiceHolder.S_INSTANCE;
  }

  private static class LoginServiceHolder {
    private static final LoginService S_INSTANCE = new LoginService();
  }


  //登陆接口
  public Observable<UserInfo> postLogin(String username, String code) {
    Map<String, Object> map = new HashMap<>(4);
    map.put("username", username);
    map.put("password", code);
    map.put("equipType", "android");
    return mLoginApi.postLogin(map);
  }

  //登陆接口
  public Observable<UserInfo> postLogin1(String username, String code) {
    Map<String, Object> map = new HashMap<>(4);
    map.put("username", username);
    map.put("password", code);
    map.put("equipType", "android");
    return mLoginApi1.postLogin(map);
  }
}
