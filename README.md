# Common_Android
## 一、使用
#### 1、添加依赖和配置
```java
allprojects {
    repositories {
        google()
        jcenter()
        maven { url "https://jitpack.io" }
        maven { url "https://source.enncloud.cn/FNMobileTeam/Common_Android/raw/master" }
    }
}
```
```ruby
dependencies {
    implementation 'com.fanneng.android:common:1.0.0@aar'
}
```

#### 2、添加混淆
```ruby
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
* CommonApplication 初始化第三方框架
```ruby
public class YourBaseApplication extend CommonApplication {
    ...
    public String setBaseUrl(){
        return "你的服务器地址，用于网络框架的访问";
    };
}
```
* CommonActivity 初始化工具类
```ruby
public class YourBaseActivity extend CommonActivity {
    ...
}
```
* CommonFragment 初始化工具类
```ruby
public class YourBaseFragment extend CommonFragment {
    ...
}
```

<br><br><br>

## 二、功能介绍
#### utils工具类

* AppContextUtils
```ruby
Context context = AppContextUtils.getContext();
```
可以获取全局Context对象
注意：凡是跟UI相关的，都应该使用Activity做为Context来处理（Dialog、startActivity、Layout Inflation）；其他的一些操作可以使用ApplicationContext
引用ApplicationContext并不会导致内存泄漏。引用Activity的Context才会导致内存泄漏。


* DateUtils<br>
针对日期处理的Utils，包括日期的格式化、判断等操作


* EventBusMsg<br>
EventBus传递内容的封装，举个栗子：
```ruby
//tag:传递消息的标记
//T:索要传递的对象
EventBus.getDefault().post(new EventBusMsg<T>(tag,T))
```


* FileUtils<br>
针对文件处理的辅助工具类


* LogUtils<br>
Log打印工具类，只会在Debug模式下才会打印出来


* NetworkUtils<br>
获取网络状态、网络类型、运营商等工具类


* SpUtils<br>
Sharepreference工具类
```ruby
SpUtils.setXXX(String key, Object value);//设置数据
SpUtils.getXXX(String key);//获取数据
SpUtils.remove(String key);//删除某个数据
SpUtils.removeAll();//删除所有保存的数据
```


* StatusBarUtils<br>
状态栏设置工具类，如果当前Activity/Fragment需要做特殊的状态栏处理则调用
```ruby
StatusBarUtil.setColor(context, ContextCompat.getColor(context,R.color.xxxx));
//如果设置的背景与状态栏字体颜色有冲突，例如状态栏背景颜色和字体颜色都是蓝色，
//则需要在setColor之前调用
//设置字体颜色为黑色
StatusBarUtil.setDarkMode(MainActivity.this);
//或者设置字体颜色为白色
StatusBarUtil.setLightMode(MainActivity.this);
```


* ToastUtils<br>
Toast工具类，已针对重复Toast做处理
```ruby
ToastUtils.show(String msg);
```


* ViewManagerUtils<br>
Activity和Fragment的管理工具类
```java
//退出所有Activity
ViewManagerUtils.getInstance().finishAllActivity();
//退出应用程序
ViewManagerUtils.getInstance().exitApp(context);
```


2. 第三方框架
[网络请求框架 Retrofit](https://github.com/square/retrofit)