package linchange.com.net;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import linchange.com.core.app.Awesome;
import linchange.com.core.app.ConfigType;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
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

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }


    //OkHttp控制器
    private static final class OKHttpHolder {
        private static final int TIME_OUT = 60; //请求超时时间

        //OkHttp实例
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS) //设置连接超时时间
                .build(); //生成OkHttp实例
    }

    //Retrofit控制器
    private static final class RetrofitHolder {
        //从全局配置中获取请求网址
        private static final String BASE_URL =
                (String) Awesome.getConfigurations().get(ConfigType.API_HOST.name());

        //Retrofit实例
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL) //基础请求地址
                .addConverterFactory(ScalarsConverterFactory.create()) //添加标准转换工厂
                .client(OKHttpHolder.OK_HTTP_CLIENT) //设置请求的客户端为OkHttp实例
                .build(); //生成Retrofit实例
    }

    //网络请求服务控制器
    private static final class RestServiceHolder {
        //创建网络请求服务
        private static final RestService REST_SERVICE
                = RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

}
