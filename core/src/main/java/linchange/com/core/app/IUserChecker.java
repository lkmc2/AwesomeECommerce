package linchange.com.core.app;

/**
 * Created by lkmc2 on 2018/2/22.
 * 用户登陆检查接口
 */

public interface IUserChecker {
    void onSignIn(); //用户登陆
    void onNotSignIn(); //用户未登录
}
