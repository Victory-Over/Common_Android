# Common_Android
## 一、使用
### 1、添加依赖和配置
```
allprojects {
    repositories {
        google()
        jcenter()
        maven { url "https://jitpack.io" }
        maven { url "https://source.enncloud.cn/FNMobileTeam/Common_Android/raw/master" }
    }
}
```
```
dependencies {
    implementation 'com.fanneng.android:common:1.0.0@aar'
}
```

### 2、添加混淆
```
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
### 3、继承common类
#### ①、CommonApplication 初始化第三方框架
```
public class YourBaseApplication extend CommonApplication {
    ...
    public String setBaseUrl(){
        return "你的服务器地址，用于网络框架的访问";
    };
}
```
#### ②、CommonActivity 初始化工具类
```
public class YourBaseActivity extend CommonActivity {
    ...
}
```
#### ③、CommonFragment 初始化工具类
```
public class YourBaseFragment extend CommonFragment {
    ...
}
```