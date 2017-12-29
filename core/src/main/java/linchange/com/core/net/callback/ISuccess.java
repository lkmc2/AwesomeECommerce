package linchange.com.core.net.callback;

/**
 * Created by lkmc2 on 2017/12/29.
 * 请求成功接口
 */

public interface ISuccess {
    /**
     * 请求成功
     * @param response 响应数据
     */
    void onSuccess(String response);
}
