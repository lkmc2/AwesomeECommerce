package linchange.com.core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import linchange.com.core.app.Awesome;
import linchange.com.core.net.RestClient;
import linchange.com.core.net.callback.ISuccess;

/**
 * Created by lkmc2 on 2018/3/5.
 * 布局刷新控制器
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT; //下拉刷新布局

    public RefreshHandler(SwipeRefreshLayout refresh_layout) {
        REFRESH_LAYOUT = refresh_layout;
        REFRESH_LAYOUT.setOnRefreshListener(this); //设置刷新监听器
    }

    private void refresh() { //布局刷新
        REFRESH_LAYOUT.setRefreshing(true); //设置布局刷新
        Awesome.getHandler().postDelayed(new Runnable() { //延迟两秒执行操作
            @Override
            public void run() {
                //进行一些网络请求
                REFRESH_LAYOUT.setRefreshing(false); //取消布局刷新
            }
        }, 2000);
    }

    public void firstPage(String url) { //获取第一页数据
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(Awesome.getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }

    @Override
    public void onRefresh() {
        refresh(); //布局刷新
    }
}
