package linchange.com.net.callback;

/**
 * Created by lkmc2 on 2017/12/29.
 * 请求接口
 */

public interface IRequest {
    /**
     * 请求开始
     */
    void onRequestStart();

    /**
     * 请求结束
     */
    void onRequestEnd();
}
