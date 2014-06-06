package jacky.test.push.xmpush;

import android.content.Context;
import android.util.Log;
import com.xiaomi.mipush.sdk.*;

/**
 * 小米的消息Receiver
 */
public class XmMessageReceiver extends PushMessageReceiver {
    @Override
    public void onCommandResult(Context context, MiPushCommandMessage cmdMessage) {
        String command = cmdMessage.getCommand();
        int resultCode = (int)cmdMessage.getResultCode();
        if(resultCode == ErrorCode.SUCCESS){// 命令成功
            if(MiPushClient.COMMAND_REGISTER.equals(command)){//注册
                Log.d("push","小米注册成功，"+cmdMessage.getCommandArguments());
                MiPushClient.setAlias(context,"CLIENT_ID",null);//设置别名
            }
            else if (MiPushClient.COMMAND_SET_ALIAS.equals(command)){//注册别名
                Log.d("push","小米别名注册成功，"+cmdMessage.getCommandArguments());
            }
        }
    }

    @Override
    public void onReceiveMessage(Context context, MiPushMessage miPushMessage) {

    }
}
