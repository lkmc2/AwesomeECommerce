package linchange.com.core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import linchange.com.core.app.Awesome;

/**
 * Created by lkmc2 on 2017/12/29.
 * 屏幕尺寸工具类
 */

public class DimenUtil {

    /**
     * 获取屏幕宽度
     * @return 屏幕宽度
     */
    public static int getScreenWidth() {
        final Resources resources = Awesome.getApplicationContext().getResources();  //获取全局资源
        final DisplayMetrics dm = resources.getDisplayMetrics(); //获取显示矩阵
        return dm.widthPixels; //返回屏幕宽
    }

    /**
     * 获取屏幕高度
     * @return 屏幕高度
     */
    public static int getScreenHeight() {
        final Resources resources = Awesome.getApplicationContext().getResources();  //获取全局资源
        final DisplayMetrics dm = resources.getDisplayMetrics(); //获取显示矩阵
        return dm.heightPixels; //返回屏幕高
    }
}
