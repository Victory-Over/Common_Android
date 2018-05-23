package com.fanneng.common.net.interceptor;

import com.fanneng.common.net.ApiConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author ：王文彬 on 2018/5/23 13：31
 * @describe：
 * @email：wwb199055@126.com
 */
public class HttpHeaderInterceptor implements Interceptor {

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request originalRequest = chain.request();
    Request authorised = originalRequest.newBuilder()
        .header("Content-type", "application/json")
        .addHeader("token", ApiConfig.getInstance().getAppToken())
        .removeHeader("Pragma").build();
    return chain.proceed(authorised);
  }
}
