package linchange.com.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;
import linchange.com.core.app.AccountManager;
import linchange.com.core.app.IUserChecker;
import linchange.com.core.delegates.AwesomeDelegate;
import linchange.com.core.ui.ILauncherListener;
import linchange.com.core.ui.OnLauncherFinishTag;
import linchange.com.core.util.storage.AwesomePreference;
import linchange.com.core.util.timer.BaseTimerTask;
import linchange.com.core.util.timer.ITimerListener;
import linchange.com.ec.R;
import linchange.com.ec.R2;

/**
 * Created by lkmc2 on 2017/12/31.
 * 启动界面的Fragment
 */

public class LauncherDelegate extends AwesomeDelegate implements ITimerListener {

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null; //文字控件
    private Timer mTimer = null; //时间控制器
    private int mCount = 5; //倒计时的数字

    private ILauncherListener mLauncherListener; //启动监听器

    @OnClick(R2.id.tv_launcher_timer) //为文字控件设置点击事件
    void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll(); //判断是否展示滚动启动页
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {
        initTimer(); // 初始化计时器
    }

    /**
     * 初始化计时器
     */
    private void initTimer() {
        mTimer = new Timer(); //初始化计时器
        final BaseTimerTask task = new BaseTimerTask(this); //初始化计时任务
        mTimer.schedule(task, 0, 1000); //设置计时任务到计时器
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof ILauncherListener) {
            mLauncherListener = (ILauncherListener) activity;
        }
    }

    /**
     * 判断是否展示滚动启动页
     */
    private void checkIsShowScroll() {
        //如果是第一次启动
        if (!AwesomePreference.getAppFlag(ScrollLauncherType.HAS_FIRST_LAUNCHER_APP.name())) {
            start(new LauncherScrollDelegate(), SINGLETASK); //启动滚动启动页
        } else { //非第一次启动
            //检查用户是否登录
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mLauncherListener != null) {
                        mLauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGN); //启动结束登录事件
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mLauncherListener != null) {
                        mLauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGN); //启动结束未登录事件
                    }
                }
            });
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() { //代理界面运行在主线程
            @Override
            public void run() {
                if (mTvTimer != null) { //文字计时器非空
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--; //计时数减1
                    if (mCount < 0) { //计时数不大于0
                        if (mTimer != null) { //计时器非空
                            mTimer.cancel(); //取消计时
                            mTimer = null; //置空计时器
                            checkIsShowScroll(); //判断是否展示滚动启动页
                        }
                    }
                }
            }
        });
    }
}
