package linchange.com.awesomeecommerce;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import linchange.com.core.delegates.AwesomeDelegate;

/**
 * Created by lkmc2 on 2017/12/29.
 * Fragment代理对象
 */

public class ExampleDelegate extends AwesomeDelegate {

    //设置布局
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    //绑定视图
    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {

    }
}
