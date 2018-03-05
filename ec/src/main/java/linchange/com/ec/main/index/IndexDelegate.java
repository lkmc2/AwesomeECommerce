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

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {

    }
}
