package linchange.com.core.util.timer;

import java.util.TimerTask;

/**
 * Created by lkmc2 on 2017/12/31.
 * 基础计时任务
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener; //时间监听器接口

    public BaseTimerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null) { //时间监听器非空
            mITimerListener.onTimer(); //执行时间监听
        }
    }
}
