package com.fanneng.common.image.glide;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

/**
 * @author ：王文彬 on 2018/5/24 17：07
 * @describe：
 * @email：wwb199055@126.com
 */

public class GlideCacheConfiguration extends AppGlideModule {
  @Override
  public void applyOptions(Context context, GlideBuilder builder) {

    builder.setMemoryCache(new LruResourceCache(GlideConfig.getMemoryCacheSize()));

    builder.setDiskCache(new InternalCacheDiskCacheFactory(context, GlideConfig.getMemoryCachePath(), GlideConfig
        .getMemoryCacheSize()));
  }
}
