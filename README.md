# Common_Android
## 一、使用
#### 1、添加依赖和配置
* 根目录build.gradle文件添加如下配置：

>>```
dependencies {
        classpath 'com.jakewharton:butterknife-gradle-plugin:8.8.1'
    }
allprojects {
    repositories {
        mavenCentral()
        maven { url "https://jitpack.io" }
        maven { url "https://source.enncloud.cn/FNMobileTeam/Common_Android/raw/master" }
    }
}
```

* APP目录build.gradle文件添加如下配置：

>>```
apply plugin: 'com.jakewharton.butterknife'

dependencies {
    implementation 'com.fanneng.android:common:1.0.0@aar'
}
```

#### 2、添加混淆
>>```
#Retrofit2
-keepattributes Signature
#Retain service method parameters.
-keepclassmembernames,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
#Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

#OkHttp3
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**
#A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

#EventBus
-keepattributes *Annotation*
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

#Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}
```

#### 3、继承common类
>>CommonApplication 初始化第三方框架
```
public class YourBaseApplication extend CommonApplication {
    ...
    public String setBaseUrl(){
        return "你的服务器地址，用于网络框架的访问";
    };
}
```
>>CommonActivity 初始化工具类
```
public class YourBaseActivity extend CommonActivity {
    ...
}
```
>>CommonFragment 初始化工具类
```
public class YourBaseFragment extend CommonFragment {
    ...
}
```

<br><br>

## 二、功能介绍

<br>

#### 1、 Net 网络框架封装
* 如何使用<br>

>>1、在你的Api层配置，以登录接口为例
```
    public interface BaseApis {
        /**
         * 登陆接口
         */
        @POST(HEAD_HOST + "user/login")
        Observable<UserInfo> postLogin(@Body Map<String, Object> map);
    }
```

>>2、在Service层提供访问方法
```
public class APIService extends BaseAPIService {

  private final BaseApis mBaseApis;

  private APIService() {
    mBaseApis = RetrofitFactory.getInstance().create(BaseApis.class);
  }

  private static class APIServiceHolder {
    private static final APIService INSTANCE = new APIService();
  }

  public static final APIService getInstance() {
    return APIServiceHolder.INSTANCE;
  }

  //登陆接口
  public Observable<UserInfo> postLogin(String username, String code) {
    Map<String, Object> map = new HashMap<>();
    map.put("username", username);
    map.put("password", code);
    map.put("equipment", PhoneOnlyNumber.getPhoneOnlyID());
    map.put("equipType", "android");
    return mBaseApis.postLogin(map);
  }
}
```

>>3、调用方法
```
APIService.getInstance()
        .postLogin(phoneNum, code)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .compose(activity.<UserInfo>bindToLifecycle())
        .subscribe(new BaseObserver<UserInfo>() {
          @Override
          public void onSuccess(UserInfo response) {
              v.loginSuccessful(response.getData());
          }

          @Override
          public void onError(Throwable e) {
              v.showMsg(e.toString());
          }

          @Override
          public void onFail(UserInfo response) {
              v.showMsg(response.getMsg());
          }

        });
```

>>4、如果App接口要使用token访问，则需要在登录之后设置app_token
```
ApiConfig.getInstance().setAppToken(app_token);
```

<br>
#### 2、utils工具类
<br>

* AppContextUtils
>>```
Context context = AppContextUtils.getContext();
```
可以获取全局Context对象
注意：凡是跟UI相关的，都应该使用Activity做为Context来处理（Dialog、startActivity、Layout Inflation）；其他的一些操作可以使用ApplicationContext，
引用ApplicationContext并不会导致内存泄漏。引用Activity的Context才会导致内存泄漏。
#如果您是继承CommonActivity的，那么您可以在您的app中调用AppContextUtils.getActivity()可以获取到当前Activity对象。

* DateUtils<br>
>>针对日期处理的Utils，包括日期的格式化、判断等操作


* EventBusMsg<br>
>>EventBus传递内容的封装，举个栗子：
```
//tag:传递消息的标记
//T:索要传递的对象
1.EventBus.getDefault().post(new EventBusMsg<T>(tag,T))
2.一个FirstActivity跳转到另一个SecondActivity发送事件
FirstActivity中发送事件：EventBus.getDefault().postSticky(new EventBusMsg<T>(tag,T));
SecondActivity接收事件：
@Subscribe(sticky = true, threadMode = ThreadMode.MAIN_ORDERED)
public void getMsg(EventBusMsg msg) {
if (tag.equals(msg.getTag())) {
 you can do your what you want...
}
}

```


* FileUtils<br>
>>针对文件处理的辅助工具类


* LogUtils<br>
>>Log打印工具类，只会在Debug模式下才会打印出来


* NetworkUtils<br>
>>获取网络状态、网络类型、运营商等工具类


* SpUtils<br>
>>Sharepreference工具类
```
SpUtils.setXXX(String key, Object value);//设置数据
SpUtils.getXXX(String key);//获取数据
SpUtils.remove(String key);//删除某个数据
SpUtils.removeAll();//删除所有保存的数据
```


* StatusBarUtils<br>
>>状态栏设置工具类，如果当前Activity/Fragment需要做特殊的状态栏处理则调用
```
StatusBarUtil.setColor(context, ContextCompat.getColor(context,R.color.xxxx));
//如果设置的背景与状态栏字体颜色有冲突，例如状态栏背景颜色和字体颜色都是蓝色，
//则需要在setColor之前调用
//设置字体颜色为黑色
StatusBarUtil.setDarkMode(MainActivity.this);
//或者设置字体颜色为白色
StatusBarUtil.setLightMode(MainActivity.this);
```


* ToastUtils<br>
>>Toast工具类，已针对重复Toast做处理
```
ToastUtils.show(String msg);
```


* ViewManagerUtils<br>
>>Activity和Fragment的管理工具类
```
//退出所有Activity
ViewManagerUtils.getInstance().finishAllActivity();
//退出应用程序
ViewManagerUtils.getInstance().exitApp(context);
```
<br><br>

#### 3、所使用到的第三方框架
* [网络请求 Retrofit](https://github.com/square/retrofit)
* [网络请求 OKHttp](https://github.com/square/okhttp)
* [响应式编程 RxAndroid](https://github.com/ReactiveX/RxAndroid)
* [线程切换 RxJava](https://github.com/ReactiveX/RxJava)
* [注解 ButterKnife](https://github.com/JakeWharton/butterknife)
* [图表 MPAndroidChart](https://github.com/PhilJay/MPAndroidChart)
* [通信 EventBus](https://github.com/greenrobot/EventBus)
* [内存检测 LeakCanary](https://github.com/square/leakcanary)

<br>

#### 4、 作者
* 鲁宇峰   邮箱：466708987@qq.com
* 王文彬   邮箱：wwb199055@126.com