package com.fanneng.common.net;

import android.content.Intent;

import com.fanneng.common.utils.AppContextUtils;
import com.fanneng.common.utils.SpUtil;
import com.fanneng.common.utils.ViewManager;


/**
 * @author luyufeng on 2018/4/20.
 */

public class ApiConfig {

    public static final int DEFAULT_TIMEOUT = 5000;

    private static final String APP_TOKEN = "app_token";

    private static ApiConfig instance = null;

    public static ApiConfig getInstance() {
        if (instance == null) {
            instance = new ApiConfig();
        }
        return instance;
    }

    /**
     * url配置
     */
    private String baseUrl = "";

    public void setServerUrl(String url) {
        this.baseUrl = url;
    }


    /**
     * 获取URl接口
     */
    public String getServerUrl() {
        return baseUrl;
    }

    public void setAppToken(String token) {
        SpUtil.setString(APP_TOKEN, token);
    }

    public String getAppToken() {
        return SpUtil.getString(APP_TOKEN);
    }

    private Class<?> clazz = null;

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void gotoActivity() {
        AppContextUtils.getContext().startActivity(new Intent(AppContextUtils.getActivity(), getClazz()));
        ViewManager.getInstance().finishAllActivity();
    }
}
