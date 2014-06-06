package jacky.test.push.xgpush;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

import java.util.List;

/**
 * Created by jbai on 14-6-6.
 */
public class XingePushInit {

    private static Context context;

    public static void init(Context context) {
        if (context == null) {
            return;
        }

        XingePushInit.context = context;

        if (shouldInit()) {
            initXingeiPush();
        }
    }

    private static void initXingeiPush() {
        XGPushConfig.enableDebug(context, true);
        XGPushManager.registerPush(context,"CLIENT_ID",new XGIOperateCallback() {
            @Override
            public void onSuccess(Object o, int i) {
                Log.d("push", "信鸽注册成功，设备token为" + o);
            }

            @Override
            public void onFail(Object o, int i, String s) {
                Log.d("push","信鸽注册失败");
            }
        });
    }

    private static boolean shouldInit(){
        ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningServiceInfo> serviceInfos = am.getRunningServices(100);
        if (serviceInfos != null) {
            String pushServiceName = context.getPackageName() + ":xg_service_v2";
            for (ActivityManager.RunningServiceInfo info : serviceInfos) {
                if (pushServiceName.equals(info.process)) {//找到信鸽
                    return false;
                }
            }
        }
        return true;
    }
}
