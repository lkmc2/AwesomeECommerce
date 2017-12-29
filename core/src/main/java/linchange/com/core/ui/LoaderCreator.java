package linchange.com.core.ui;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by lkmc2 on 2017/12/29.
 * 进度加载器生成者
 */

public final class LoaderCreator {
    //进度加载器存储Map
    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    /**
     * 创建加载视图
     * @param type 类型名
     * @param context 上下文
     * @return 加载视图界面
     */
    static AVLoadingIndicatorView create(String type, Context context) {
        //新建加载视图指示器
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);

        if (LOADING_MAP.get(type) == null) { //进度加载器Map未有该类型的缓存
            final Indicator indicator = getIndicator(type); //获取指示器
            LOADING_MAP.put(type, indicator); //将该指示器添加到Map
        }

        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type)); //设置加载视图的指示器
        return avLoadingIndicatorView; //返回加载视图
    }

    /**
     * 获取指示器
     * @param name 指示器名
     * @return 指示器名对应的指示器
     */
    private static Indicator getIndicator(String name) {
        if (name == null || name.isEmpty()) { //指示器名非空
            return null;
        }

        //图片对象类名
        final StringBuilder drawableClassName = new StringBuilder();
        if (!name.contains(".")) { //类名不包括逗号
            //获取默认包名
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackageName)
                    .append(".indicators.");
        }
        drawableClassName.append(name); //拼接图片对象类名

        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance(); //生成图片对象类实例
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
