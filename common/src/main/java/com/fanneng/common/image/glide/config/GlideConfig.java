package com.fanneng.common.image.glide.config;

/**
 * @author ：王文彬 on 2018/5/24 17：26
 * @describe： Glide的一些基础配置
 * @email：wwb199055@126.com
 */
public class GlideConfig {

  /**
   * Glide缓存大小：20M
   */
  private static final int MEMORY_CACHE_SIZE = 1024 * 1024 * 20;


  /**
   * 图片缓存子目录名称
   */
  private static final String MEMORY_CACHE_PATH = "memory_cache_path";


  public static int getMemoryCacheSize() {
    return MEMORY_CACHE_SIZE;
  }

  public static String getMemoryCachePath() {
    return MEMORY_CACHE_PATH;
  }
}
