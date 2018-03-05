package linchange.com.core.app;

import android.content.Context;
import android.os.Handler;

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
        Configurator.getInstance().getAwesomeConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    /**
     * 获取配置器
     * @return 配置器
     */
    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    /**
     * 获取某项配置信息
     * @param key 配置信息名
     * @param <T> 泛型
     * @return 配置信息列表
     */
    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    /**
     * 获取全局应用上下文
     * @return 全局应用上下文
     */
    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    /**
     * 获取Handler
     * @return Handler实例
     */
    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }

}
