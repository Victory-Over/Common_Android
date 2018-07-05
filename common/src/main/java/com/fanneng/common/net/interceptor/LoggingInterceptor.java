package com.fanneng.common.net.interceptor;

import com.fanneng.common.utils.LogUtils;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author ：王文彬 on 2018/5/23 13：24
 * @describe：
 * @email：wwb199055@126.com
 */
public class LoggingInterceptor {

    public static HttpLoggingInterceptor getLoggingInterceptor() {
        //日志拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> {
            LogUtils.e("OKHttp-----", message.toString());
        });

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return interceptor;
    }

}
