package linchange.com.core.ui.recycler;

import java.util.ArrayList;

/**
 * Created by lkmc2 on 2018/3/5.
 * 数据转换器
 */

public abstract class DataConverter {

    //实体列表
    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();

    //Json数据
    private String mJsonData = null;

    //将数据进行转换
    public abstract ArrayList<MultipleItemEntity> convert();

    //设置json数据
    public DataConverter setJsonData(String jsonData) {
        this.mJsonData = jsonData;
        return this;
    }

    //获取josn数据
    protected String getJonsData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("DATA IS NULL!");
        }
        return mJsonData;
    }
}
