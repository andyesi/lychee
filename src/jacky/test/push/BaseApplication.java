package jacky.test.push;

import android.app.Application;
import android.util.Log;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.mipush.sdk.Logger;
import jacky.test.push.xmpush.XiaomiPushInit;

/**
 * Created by jbai on 14-6-5.
 */
public class BaseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        XiaomiPushInit.init(BaseApplication.this);
    }


}
