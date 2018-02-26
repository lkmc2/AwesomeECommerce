package linchange.com.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;

import linchange.com.core.app.AccountManager;
import linchange.com.core.app.IUserChecker;
import linchange.com.core.delegates.AwesomeDelegate;
import linchange.com.core.ui.ILauncherListener;
import linchange.com.core.ui.OnLauncherFinishTag;
import linchange.com.core.util.launcher.LauncherHolderCreator;
import linchange.com.core.util.storage.AwesomePreference;
import linchange.com.ec.R;

/**
 * Created by lkmc2 on 2017/12/31.
 * 启动页可滚动Fragment
 */

public class LauncherScrollDelegate
                extends AwesomeDelegate
                implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null; //轮播图控件
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>(); //图片id列表

    private ILauncherListener mLauncherListener; //启动监听器

    /**
     * 初始化轮播图
     */
    private void initBanner() {
        //添加资源到图片id列表
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);

        //设置轮播图控件属性
        mConvenientBanner.setPages(new LauncherHolderCreator(), INTEGERS) //设置页数
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus}) //下标图片
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL) //图片对齐方法
                .setOnItemClickListener(this) //子项点击事件
                .setCanLoop(false); //不能循环
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof ILauncherListener) {
            mLauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        //初始化轮播图控件
        mConvenientBanner = new ConvenientBanner<Integer>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {
        initBanner(); //初始化轮播图
    }

    @Override
    public void onItemClick(int position) {
        if (position == INTEGERS.size() - 1) { //点击最后一张图片
            //设置第一次启动app的标记
            AwesomePreference.setAppFlag(ScrollLauncherType.HAS_FIRST_LAUNCHER_APP.name(), true);

            //检查用户是否已经登陆
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
}
