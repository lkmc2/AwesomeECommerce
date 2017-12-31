package linchange.com.awesomeecommerce;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import linchange.com.core.activities.ProxyActivity;
import linchange.com.core.delegates.AwesomeDelegate;
import linchange.com.ec.launcher.LauncherDelegate;
import linchange.com.ec.launcher.LauncherScrollDelegate;


public class MainActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    //设置根代理
    @Override
    public AwesomeDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

}
