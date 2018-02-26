package linchange.com.core.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import linchange.com.core.net.RestClient;
import linchange.com.core.net.callback.IError;
import linchange.com.core.net.callback.IFailure;
import linchange.com.core.net.callback.ISuccess;
import linchange.com.core.util.log.AwesomeLogger;

/**
 * Created by lkmc2 on 2018/2/26.
 * 基础微信实体界面
 */

public abstract class BaseWXEntryActivity extends BaseWXActivity {

    /**
     * 注册成功回调方法
     * @param userInfo 用户信息
     */
    protected abstract void onSignInSuccess(String userInfo);

    //微信发送请求到第三方应用后的回调
    @Override
    public void onReq(BaseReq baseReq) {

    }

    //第三方应用发送请求到微信后的回调
    @Override
    public void onResp(BaseResp baseResp) {
        final String code = ((SendAuth.Resp)baseResp).code;
        final StringBuilder authUrl = new StringBuilder();

        authUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append(AwesomeWeChat.APP_ID)
                .append("&secret=")
                .append(AwesomeWeChat.APP_SECRET)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");

        AwesomeLogger.d("authUrl", authUrl.toString());
        getAuth(authUrl.toString()); //获取微信信息
    }

    /**
     * 获取微信信息
     * @param authUrl 请求网址
     */
    private void getAuth(String authUrl) {
        RestClient.builder()
                .url(authUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject authObject = JSON.parseObject(response);
                        final String accessToken = authObject.getString("access_token");
                        final String openId = authObject.getString("openId");

                        final StringBuilder userInfoUrl = new StringBuilder();

                        userInfoUrl.append("https://api.weixin.qq.com/sns/userinfo?access_token=")
                                .append(accessToken)
                                .append("&openid=")
                                .append(openId)
                                .append("&lang=")
                                .append("zh_CN");

                        AwesomeLogger.d("userInfoUrl", userInfoUrl.toString());
                        getUserInfo(userInfoUrl.toString());
                    }
                })
                .build()
                .get();
    }

    /**
     * 获取用户信息
     * @param userInfoUrl 用户信息请求网址
     */
    private void getUserInfo(String userInfoUrl) {
        RestClient.builder()
                .url(userInfoUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        onSignInSuccess(response); //执行登陆成功事件
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
