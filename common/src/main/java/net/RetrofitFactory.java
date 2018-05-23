package net;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.AppContextUtils;
import utils.LogUtils;
import utils.NetworkUtils;

/**
 * describe：Retrofit+RxJava网络请求封装
 * author ：王文彬 on 2018/5/22 11：21
 * email：wwb199055@126.com
 */

public class RetrofitFactory {


  private final Retrofit retrofit;

  private RetrofitFactory() {
    //日志拦截器
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> {
      try {
        String text = URLDecoder.decode(message, "utf-8");
        LogUtils.e("OKHttp-----", text);
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
        LogUtils.e("OKHttp-----", message);
      }
    });

    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    // 指定缓存路径,缓存大小100Mb
    File cacheFile = new File(AppContextUtils.getContext().getCacheDir(), "HttpCache");
    Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);

    OkHttpClient httpClient = new OkHttpClient().newBuilder()
        .readTimeout(BaseAppConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(BaseAppConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .addInterceptor(new LoginInterceptor())
        .addNetworkInterceptor(new HttpCacheInterceptor())
        .cache(cache)
        .build();

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();

    retrofit = new Retrofit.Builder()
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BaseAppConfig.getInstance().getServerUrl())
        .build();

  }

  private class LoginInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

      Request originalRequest = chain.request();
      String cacheControl = originalRequest.cacheControl().toString();
      Request authorised = originalRequest.newBuilder()
          .header("Content-type", "application/json")
          .header("Cache-Control", cacheControl)
          .addHeader("token", BaseAppConfig.getInstance().getAppToken())
          .removeHeader("Pragma").build();
      return chain.proceed(authorised);
    }
  }


  //  创建单例
  private static class RetrofitFactoryHolder {
    private static final RetrofitFactory INSTANCE = new RetrofitFactory();
  }

  public static final RetrofitFactory getInstance() {
    return RetrofitFactoryHolder.INSTANCE;
  }

  private class HttpCacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

      Request request = chain.request();
      //没网强制从缓存读取
      if (!NetworkUtils.isConnected()) {
        request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
        LogUtils.d("Okhttp", "no network");
      }

      Response originalResponse = chain.proceed(request);
      if (NetworkUtils.isConnected()) {
        //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
        String cacheControl = request.cacheControl().toString();
        return originalResponse.newBuilder()
            .header("Content-type", "application/json")
            .header("Cache-Control", cacheControl)
            .addHeader("token", BaseAppConfig.getInstance().getAppToken())
            .removeHeader("Pragma")
            .build();
      } else {
        return originalResponse.newBuilder()
            .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
            .removeHeader("Pragma").build();
      }
    }
  }

  /**
   * 根据Api接口类生成Api实体
   *
   * @param clazz 传入的Api接口类
   * @return Api实体类
   */
  public <T> T create(Class<T> clazz) {
    return retrofit.create(clazz);
  }
}
