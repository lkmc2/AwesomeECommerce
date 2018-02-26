package linchange.com.core.app;

import linchange.com.core.util.storage.AwesomePreference;

/**
 * Created by lkmc2 on 2018/2/22.
 * 账户管理工具
 */

public class AccountManager {

    private enum SignTag {
        SIGN_TAG //登陆
    }

    /**
     * 保存用户登陆状态
     * @param state 登陆状态
     */
    public static void setSignState(boolean state) {
        AwesomePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    /**
     * 获取用户登陆状态
     * @return 登陆状态
     */
    private static boolean isSignIn() {
        return AwesomePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    /**
     * 检查用户登陆状态
     * @param checker 用户登陆检查接口
     */
    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) { //用户已登录
            checker.onSignIn(); //执行登陆事件
        } else { //未登录
            checker.onNotSignIn(); //执行未登录事件
        }
    }
}
