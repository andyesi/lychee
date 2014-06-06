package jacky.test.push;

import android.app.Application;
import jacky.test.push.xgpush.XingePushInit;
import jacky.test.push.xmpush.XiaomiPushInit;

/**
 * Created by jbai on 14-6-5.
 */
public class BaseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        XiaomiPushInit.init(BaseApplication.this);

        XingePushInit.init(BaseApplication.this);
    }


}
