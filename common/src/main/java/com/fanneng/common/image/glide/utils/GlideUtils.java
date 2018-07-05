package com.fanneng.common.image.glide.utils;

/**
 * @author ：王文彬 on 2018/5/24 17：10
 * @describe： 图片显示工具类，是基于{@link com.bumptech.glide.Glide 4}版本进行的封装
 * @email：wwb199055@126.com
 */
public class GlideUtils {


  /**
   * @param errorImg 错误和占位符图片
   * @param url      图片地址
   * @param imgView  需要显示的图片
   *//*
  @SuppressLint("CheckResult")
  public static void show(int errorImg, String url, ImageView imgView) {

*//*    RequestOptions options = new RequestOptions();
    options.placeholder(errorImg)
        .error(errorImg)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

    Glide.with(AppContextUtils.getActivity())
        .load(url)
        .apply(options)
        .into(imgView);*//*

    GlideApp.with(AppContextUtils.getActivity())
        .load(url)
        .placeholder(errorImg)
        .error(errorImg)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .dontAnimate()
        .centerCrop()
        .into(imgView);
  }

  *//**
   * @param errorImg 错误和占位符图片
   * @param path     图片地址(drawable、mipmap下的图片)
   * @param imgView  需要显示的图片
   *//*
  @SuppressLint("CheckResult")
  public static void show(int errorImg, int path, ImageView imgView) {


    GlideApp.with(AppContextUtils.getActivity())
        .load(path)
        .placeholder(errorImg)
        .error(errorImg)
        .skipMemoryCache(true)
        .dontAnimate()
        .centerCrop()
        .into(imgView);

  }


  *//**
   * 圆角
   *
   * @param errorImg 错误的资源图片
   * @param url      图片链接
   * @param imgView  组件
   *//*
  @SuppressLint("CheckResult")
  public static void showCircle(int errorImg, String url, ImageView imgView) {


    GlideApp.with(AppContextUtils.getActivity())
        .load(url)
        .placeholder(errorImg)
        .error(errorImg)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .transition(DrawableTransitionOptions.withCrossFade())
        .dontAnimate()
        .centerCrop()
        .into(imgView);

  }

  *//**
   * 圆角
   *
   * @param errorImg 错误的资源图片
   * @param path     图片地址
   * @param imgView  组件
   *//*
  @SuppressLint("CheckResult")
  public static void showCircle(int errorImg, int path, ImageView imgView) {


    GlideApp.with(AppContextUtils.getActivity())
        .load(path)
        .placeholder(errorImg)
        .error(errorImg)
        .transform(new GlideCircleTransform())
        .dontAnimate()
        .centerCrop()
        .into(imgView);


  }


  *//**
   * 矩形圆角
   *
   * @param errorImg 错误的资源图片
   * @param url      图片地址
   * @param imgView  组件
   *//*
  @SuppressLint("CheckResult")
  public static void showImageViewToRoundedCorners(int errorImg, String url, ImageView imgView) {

    GlideApp.with(AppContextUtils.getActivity())
        .load(url)
        .placeholder(errorImg)
        .error(errorImg)
        .transform(new RoundedRectanglesTransformation(35, 0, RoundedRectanglesTransformation.CornerType.ALL))
        .transition(DrawableTransitionOptions.withCrossFade())
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .dontAnimate()
        .centerCrop()
        .into(imgView);
  }*/

}
