package com.fanneng.common.demo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;

import com.fanneng.common.net.dialog.CustomProgressDialogUtils;
import com.fanneng.common.utils.LogUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

public class MainActivity extends RxAppCompatActivity {

  private String url = "http://zhainanba.net/wp-content/uploads/2017/08/2017-8-31-15385-5.jpg";

  @SuppressLint("CheckResult")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ImageView imageView = findViewById(R.id.iv_test);

    imageView.setOnClickListener(v ->
        LogUtils.e("--->" + BuildConfig.DEBUG + "asdfsadf"));

    //ClearGlideCacheUtils.getInstance().clearCacheMemory();
    //ClearGlideCacheUtils.getInstance().cleanCatchDisk();
    //Glide.with(this).load(url).into(imageView);

    // GlideUtils.show(this, R.mipmap.ic_launcher, url, imageView);

    //GlideUtils.showFade(this, R.mipmap.ic_launcher, url, imageView);

    //GlideUtils.showCircleRound(this, R.mipmap.ic_launcher, url, imageView,0);

    //GlideUtils.showCircle(this,R.mipmap.ic_launcher,R.mipmap.ic_launcher,imageView);


    //GlideUtils.showRectangleCircle(this,R.mipmap.ic_launcher,url,imageView);


    /*Flowable.create((FlowableEmitter<Drawable> emitter) -> {
      FutureTarget<Drawable> submit = GlideUtils.iShow(this, R.mipmap.ic_launcher, url).diskCacheStrategy
          (DiskCacheStrategy.RESOURCE).submit(100, 100);
      Drawable drawable1 = submit.get();
      emitter.onNext(drawable1);
    }, BackpressureStrategy.BUFFER)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(drawable -> imageView.setBackgroundDrawable(drawable));*/

    CustomProgressDialogUtils progressDialogUtils = new CustomProgressDialogUtils();

    progressDialogUtils.showProgress(this,"正在加载中…");
  }
}
