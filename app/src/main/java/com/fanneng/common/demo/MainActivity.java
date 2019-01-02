package com.fanneng.common.demo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;

import com.fanneng.common.demo.api.LoginService;
import com.fanneng.common.demo.entity.UserInfo;
import com.fanneng.common.net.BaseObserver;
import com.fanneng.common.utils.LogUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends RxAppCompatActivity {

  private String url = "http://zhainanba.net/wp-content/uploads/2017/08/2017-8-31-15385-5.jpg";

  @SuppressLint("CheckResult")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //AppContextUtils.init(this.getApplicationContext());

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

    /*CustomProgressDialogUtils progressDialogUtils = new CustomProgressDialogUtils();

    progressDialogUtils.showProgress(this,"正在加载中…");*/


    LoginService.getInstance()
        .postLogin1("18800010001", "123456")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .compose(this.bindToLifecycle())
        .subscribe(new BaseObserver<UserInfo>() {

          @Override
          public void onSuccess(UserInfo response) {

          }
        });

    LoginService.getInstance()
        .postLogin("18800010001", "123456")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .compose(this.bindToLifecycle())
        .subscribe(new BaseObserver<UserInfo>() {

          @Override
          public void onSuccess(UserInfo response) {

          }
        });


  }
}
