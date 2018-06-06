package com.fanneng.common.image.glide.config;

/**
 * @author ：王文彬 on 2018/5/25 17：35
 * @describe： 圆角图片处理
 * @email：wwb199055@126.com
 */
public class GlideCircleTransform /*extends CenterCrop*/ {

  /*private float radius = 0f;

  public GlideCircleTransform() {
    this(4);
  }

  public GlideCircleTransform(int dp) {
    super();
    this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
  }

  @Override
  protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
    Bitmap transform = super.transform(pool, toTransform, outWidth, outHeight);
    return roundCrop(pool, transform);
  }

  private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
    if (source == null) {
      return null;
    }

    Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
    if (result == null) {
      result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
    }

    Canvas canvas = new Canvas(result);
    Paint paint = new Paint();
    paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
    paint.setAntiAlias(true);
    RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
    canvas.drawRoundRect(rectF, radius, radius, paint);
    return result;
  }


  @Override
  public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

  }*/

}
