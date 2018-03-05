package linchange.com.core.net.rx;

import android.content.Context;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import linchange.com.core.net.HttpMethod;
import linchange.com.core.net.RestCreator;
import linchange.com.core.ui.loader.AwesomeLoader;
import linchange.com.core.ui.loader.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by lkmc2 on 2017/12/29.
 * 网络请求客户端
 */

public class RxRestClient {
    private final String URL; //请求网址
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams(); //请求参数
    private final RequestBody BODY; //请求体
    private final LoaderStyle LOADER_STYLE; //进度加载器样式
    private final File FILE; //文件
    private final Context CONTEXT; //上下文对象

    public RxRestClient(String url,
                        Map<String, Object> params,
                        RequestBody body,
                        LoaderStyle loader_style,
                        File file,
                        Context context) {
        this.URL = url;
        this.PARAMS.putAll(params);
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
    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }

    /**
     * get网络请求方法
     */
    public final Observable<String> get() {
        return request(HttpMethod.GET);
    }

    /**
     * post网络请求方法
     */
    public final Observable<String> post() {
        if (BODY == null) { //请求体为空
            return request(HttpMethod.POST); //执行Post请求
        } else { //请求体非空
            if (!PARAMS.isEmpty()) { //请求参数非空，抛出异常
                throw new RuntimeException("参数必须是null");
            }
            return request(HttpMethod.POST_RAW); //执行Post原生数据请求
        }
    }

    /**
     * put网络请求方法
     */
    public final Observable<String> put() {
        if (BODY == null) { //请求体为空
            return request(HttpMethod.PUT); //执行Put请求
        } else { //请求体非空
            if (!PARAMS.isEmpty()) { //请求参数非空，抛出异常
                throw new RuntimeException("参数必须是null");
            }
            return request(HttpMethod.PUT_RAW); //执行Put原生数据请求
        }
    }

    /**
     * delete网络请求方法
     */
    public final Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }

    /**
     * 下载文件方法
     */
    public final Observable<ResponseBody> download() {
        return RestCreator.getRxRestService().download(URL, PARAMS);
//        //使用下载器处理下载事件
//        new DownloadHandler(URL, DOWNLOAD_DIR, EXTENSION, NAME, REQUEST, SUCCESS, FAILURE, ERROR)
//                .handleDownload();
    }

    /**
     * 请求网络数据
     *
     * @param method 网络请求方法
     * @return 可观察对象
     */
    private Observable<String> request(HttpMethod method) {
        final RxRestService service = RestCreator.getRxRestService(); //获取网络请求服务
        Observable<String> observable = null; //可观察者

        if (LOADER_STYLE != null) { //进度加载器样式非空
            AwesomeLoader.showLoading(CONTEXT, LOADER_STYLE); //显示进度加载器
        }

        switch (method) { //按照传入的请求方式生成call对象
            case GET:
                observable = service.get(URL, PARAMS);
                break;
            case POST:
                observable = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                observable = service.postRaw(URL, BODY);
                break;
            case PUT:
                observable = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                observable = service.putRaw(URL, BODY);
                break;
            case DELETE:
                observable = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                //获取一个包含了文件内容的请求体
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                //生成新的请求表单
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                observable = service.upload(URL, body); //执行文件上传
                break;
            default:
                break;
        }

        return observable;
    }

}
