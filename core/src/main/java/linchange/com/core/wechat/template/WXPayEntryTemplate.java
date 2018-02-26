package linchange.com.core.wechat.template;

import linchange.com.core.wechat.BaseWXEntryActivity;

/**
 * Created by lkmc2 on 2018/2/26.
 * 微信支付实体模版
 */

public class WXPayEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish(); //结束界面
        overridePendingTransition(0, 0); //设置进入退出无动画
    }

    @Override
    protected void onSignInSuccess(String userInfo) {

    }
}
