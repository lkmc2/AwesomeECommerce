package linchange.com.core.net.callback;

/**
 * Created by lkmc2 on 2017/12/29.
 * 请求错误接口
 */

public interface IError {
    /**
     * 请求错误
     * @param code 错误代码
     * @param msg 错误消息
     */
    void onError(int code, String msg);
}
