package linchange.com.core.net.rx;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by lkmc2 on 2017/12/29.
 * RxJava响应式网络请求服务接口
 */

public interface RxRestService {

    /**
     * get请求
     * @param url 请求网址
     * @param params 请求参数
     * @return 网络数据
     */
    @GET
    Observable<String> get(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * post请求
     * @param url 请求网址
     * @param params 请求参数
     * @return 网络数据
     */
    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url, @FieldMap Map<String, Object> params);

    /**
     * post提交原生数据请求
     * @param url 请求网址
     * @param body 请求体
     * @return 网络数据
     */
    @POST
    Observable<String> postRaw(@Url String url, @Body RequestBody body);

    /**
     * put请求
     * @param url 请求网址
     * @param params 请求参数
     * @return 网络数据
     */
    @FormUrlEncoded
    @PUT
    Observable<String> put(@Url String url, @FieldMap Map<String, Object> params);

    /**
     * put原生数据请求
     * @param url 请求网址
     * @param body 请求体
     * @return 网络数据
     */
    @PUT
    Observable<String> putRaw(@Url String url, @Body RequestBody body);

    /**
     * delete请求
     * @param url 请求网址
     * @param params 请求参数
     * @return 网络数据
     */
    @DELETE
    Observable<String> delete(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * 下载数据
     * @param url 请求网址
     * @param params 请求参数
     * @return 网络数据
     */
    @Streaming //边下载文件边存储
    @GET
    Observable<ResponseBody> download(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * 上传数据
     * @param url 请求网址
     * @param file 上传的文件
     * @return 网络数据
     */
    @Multipart
    @GET
    Observable<String> upload(@Url String url, @Part MultipartBody.Part file);
}
