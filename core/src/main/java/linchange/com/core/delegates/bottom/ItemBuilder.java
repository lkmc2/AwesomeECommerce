package linchange.com.core.delegates.bottom;

import java.util.LinkedHashMap;

/**
 * Created by lkmc2 on 2018/3/5.
 * 底栏子项生成器
 */

public final class ItemBuilder {

    //底栏子项列表
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    //添加底栏子项
    public final ItemBuilder addItem(BottomTabBean bean, BottomItemDelegate delegate) {
        ITEMS.put(bean, delegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean, BottomItemDelegate> items) {
        ITEMS.putAll(items);
        return this;
    }

    //生成底栏列表对象
    public final LinkedHashMap<BottomTabBean, BottomItemDelegate> build() {
        return ITEMS;
    }
}
