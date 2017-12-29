package linchange.com.awesomeecommerce;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import linchange.com.core.delegates.AwesomeDelegate;
import linchange.com.net.RestClient;
import linchange.com.net.callback.IError;
import linchange.com.net.callback.IFailure;
import linchange.com.net.callback.ISuccess;

/**
 * Created by lkmc2 on 2017/12/29.
 * Fragment代理对象
 */

public class ExampleDelegate extends AwesomeDelegate {

    //设置布局
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    //绑定视图
    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://news.baidu.com/")
                .params("", "")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
