package linchange.com.core.ui;

/**
 * Created by lkmc2 on 2018/2/23.
 * 启动监听器
 */

public interface ILauncherListener {
    /**
     * 启动完成
     * @param tag 启动结束类型（是否登陆）
     */
    void onLauncherFinish(OnLauncherFinishTag tag);
}
