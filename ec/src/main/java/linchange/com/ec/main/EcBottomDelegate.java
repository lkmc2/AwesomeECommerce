package linchange.com.ec.main;

import android.graphics.Color;

import java.util.LinkedHashMap;

import linchange.com.core.delegates.bottom.BaseBottomDelegate;
import linchange.com.core.delegates.bottom.BottomItemDelegate;
import linchange.com.core.delegates.bottom.BottomTabBean;
import linchange.com.core.delegates.bottom.ItemBuilder;
import linchange.com.ec.main.index.IndexDelegate;
import linchange.com.ec.main.sort.SortDelegate;

/**
 * Created by lkmc2 on 2018/3/5.
 * 底栏实现类
 */

public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}","分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}","发现"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-user}","我的"), new IndexDelegate());

        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
