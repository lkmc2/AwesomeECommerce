package linchange.com.core.net.callback;


import android.os.Handler;

import linchange.com.core.ui.AwesomeLoader;
import linchange.com.core.ui.LoaderStyle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lkmc2 on 2017/12/29.
 * 请求回调对象
 */

public class RequestCallbacks implements Callback<String> {

    private final IRequest REQUEST; //请求接口
    private final ISuccess SUCCESS; //请求成功接口
    private final IFailure FAILURE; //请求失败接口
    private final IError ERROR; //请求错误接口
    private final LoaderStyle LOADER_STYLE; //进度加载器类型
    private static final Handler HANDLER = new Handler(); //界面控制器

    public RequestCallbacks(IRequest request, ISuccess success,
                            IFailure failure, IError error, LoaderStyle style) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) { //响应获取成功
            if (call.isExecuted()) { //方法执行
                if (SUCCESS != null) { //成功回调接口非空
                    SUCCESS.onSuccess(response.body()); //执行成功方法
                }
            }
        } else {
            if (ERROR != null) { //错误回调接口接口非空
                ERROR.onError(response.code(), response.message()); //执行错误方法
            }
        }

        stopLoading(); //取消显示进度加载器
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) { //请求失败回调接口非空
            FAILURE.onFailure(); //执行请求失败方法
        }

        if (REQUEST != null) { //请求回调接口非空
            REQUEST.onRequestEnd(); //执行请求完成
        }

        stopLoading(); //取消显示进度加载器
    }

    /**
     * 取消显示进度加载器
     */
    private void stopLoading() {
        if (LOADER_STYLE != null) { //进度加载器样式非空
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    AwesomeLoader.stopLoading(); //取消显示进度加载器
                }
            }, 1000); //延时1秒执行
        }
    }
}
