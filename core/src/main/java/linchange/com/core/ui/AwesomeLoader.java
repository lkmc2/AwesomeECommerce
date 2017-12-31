package linchange.com.core.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import linchange.com.core.R;
import linchange.com.core.util.dimen.DimenUtil;

/**
 * Created by lkmc2 on 2017/12/29.
 * 全局进度加载器
 */

public class AwesomeLoader {

    private static final int LOADER_SIZE_SCALE = 8; //屏幕缩放比
    private static final int LOADER_OFFSET_SCALE = 10; //进度加载器界面偏移量

    //默认加载器样式
    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>(); //加载器列表

    /**
     * 展示进度加载器
     * @param context 上下文
     * @param type 进度条类型（枚举）
     */
    public static void showLoading(Context context, Enum<LoaderStyle> type) {
        showLoading(context, type.name()); //展示进度加载器
    }

    /**
     * 展示进度加载器
     * @param context 上下文
     * @param type 进度条类型
     */
    public static void showLoading(Context context, String type) {
        //初始化界面对话框
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        //创建进度加载指示器
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);

        //给对话框设置显示内容为进度加载指示器
        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth = DimenUtil.getScreenWidth(); //获取屏幕宽
        int deviceHeight = DimenUtil.getScreenHeight(); //获取屏幕高

        final Window dialogWindow = dialog.getWindow(); //获取当前Activity的窗口

        if (dialogWindow != null) { //对话框窗口非空
            WindowManager.LayoutParams lp = dialogWindow.getAttributes(); //获取屏幕参数
            lp.width = deviceWidth / LOADER_SIZE_SCALE; //设置参数的宽
            lp.height = deviceHeight / LOADER_SIZE_SCALE; //设置参数的高
            lp.height = lp.height + deviceHeight / LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER; //居中对齐
        }

        LOADERS.add(dialog); //添加对话框到加载器列表
        dialog.show(); //展示对话框
    }

    /**
     * 显示进度加载器
     * @param context 上下文
     */
    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }

    /**
     * 停止进度加载器的显示
     */
    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) { //遍历进度加载器列表
            if (dialog != null) { //对话框非空
                if (dialog.isShowing()) { //对话框正在显示
                    dialog.cancel(); //取消对话框显示
                }
            }
        }
    }
}
