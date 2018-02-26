package linchange.com.core.wechat.callback;

/**
 * Created by lkmc2 on 2018/2/26.
 * 微信登陆回调接口
 */

public interface IWeChatSignInCallback {

    void onSignInSuccess(String userInfo); //微信登陆成功

}
