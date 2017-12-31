package linchange.com.core.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import linchange.com.core.activities.ProxyActivity;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by lkmc2 on 2017/12/28.
 * Fragment代理基础界面
 */

public abstract class BaseDelegate extends SwipeBackFragment {

    @SuppressWarnings("SpellCheckingInspection")
    private Unbinder mUnbinder = null; //Butterknife的未绑定器

    public abstract Object setLayout(); //设置布局

    public abstract void onBindView(@Nullable Bundle saveInstanceState, View rootView); //绑定视图

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView; //根界面

        if (setLayout() instanceof Integer) { //传入int型资源文件
            //加载布局
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) { //传入View型对象
            rootView = (View) setLayout(); //将对象转换成View设置给根界面
        } else { //非int或者View类型
            throw new ClassCastException("setLayout 的类型必须是int或者View类型");
        }

        mUnbinder = ButterKnife.bind(this, rootView); //设置未绑定器绑定的Fragment与布局
        onBindView(savedInstanceState, rootView); //绑定视图

        return rootView; //返回根布局
    }

    /**
     * 获取代理的Activity
     * @return 代理的Activity
     */
    public final ProxyActivity getProxyActivity() {
        return (ProxyActivity) _mActivity;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mUnbinder != null) { //未绑定器非空
            mUnbinder.unbind(); //解除绑定
        }
    }
}
