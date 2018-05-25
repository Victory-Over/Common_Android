package com.fanneng.common.image.glide.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.fanneng.common.image.glide.config.GlideCircleTransform;
import com.fanneng.common.image.glide.config.RoundedRectanglesTransformation;

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
  @SuppressLint("CheckResult")
  public static void show(Context context, int errorImg, String url, ImageView imgView) {

    RequestOptions options = new RequestOptions();
    options.centerCrop()
        .placeholder(errorImg)
        .error(errorImg)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

    Glide.with(context)
        .load(url)
        .apply(options)
        .into(imgView);
  }

  /**
   * @param context  上下文
   * @param errorImg 错误和占位符图片
   * @param path     图片地址(drawable、mipmap下的图片)
   * @param imgView  需要显示的图片
   */
  @SuppressLint("CheckResult")
  public static void show(Context context, int errorImg, int path, ImageView imgView) {

    RequestOptions options = new RequestOptions();
    options.centerCrop()
        .placeholder(errorImg)
        .error(errorImg)
        .centerCrop()
        .skipMemoryCache(true);

    Glide.with(context)
        .load(path)
        .apply(options)
        .into(imgView);
  }


  /**
   * 圆角
   *
   * @param context  上下文
   * @param errorImg 错误的资源图片
   * @param url      图片链接
   * @param imgView  组件
   */
  @SuppressLint("CheckResult")
  public static void showCircle(Application context, int errorImg, String url, ImageView imgView) {

    RequestOptions options = new RequestOptions();
    options.centerCrop()
        .placeholder(errorImg)
        .error(errorImg)
        .transform(new GlideCircleTransform())
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE);


    Glide.with(context).load(url)
        .apply(options)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imgView);


  }

  /**
   * 圆角
   *
   * @param context  上下文
   * @param errorImg 错误的资源图片
   * @param path     图片地址
   * @param imgView  组件
   */
  @SuppressLint("CheckResult")
  public static void showCircle(Application context, int errorImg, int path, ImageView imgView) {

    RequestOptions options = new RequestOptions();
    options.centerCrop()
        .placeholder(errorImg)
        .error(errorImg)
        .transform(new GlideCircleTransform())
        .centerCrop();


    Glide.with(context).load(path)
        .apply(options)
        .into(imgView);


  }


  /**
   * 矩形圆角
   *
   * @param context  上下文
   * @param errorImg 错误的资源图片
   * @param url      图片地址
   * @param imgView  组件
   */
  @SuppressLint("CheckResult")
  public static void showImageViewToRoundedCorners(Application context, int errorImg,
                                                   String url, ImageView imgView) {


    RequestOptions options = new RequestOptions();
    options.centerCrop()
        .placeholder(errorImg)
        .error(errorImg)
        .transform(new RoundedRectanglesTransformation(35, 0, RoundedRectanglesTransformation.CornerType.ALL))
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

    Glide.with(context).load(url)
        .apply(options)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imgView);
  }

}
