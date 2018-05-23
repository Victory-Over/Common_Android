package com.fanneng.common.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtils {
    /**
     * 动态权限请求
     *
     * @param context Activity
     * @param permission 权限数组
     * */
    public static void requestPermissions(Activity context, String permission[]) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> list = new ArrayList<>();
            for (String per : permission) {
                if (ContextCompat.checkSelfPermission(context, per) != PackageManager.PERMISSION_GRANTED) {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(context, per)) {
                        list.add(per);
                    }
                }
            }

            if(list.size() > 0) {
                String[] strings = new String[list.size()];
                list.toArray(strings);
                ActivityCompat.requestPermissions(context, strings, 1);
            }
        }
    }
}
