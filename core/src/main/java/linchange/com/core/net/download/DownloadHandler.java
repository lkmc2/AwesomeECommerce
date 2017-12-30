package linchange.com.core.net.download;

import android.os.AsyncTask;

import java.util.WeakHashMap;

import linchange.com.core.net.RestCreator;
import linchange.com.core.net.callback.IError;
import linchange.com.core.net.callback.IFailure;
import linchange.com.core.net.callback.IRequest;
import linchange.com.core.net.callback.ISuccess;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lkmc2 on 2017/12/30.
 * 下载控制器
 */

public class DownloadHandler {
    private final String URL; //请求网址
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams(); //请求参数
    private final String DOWNLOAD_DIR; //下载路径
    private final String EXTENSION; //下载后缀名
    private final String NAME; //下载文件名
    private final IRequest REQUEST; //请求接口
    private final ISuccess SUCCESS; //请求成功接口
    private final IFailure FAILURE; //请求失败接口
    private final IError ERROR; //请求错误接口

    public DownloadHandler(String url,
                           String downloadDir,
                           String extension,
                           String name,
                           IRequest request,
                           ISuccess success,
                           IFailure failure,
                           IError error) {
        this.URL = url;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
    }

    public void handleDownload() {
        if (REQUEST != null) { //请求接口非空
            REQUEST.onRequestStart(); //请求开始
        }

        //执行下载操作
        RestCreator.getRestService().download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) { //响应成功
                            final ResponseBody responseBody = response.body(); //获取请求体

                            //保存文件异步任务
                            final SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                                    DOWNLOAD_DIR, EXTENSION, responseBody, NAME);

                            //此处进行判断防止文件下载不全
                            if (task.isCancelled()) { //如果任务取消
                                if (REQUEST != null) { //请求接口非空
                                    REQUEST.onRequestEnd(); //执行请求结束
                                }
                            }
                        } else { //响应错误
                            if (ERROR != null) { //错误接口非空
                                ERROR.onError(response.code(), response.message()); //执行错误回调
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAILURE != null) { //失败接口非空
                            FAILURE.onFailure(); //执行失败回调
                        }
                    }
                });
    }

}
