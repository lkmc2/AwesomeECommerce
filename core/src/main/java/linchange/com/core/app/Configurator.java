package linchange.com.core.app;

import android.app.Activity;
import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by lkmc2 on 2017/12/27.
 * 配置器
 */

public class Configurator {
    //消息控制器
    private static final Handler HANDLER = new Handler();

    //配置信息列表
    private static final HashMap<Object, Object> AWESOME_CONFIGS = new HashMap<>();

    //图标列表
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    //拦截器列表
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    //构造器
    private Configurator() {
        AWESOME_CONFIGS.put(ConfigKeys.CONFIG_READY, false); //设置配置未完成
        AWESOME_CONFIGS.put(ConfigKeys.HANDLER, HANDLER); //设置消息控制器
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
    final HashMap<Object, Object> getAwesomeConfigs() {
        return AWESOME_CONFIGS;
    }

    //设置配置完成
    public final void configure() {
        initIcons(); //初始化图标
        AWESOME_CONFIGS.put(ConfigKeys.CONFIG_READY, true); //设置配置完成
    }

    /**
     * 配置api信息
     * @param host 主机地址
     * @return 控制器
     */
    public final Configurator withApiHost(String host) {
        AWESOME_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    /**
     * 延迟加载
     * @param delayed 延迟时间
     * @return 控制器
     */
    public final Configurator withLoaderDelayed(long delayed) {
        AWESOME_CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed);
        return this;
    }

    //检查配置信息是否配置完成
    private void checkConfiguration() {
        //获取是否配置完成
        final boolean isReady = (boolean) AWESOME_CONFIGS.get(ConfigKeys.CONFIG_READY);
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
    final <T> T getConfiguration(Object key) {
        checkConfiguration(); //检查是否配置完成
        final Object value = AWESOME_CONFIGS.get(key);
        if (value == null) {
            throw new RuntimeException(key.toString() + "的值为空");
        }
        return (T) value;
    }

    /**
     * 初始化图标
     */
    private void initIcons() {
        if (ICONS.size() > 0) { //图标列表内容大于0
            //使用第0个图标获取初始化器
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i)); //初始化器对剩下的图标进行初始化
            }
        }
    }

    /**
     * 设置拦截器
     * @param interceptor 拦截器
     * @return 配置器对象
     */
    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor); //将拦截器添加到拦截器列表
        AWESOME_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS); //存储拦截器列表
        return this;
    }

    /**
     * 设置拦截器列表
     * @param interceptors 拦截器列表
     * @return 配置器对象
     */
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors); //将传入的拦截器列表传入拦截器列表
        AWESOME_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS); //存储拦截器列表
        return this;
    }

    /**
     * 添加图标
     * @param descriptor 图标描述
     * @return 配置器对象
     */
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor); //将图标添加到图标列表
        return this;
    }

    /**
     * 添加微信AppId
     * @param appId 微信AppId
     * @return 配置器对象
     */
    public final Configurator withWeChatAppId(String appId) {
        AWESOME_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID, appId); //存储appid
        return this;
    }

    /**
     * 添加微信App密匙
     * @param appSecrest 微信App密匙
     * @return 配置器对象
     */
    public final Configurator withWeChatAppSecret(String appSecrest) {
        AWESOME_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID, appSecrest); //存储appid
        return this;
    }

    /**
     * 添加Activity
     * @param activity activity上下文
     * @return 配置器对象
     */
    public final Configurator withActivity(Activity activity) {
        AWESOME_CONFIGS.put(ConfigKeys.ACTIVITY, activity); //存储activity
        return this;
    }
}
