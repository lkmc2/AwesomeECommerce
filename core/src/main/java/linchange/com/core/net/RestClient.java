package linchange.com.core.net;

import android.content.Context;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import linchange.com.core.net.callback.IError;
import linchange.com.core.net.callback.IFailure;
import linchange.com.core.net.callback.IRequest;
import linchange.com.core.net.callback.ISuccess;
import linchange.com.core.net.callback.RequestCallbacks;
import linchange.com.core.ui.AwesomeLoader;
import linchange.com.core.ui.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by lkmc2 on 2017/12/29.
 * 网络请求客户端
 */

public class RestClient {
    private final String URL; //请求网址
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams(); //请求参数
    private final IRequest REQUEST; //请求接口
    private final ISuccess SUCCESS; //请求成功接口
    private final IFailure FAILURE; //请求失败接口
    private final IError ERROR; //请求错误接口
    private final RequestBody BODY; //请求体
    private final LoaderStyle LOADER_STYLE; //进度加载器样式
    private final File FILE; //文件
    private final Context CONTEXT; //上下文对象

    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      LoaderStyle loader_style,
                      File file,
                      Context context) {
        this.URL = url;
        this.PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.LOADER_STYLE = loader_style;
        this.FILE = file;
        this.CONTEXT = context;
    }

    /**
     * 用构造者方式新建网络请求客户端
     *
     * @return 网络请求客户端
     */
    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    /**
     * get网络请求方法
     */
    public final void get() {
        request(HttpMethod.GET);
    }

    /**
     * post网络请求方法
     */
    public final void post() {
        if (BODY == null) { //请求体为空
            request(HttpMethod.POST); //执行Post请求
        } else { //请求体非空
            if (!PARAMS.isEmpty()) { //请求参数非空，抛出异常
                throw new RuntimeException("参数必须是null");
            }
            request(HttpMethod.POST_RAW); //执行Post原生数据请求
        }
    }

    /**
     * put网络请求方法
     */
    public final void put() {
        if (BODY == null) { //请求体为空
            request(HttpMethod.PUT); //执行Put请求
        } else { //请求体非空
            if (!PARAMS.isEmpty()) { //请求参数非空，抛出异常
                throw new RuntimeException("参数必须是null");
            }
            request(HttpMethod.PUT_RAW); //执行Put原生数据请求
        }
    }

    /**
     * delete网络请求方法
     */
    public final void delete() {
        request(HttpMethod.DELETE);
    }

    /**
     * 请求网络数据
     *
     * @param method 网络请求方法
     */
    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService(); //获取网络请求服务
        Call<String> call = null; //调用对象

        if (REQUEST != null) { //请求接口非空
            REQUEST.onRequestStart(); //开始请求
        }

        if (LOADER_STYLE != null) { //进度加载器样式非空
            AwesomeLoader.showLoading(CONTEXT, LOADER_STYLE); //显示进度加载器
        }

        switch (method) { //按照传入的请求方式生成call对象
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                //获取一个包含了文件内容的请求体
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                //生成新的请求表单
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = RestCreator.getRestService().upload(URL, body); //执行文件上传
                break;
            default:
                break;
        }

        if (call != null) { //call对象非空
            call.enqueue(getRequestCallback()); //在子线程执行异步请求
        }
    }

    /**
     * 获取请求回调接口
     * @return 请求回调接口
     */
    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(REQUEST, SUCCESS, FAILURE, ERROR, LOADER_STYLE);
    }
}
