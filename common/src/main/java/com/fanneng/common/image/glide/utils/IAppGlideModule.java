package com.fanneng.common.image.glide.utils;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

/**
 * @author ：king9999 on 2018/6/4 13：30
 * @describe：
 * @email：wwb199055@enn.cn
 */

public class IAppGlideModule extends AppGlideModule {
  @Override
  public void applyOptions(Context context, GlideBuilder builder) {
    int memoryCacheSizeBytes = 1024 * 1024 * 20;
    builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes));
  }
}
