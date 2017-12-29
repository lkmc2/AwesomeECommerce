package linchange.com.net;

import java.util.Map;
import java.util.WeakHashMap;

import linchange.com.net.callback.IError;
import linchange.com.net.callback.IFailure;
import linchange.com.net.callback.IRequest;
import linchange.com.net.callback.ISuccess;
import okhttp3.RequestBody;

/**
 * Created by lkmc2 on 2017/12/29.
 * 网络请求客户端
 */

public class RestClient {
    private final String URL; //请求网址
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams(); //请求参数
    private final IRequest IREQUEST; //请求接口
    private final ISuccess ISUCCESS; //请求成功接口
    private final IFailure IFAILURE; //请求失败接口
    private final IError IERROR; //请求错误接口
    private final RequestBody BODY; //请求体

    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body) {
        this.URL = url;
        this.PARAMS.putAll(params);
        this.IREQUEST = request;
        this.ISUCCESS = success;
        this.IFAILURE = failure;
        this.IERROR = error;
        this.BODY = body;
    }

    //用构造者方式新建网络请求客户端
    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }
}
