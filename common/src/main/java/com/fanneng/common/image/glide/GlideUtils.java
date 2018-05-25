package com.fanneng.common.image.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * @author ：王文彬 on 2018/5/24 17：10
 * @describe： 图片显示工具类，是基于{@link com.bumptech.glide.Glide 4}版本进行的封装
 * @email：wwb199055@126.com
 */
public class GlideUtils {


  /**
   * @param context  上下文
   * @param errorImg 错误和占位符图片
   * @param url      图片地址
   * @param imgView  需要显示的图片
   */
  public static void show(Context context, int errorImg, String url,
                          ImageView imgView) {
    GlideApp.with(context)
        .load(url)
        .placeholder(errorImg)
        .error(errorImg)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .into(imgView);
  }
}
