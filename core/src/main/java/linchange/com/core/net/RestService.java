package linchange.com.core.net;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
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
 * 网络请求服务接口
 */

public interface RestService {

    /**
     * get请求
     * @param url 请求网址
     * @param params 请求参数
     * @return 网络数据
     */
    @GET
    Call<String> get(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * post请求
     * @param url 请求网址
     * @param params 请求参数
     * @return 网络数据
     */
    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String, Object> params);

    /**
     * 存储数据
     * @param url 请求网址
     * @param params 请求参数
     * @return 网络数据
     */
    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap Map<String, Object> params);

    /**
     * 删除数据
     * @param url 请求网址
     * @param params 请求参数
     * @return 网络数据
     */
    @DELETE
    Call<String> delete(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * 下载数据
     * @param url 请求网址
     * @param params 请求参数
     * @return 网络数据
     */
    @Streaming //边下载文件边存储
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * 上传数据
     * @param url 请求网址
     * @param file 上传的文件
     * @return 网络数据
     */
    @Multipart
    @GET
    Call<ResponseBody> upload(@Url String url, @Part MultipartBody.Part file);
}
