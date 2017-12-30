package linchange.com.core.net;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import linchange.com.core.app.Awesome;
import linchange.com.core.app.ConfigKeys;
import linchange.com.core.net.rx.RxRestService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by lkmc2 on 2017/12/29.
 * 网络请求生成器
 */

public class RestCreator {

    //请求参数控制器
    private static final class ParamsHolder {
        //请求参数列表
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    /**
     * 获取请求参数列表
     * @return 请求参数列表
     */
    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    //OkHttp控制器
    private static final class OKHttpHolder {
        private static final int TIME_OUT = 60; //请求超时时间
        //网络请求客户端生成器
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        //拦截器列表
        private static final ArrayList<Interceptor> INTERCEPTORS = Awesome.getConfiguration(ConfigKeys.INTERCEPTOR);

        /**
         * 添加拦截器
         * @return 网络请求客户端生成器
         */
        private static OkHttpClient.Builder addInterceptor() {
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) { //拦截器列表非空
                for (Interceptor interceptor : INTERCEPTORS) { //遍历拦截器列表
                    BUILDER.addInterceptor(interceptor); //将拦截器添加到网络请求客户端生成器
                }
            }
            return BUILDER; //返回网络请求客户端生成器
        }

        //OkHttp实例
        private static final OkHttpClient OK_HTTP_CLIENT =
                addInterceptor() //添加拦截器
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS) //设置连接超时时间
                .build(); //生成OkHttp实例
    }

    //Retrofit控制器
    private static final class RetrofitHolder {
        //从全局配置中获取请求网址
        private static final String BASE_URL = Awesome.getConfiguration(ConfigKeys.API_HOST);

        //Retrofit实例
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL) //基础请求地址
                .client(OKHttpHolder.OK_HTTP_CLIENT) //设置请求的客户端为OkHttp实例
                .addConverterFactory(ScalarsConverterFactory.create()) //添加标准转换工厂
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build(); //生成Retrofit实例
    }

    //网络请求服务控制器
    private static final class RestServiceHolder {
        //创建网络请求服务
        private static final RestService REST_SERVICE
                = RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    /**
     * 获取网络请求服务
     * @return 网络请求服务
     */
    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    //网络请求服务控制器
    private static final class RxRestServiceHolder {
        //创建网络请求服务
        private static final RxRestService REST_SERVICE
                = RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
    }

    /**
     * 获取网络请求服务
     * @return 网络请求服务
     */
    public static RxRestService getRxRestService() {
        return RxRestServiceHolder.REST_SERVICE;
    }

}
