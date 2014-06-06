package jacky.test.push.xmpush;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;

/**
 * 负责小米push服务的初始化
 */
public class XiaomiPushInit {

    private static final String APP_ID = "2882303761517189335";
    private static final String APP_KEY = "5661718974335";

    private static Context context;

    public static void init(Context context) {
        if (context == null) {
            return;
        }

        XiaomiPushInit.context = context;

        if (shouldInit()) {
            initXiaomiPush();
        }
    }

    private static void initXiaomiPush() {
        LoggerInterface xiaomiLogger = new LoggerInterface() {
            @Override
            public void log(String s) {
                Log.d("xiaomi", s);
            }

            @Override
            public void log(String s, Throwable throwable) {
                Log.d("xiaomi", s, throwable);
            }

            @Override
            public void setTag(String s) {

            }
        };

        Logger.setLogger(context, xiaomiLogger);//设置logger

//        Logger.disablePushFileLog(BaseApplication.this);//设置是否关闭文件log

        Constants.useSandbox();//设置沙盒环境
//        Constants.useOfficial();//设置正式环境

        MiPushClient.registerPush(context,APP_ID,APP_KEY);


    }

    private static boolean shouldInit() {
        ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningServiceInfo> serviceInfos = am.getRunningServices(100);
        if (serviceInfos != null) {
            String pushServiceName = context.getPackageName() + ":xmpush";
            for (ActivityManager.RunningServiceInfo info : serviceInfos) {
                if (pushServiceName.equals(info.process)) {//找到小米
                    return false;
                }
            }
        }
        return true;
    }
}
