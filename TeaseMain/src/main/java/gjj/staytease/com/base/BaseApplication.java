package gjj.staytease.com.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;


import com.easemob.EMCallBack;
import com.tencent.connect.auth.QQAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;


import java.util.Iterator;
import java.util.List;

import gjj.staytease.com.chatuidemo.DemoHXSDKHelper;
import gjj.staytease.com.config.ApiConstants;
import gjj.staytease.com.interactor.service.PlayService;
import gjj.staytease.com.util.SPUtils;

/**
 * Created by gaojiangjian on 15/11/20.
 */
public class BaseApplication extends Application {
    public static Context context;
    public static IWXAPI msgApi;
    public static QQAuth qqAuth;
    public static Tencent tencent;


    public static Context applicationContext;
    private static BaseApplication instance;
    // login user name
    public final String PREF_USERNAME = "username";

    /**
     * 当前用户nickname,为了苹果推送不是userid而是昵称
     */
    public static String currentUserNick = "";
    public static DemoHXSDKHelper hxSDKHelper = new DemoHXSDKHelper();

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        instance = this;
        context = getApplicationContext();
        msgApi = WXAPIFactory.createWXAPI(this, ApiConstants.WEIXINAPP_ID);
        msgApi.registerApp(ApiConstants.WEIXINAPP_ID);
        qqAuth = QQAuth.createInstance(ApiConstants.QQAPP_ID, this);
        tencent = Tencent.createInstance(ApiConstants.QQAPP_ID, this);
         startService(new Intent(context, PlayService.class));
        hxSDKHelper.onInit(applicationContext);
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
//         如果app启用了远程的service，此application:onCreate会被调用2次
//         为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
//         默认的app会在以包名为默认的process name下运行，如果查到的process name不是app的process
//         name就立即返回

        if (processAppName == null
                || !processAppName.equalsIgnoreCase("gjj.staytease.com")) {
            // Log.e(TAG, "enter the service process!");
            // "com.easemob.chatuidemo"为demo的包名，换到自己项目中要改成自己包名

            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }
    }

    public static BaseApplication getInstance() {
        return instance;
    }


    /**
     * 获取当前登陆用户名
     *
     * @return
     */
    public String getUserName() {
        return hxSDKHelper.getHXId();
    }

    /**
     * 获取密码
     *
     * @return
     */
    public String getPassword() {
        return hxSDKHelper.getPassword();
    }

    /**
     * 设置用户名
     *
     * @paramuser
     */
    public void setUserName(String username) {
        hxSDKHelper.setHXId(username);
    }

    /**
     * 设置密码 下面的实例代码 只是demo，实际的应用中需要加password 加密后存入 preference 环信sdk
     * 内部的自动登录需要的密码，已经加密存储了
     *
     * @param pwd
     */
    public void setPassword(String pwd) {
        hxSDKHelper.setPassword(pwd);
    }

    /**
     * 退出登录,清空数据
     */
    public void logout(final boolean isGCM, final EMCallBack emCallBack) {
        // 先调用sdk logout，在清理app中自己的数据
        hxSDKHelper.logout(isGCM, emCallBack);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        msgApi.unregisterApp();
        stopService(new Intent(context, PlayService.class));
    }


    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this
                .getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i
                    .next());
            try {
                if (info.pid == pID) {
                    CharSequence c = pm.getApplicationLabel(pm
                            .getApplicationInfo(info.processName,
                                    PackageManager.GET_META_DATA));
                    // Log.d("Process", "Id: "+ info.pid +" ProcessName: "+
                    // info.processName +"  Label: "+c.toString());
                    // processName = c.toString();
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }
}
