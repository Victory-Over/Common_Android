package com.fanneng.common.net.interceptor;

import com.fanneng.common.utils.LogUtils;
import com.fanneng.common.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author ：王文彬 on 2018/5/23 13：34
 * @describe：配置缓存的拦截器
 * @email：wwb199055@126.com
 */
public class HttpCacheInterceptor implements Interceptor {
  @Override
  public Response intercept(Chain chain) throws IOException {
    Request request = chain.request();
    //没网强制从缓存读取
    if (!NetworkUtils.isConnected()) {
      request = request.newBuilder()
          .cacheControl(CacheControl.FORCE_CACHE)
          .build();
      LogUtils.e("Okhttp", "no network");
    }

    Response originalResponse = chain.proceed(request);
    if (NetworkUtils.isConnected()) {
      //有网的时候读接口上的@Headers里的配置
      String cacheControl = request.cacheControl().toString();

      return originalResponse.newBuilder()
          .header("Cache-Control", cacheControl)
          .removeHeader("Pragma")
          .build();
    } else {
      return originalResponse.newBuilder()
          .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
          .removeHeader("Pragma")
          .build();
    }
  }
}
