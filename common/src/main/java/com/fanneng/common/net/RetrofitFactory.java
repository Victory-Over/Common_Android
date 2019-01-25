package com.fanneng.common.net;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.fanneng.common.net.interceptor.HttpCacheInterceptor;
import com.fanneng.common.net.interceptor.HttpHeaderInterceptor;
import com.fanneng.common.net.interceptor.LoggingInterceptor;
import com.fanneng.common.utils.AppContextUtils;
import com.fanneng.common.utils.StringUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * describe：Retrofit+RxJava网络请求封装
 * author ：王文彬 on 2018/5/22 11：21
 * email：wwb199055@126.com
 */

public class RetrofitFactory {


  private final Retrofit.Builder retrofit;
  private Retrofit build;

  private RetrofitFactory() {

    // 指定缓存路径,缓存大小100Mb
    File cacheFile = new File(AppContextUtils.getContext().getCacheDir(), "HttpCache");
    Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);

    OkHttpClient httpClient = new OkHttpClient().newBuilder()
        .readTimeout(ApiConfig.getInstance().getDefaultTimeout(), TimeUnit.MILLISECONDS)
        .connectTimeout(ApiConfig.getInstance().getDefaultTimeout(), TimeUnit.MILLISECONDS)
        .addInterceptor(LoggingInterceptor.getLoggingInterceptor())
        .addInterceptor(new HttpHeaderInterceptor())
        .addNetworkInterceptor(new HttpCacheInterceptor())
        .cache(cache)
        .build();

    Gson gson = new GsonBuilder()
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .serializeNulls()
        //.excludeFieldsWithoutExposeAnnotation()
        //.enableComplexMapKeySerialization()
        .setPrettyPrinting()
        .disableHtmlEscaping()
        .create();

    retrofit = new Retrofit.Builder()
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

    if (StringUtils.isNotNull(ApiConfig.getInstance().getServerUrl())) {
      build = retrofit.baseUrl(ApiConfig.getInstance().getServerUrl()).build();
    }

  }

  private static class RetrofitFactoryHolder {
    private static final RetrofitFactory INSTANCE = new RetrofitFactory();
  }

  public static final RetrofitFactory getInstance() {
    return RetrofitFactoryHolder.INSTANCE;
  }


  /**
   * 根据Api接口类生成Api实体
   *
   * @param clazz 传入的Api接口类
   * @return Api实体类
   */
  public <T> T create(Class<T> clazz) {
    return build.create(clazz);
  }

  /**
   * 根据Api接口类生成Api实体
   *
   * @param baseUrl baseUrl
   * @param clazz   传入的Api接口类
   * @return Api实体类
   */
  public <T> T create(String baseUrl, Class<T> clazz) {
    return retrofit.baseUrl(baseUrl).build().create(clazz);
  }
}
