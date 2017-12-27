package linchange.com.core.app;

import java.util.WeakHashMap;

/**
 * Created by lkmc2 on 2017/12/27.
 * 配置器
 */

public class Configurator {
    //配置信息列表
    private static final WeakHashMap<String, Object> AWESOME_CONFIGS = new WeakHashMap<>();

    //构造器
    private Configurator() {
        AWESOME_CONFIGS.put(ConfigType.CONFIG_READY.name(), false); //设置配置未完成
    }

    //静态内部类
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    /**
     * 获取配置器实例对象
     * @return 配置器实例对象
     */
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 获取配置信息列表
     * @return 配置信息列表
     */
    final WeakHashMap<String, Object> getAwesomeConfigs() {
        return AWESOME_CONFIGS;
    }

    //设置配置完成
    public final void configure() {
        AWESOME_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    /**
     * 配置api信息
     * @param host 主机地址
     * @return 控制器
     */
    public final Configurator withApiHost(String host) {
        AWESOME_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    //检查配置信息是否配置完成
    private void checkConfiguration() {
        //获取是否配置完成
        final boolean isReady = (boolean) AWESOME_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("配置信息未配置完成，请调用configure()方法");
        }
    }

    /**
     * 获取指定类型的信息
     * @param key 类型名
     * @param <T> 泛型
     * @return 指定类型的信息
     */
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfiguration(); //检查是否配置完成
        return (T) AWESOME_CONFIGS.get(key.name());
    }
}
