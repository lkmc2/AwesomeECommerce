package linchange.com.awesomeecommerce;

import android.app.Application;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import linchange.com.core.app.Awesome;
import linchange.com.core.net.intercepts.DebugInterceptor;
import linchange.com.ec.font.FontEcModule;

/**
 * Created by lkmc2 on 2017/12/27.
 * 全局应用
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

//        Iconify.with(new FontAwesomeModule());
        Awesome.init(this) //初始化全局配置对象
                .withIcon(new FontAwesomeModule()) //配置图标
//                .withIcon(new FontEcModule())
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .withApiHost("http://127.0.0.1/") //配置主机地址
                .configure(); //配置完成
    }
}
