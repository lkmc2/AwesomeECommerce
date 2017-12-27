package linchange.com.core.app;

import android.content.Context;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by lkmc2 on 2017/12/27.
 * 全局配置对象
 */

public class Awesome {

    /**
     * 初始化配置信息
     * @param context 上下文
     * @return 配置器
     */
    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    /**
     * 获取配置信息
     * @return 配置信息
     */
    private static HashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getAwesomeConfigs();
    }

    /**
     * 获取全局应用上下文
     * @return 全局应用上下文
     */
    public static Context getApplication() {
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }

}
