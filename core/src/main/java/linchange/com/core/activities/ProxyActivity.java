package linchange.com.core.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import linchange.com.core.R;
import linchange.com.core.delegates.AwesomeDelegate;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by lkmc2 on 2017/12/28.
 * 代理界面的Activity
 */

public abstract class ProxyActivity extends SupportActivity {

    /**
     * 设置根代理
     * @return 全局代理对象
     */
    public abstract AwesomeDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initContainer(savedInstanceState); //初始化界面容器
    }

    /**
     * 初始化界面容器
     * @param savedInstanceState 实例保存状态
     */
    private void initContainer(@Nullable Bundle savedInstanceState) {
        //新建主容器
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container); //设置容器id

        setContentView(container); //将主容器设置为界面根布局

        if (savedInstanceState == null) { //如果界面数据为空
            //在容器中生成新的Fragment界面
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //系统资源回收
        System.gc();
        System.runFinalization();
    }
}
