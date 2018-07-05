package com.fanneng.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * @author ：鲁宇峰 on 2018/6/5 13：47
 *         email：luyufengc@enn.cn
 */
public class GoPageUtil {

    private static Intent getIntent(Context context, Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(context, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        return intent;
    }

    public static void goPage(Context context, Class<?> clz) {
        goPage(context, clz, null);
    }

    public static void goPage(Activity activity, Class<?> clz, int requestCode) {
        goPage(activity, clz, null, requestCode);
    }

    public static void goPage(Context context, Class<?> clz, Bundle bundle) {
        context.startActivity(getIntent(context, clz, bundle));
    }

    public static void goPage(Activity activity, Class<?> clz, Bundle bundle,
                              int requestCode) {
        activity.startActivityForResult(getIntent(activity, clz, bundle), requestCode);
    }

    public static void goPage(Fragment fregment, Class<?> clz, Bundle bundle,
                              int requestCode) {
        fregment.startActivityForResult(getIntent(fregment.getActivity(), clz, bundle), requestCode);
    }

    public static void goPage(Fragment fregment, Class<?> clz, Bundle bundle) {
        fregment.startActivity(getIntent(fregment.getActivity(), clz, bundle));
    }

    public static void goPage(Context context, String className) {
        Intent intent = new Intent();
        intent.setClassName(context, className);
        context.startActivity(intent);
    }
}
