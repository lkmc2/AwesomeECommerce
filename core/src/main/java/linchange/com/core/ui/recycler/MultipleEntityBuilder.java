package linchange.com.core.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * Created by lkmc2 on 2018/3/5.
 * RecycleView子项实体类建造器
 */

public class MultipleEntityBuilder {
    //变量map
    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    public MultipleEntityBuilder() {
        FIELDS.clear(); //清除数据
    }

    //设置子项类型
    public final MultipleEntityBuilder setItemType(int itemType) {
        FIELDS.put(MultipleFields.ITEM_TYPE, itemType);
        return this;
    }

    //设置特定类型
    public final MultipleEntityBuilder setField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

    //将所有类型添加到map
    public final MultipleEntityBuilder setFields(WeakHashMap<Object, Object> map) {
        FIELDS.putAll(map);
        return this;
    }

    public final MultiItemEntity builder() {
        return new MultipleItemEntity(FIELDS);
    }
}
