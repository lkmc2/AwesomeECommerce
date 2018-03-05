package linchange.com.core.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import linchange.com.core.R;
import linchange.com.core.R2;
import linchange.com.core.delegates.AwesomeDelegate;
import me.yokeyword.fragmentation.ISupportFragment;

/**
 * Created by lkmc2 on 2018/2/27.
 * 基础底部代理界面
 */

public abstract class BaseBottomDelegate extends AwesomeDelegate implements View.OnClickListener {
    //Tab bean子项
    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    //代理Tab子项
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();

    //代理Tab和 Tab bean的map
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    private int mCurrentDelegate = 0; //当前激活的Tab
    private int mIndexDelegate = 0; //初始激活Tab
    private int mClickedColor = Color.RED; //激活Tab后的颜色

    //设置item
    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    public abstract int setIndexDelegate(); //设置代理界面的下标

    @ColorInt
    public abstract int setClickedColor(); //设置点击Tab时的颜色

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar = null; //底栏

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mIndexDelegate = setIndexDelegate(); //设置代理界面的下标
        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor(); //激活Tab后的颜色
        }

        final ItemBuilder builder = ItemBuilder.builder(); //子项生成器
        //设置子项到builder中
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items); //将生成的子项加入子项列表中

        for (Map.Entry<BottomTabBean, BottomItemDelegate> item : ITEMS.entrySet()) {
            final BottomTabBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();

            TAB_BEANS.add(key); //将key加入对应的map
            ITEM_DELEGATES.add(value); //将value加入对应的map
        }
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {
        final int size = ITEMS.size();

        for (int i = 0; i < size; i++) {
            //将子项布局加载到mBottomBar上
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            //设置每个item的点击事件
            item.setTag(i); //设置tag
            item.setOnClickListener(this); //设置点击事件

            //底栏图标和文字
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);

            final BottomTabBean bean = TAB_BEANS.get(i); //获取底栏的bean数据
            itemIcon.setText(bean.getIcon()); //设置Tab图标
            itemTitle.setText(bean.getTitle()); //设置Tab文字

            if (i == mIndexDelegate) { //设置被点击的Tab的颜色
                itemIcon.setTextColor(mClickedColor);
                itemTitle.setTextColor(mClickedColor);
            }
        }

        //将map转换成数组
        final ISupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new ISupportFragment[size]);
        //加载根布局
        getSupportDelegate().loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, delegateArray);
    }

    private void resetColor() { //重置Tab颜色
        final int count = mBottomBar.getChildCount();
        for (int i = 0; i < count; i++) {
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            //给Tab图标设置颜色
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            itemIcon.setTextColor(Color.GRAY);
            //给Tab图标设置颜色
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemTitle.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onClick(View v) {
        final int tag = (int) v.getTag(); //获取Tag
        resetColor(); //重置颜色
        final RelativeLayout item = (RelativeLayout) v;
        //给Tab图标设置颜色
        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        itemIcon.setTextColor(mClickedColor);
        //给Tab图标设置颜色
        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mClickedColor);

        //显示一个Fragment并隐藏一个Fragment
        getSupportDelegate().showHideFragment(ITEM_DELEGATES.get(tag), ITEM_DELEGATES.get(mCurrentDelegate));

        mCurrentDelegate = tag; //设置当前界面下标
    }
}
