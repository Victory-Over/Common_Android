package com.fanneng.common.image.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fanneng.common.image.GlideApp;

/**
 * @author ：王文彬 on 2018/5/24 17：10
 * @describe：
 * @email：wwb199055@126.com
 */
public class GlideUtils {


  public static void showImageView(Context context, int errorImg, String url,
                                   ImageView imgView) {
    GlideApp.with(context)
        .load(url)
        .placeholder(errorImg)
        .error(errorImg)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .into(imgView);

   /* GlideApp.with(context)
        .load(SpUtil.getString(ConstantUtils.IMGURL))
        .placeholder(R.mipmap.user_head_icon)
        .error(R.mipmap.user_head_icon)
        .dontAnimate()
        .centerCrop()
        .into(userHeadIv);
    Glide.with(context).load(url)// 加载图片
        .error(errorimg)// 设置错误图片
        .crossFade()// 设置淡入淡出效果，默认300ms，可以传参
        .placeholder(errorimg)// 设置占位图
        .diskCacheStrategy(DiskCacheStrategy.RESULT)// 缓存修改过的图片
        .into(imgeview);*/
    // Glide.with(context).load(url).thumbnail(0.1f).error(errorimg)
    // .into(imgeview);

    // Glide
    // .with(context)
    // .load(UsageExampleListViewAdapter.eatFoodyImages[0])
    // .placeholder(R.mipmap.ic_launcher) //设置占位图
    // .error(R.mipmap.future_studio_launcher) //设置错误图片
    // .crossFade() //设置淡入淡出效果，默认300ms，可以传参
    // //.dontAnimate() //不显示动画效果
    // .into(imageViewFade);

  }
}
