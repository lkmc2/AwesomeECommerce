package linchange.com.core.net.intercepts;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * Created by lkmc2 on 2017/12/30.
 * 基础拦截器
 */

public abstract class BaseInterceptor implements Interceptor {

    /**
     * 获取网址参数
     * @param chain 请求链
     * @return 网址参数链表
     */
    protected LinkedHashMap<String, String> getUrlParameters(Chain chain) {
        final HttpUrl url = chain.request().url(); //获取请求网址
        int size = url.querySize(); //获取请求的个数

        //参数链表
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) { //将参数的键值添加到参数链表
            params.put(url.queryParameterName(i), url.queryParameterValue(i));
        }
        return params; //返回参数链表
    }

    /**
     * 获取网址参数
     * @param chain 请求链
     * @param key 要获取的参数名
     * @return 要获取参数的值
     */
    protected String getUrlParameters(Chain chain, String key) {
        final Request request = chain.request(); //获取请求
        return request.url().queryParameter(key); //返回网址中该参数的值
    }

    /**
     * 获取请求体参数
     * @param chain 请求链
     * @return 参数链表
     */
    protected LinkedHashMap<String, String> getBodyParameters(Chain chain) {
        //获取请求体
        final FormBody formBody = (FormBody) chain.request().body();
        //参数链表
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        int size = formBody.size(); //获取请求的个数
        for (int i = 0; i < size; i++) { //将参数的键值添加到参数链表
            params.put(formBody.name(i), formBody.value(i));
        }
        return params; //返回参数链表
    }

    /**
     * 获取请求体参数（重载）
     * @param chain 请求链
     * @param key 参数名
     * @return 参数链表中该参数名对应的值
     */
    protected String getBodyParameters(Chain chain, String key) {
        return getBodyParameters(chain).get(key);
    }
}
