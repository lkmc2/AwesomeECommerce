package linchange.com.core.wechat;

import android.app.Activity;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import linchange.com.core.app.Awesome;
import linchange.com.core.app.ConfigKeys;
import linchange.com.core.wechat.callback.IWeChatSignInCallback;

/**
 * Created by lkmc2 on 2018/2/26.
 * 微信配置类
 */

public class AwesomeWeChat {
    public static final String APP_ID = Awesome.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    public static final String APP_SECRET = Awesome.getConfiguration(ConfigKeys.WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI;
    private IWeChatSignInCallback mSignInCallback = null;

    private AwesomeWeChat() {
        final Activity activity = Awesome.getConfiguration(ConfigKeys.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }

    /**
     * 设置微信登陆成功回调
     * @param callback 微信登陆成功回调
     * @return 微信配置类
     */
    public AwesomeWeChat onSignSuccess(IWeChatSignInCallback callback) {
        this.mSignInCallback = callback;
        return this;
    }

    /**
     * 获取微信登陆回调
     * @return 微信登陆回调
     */
    public IWeChatSignInCallback getSignInCallback() {
        return mSignInCallback;
    }

    private static final class Holder {
        private static final AwesomeWeChat INSTANCE = new AwesomeWeChat();
    }

    /**
     * 获取微信实例
     * @return 微信实例
     */
    public static AwesomeWeChat getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 获取微信api
     * @return 微信api
     */
    public final IWXAPI getWXAPI() {
        return WXAPI;
    }

    /**
     * 登陆微信api
     */
    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }
}
