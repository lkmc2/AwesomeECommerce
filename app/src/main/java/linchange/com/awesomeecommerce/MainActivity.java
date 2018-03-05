package linchange.com.awesomeecommerce;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import linchange.com.core.activities.ProxyActivity;
import linchange.com.core.app.Awesome;
import linchange.com.core.delegates.AwesomeDelegate;
import linchange.com.core.ui.launcher.ILauncherListener;
import linchange.com.core.ui.launcher.OnLauncherFinishTag;
import linchange.com.ec.launcher.LauncherDelegate;
import linchange.com.ec.main.EcBottomDelegate;
import linchange.com.ec.sign.ISignListener;
import linchange.com.ec.sign.SignInDelegate;


public class MainActivity extends ProxyActivity
                            implements ISignListener,
                                        ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Awesome.getConfigurator().withActivity(this); //存储activity对象到全局配置
    }

    //设置根代理
    @Override
    public AwesomeDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGN: //已登录
                Toast.makeText(this, "启动结束，用户登陆了", Toast.LENGTH_LONG).show();
                startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGN: //未登录
                Toast.makeText(this, "启动结束，用户没登陆", Toast.LENGTH_LONG).show();
                startWithPop(new SignInDelegate()); //进入注册页面，并把上一页面清除
                break;
            default:
                break;
        }
    }
}
