package linchange.com.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import linchange.com.core.delegates.bottom.BottomItemDelegate;
import linchange.com.core.ui.refresh.RefreshHandler;
import linchange.com.ec.R;
import linchange.com.ec.R2;

/**
 * Created by lkmc2 on 2018/3/5.
 * 首页Fragment界面
 */

public class IndexDelegate extends BottomItemDelegate {

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null; //列表控件

    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null; //下拉刷新布局

    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null; //顶部工具栏

    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null; //扫描图标

    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null; //搜索框

    private RefreshHandler mRefreshHandler = null; //刷新控制器

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {
        mRefreshHandler = new RefreshHandler(mRefreshLayout); //给刷新布局绑定控制器
    }

    private void initRefreshLayout() { //初始化下拉刷新布局
        mRefreshLayout.setColorSchemeResources( //设置刷新提示球颜色
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        //设置刷新球位置偏移
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        initRefreshLayout(); //初始化下拉刷新布局
        mRefreshHandler.firstPage("test.json"); //获取第一页数据
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }


}
