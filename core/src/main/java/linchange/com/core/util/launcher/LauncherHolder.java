package linchange.com.core.util.launcher;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by lkmc2 on 2017/12/31.
 * 启动控制器
 */

public class LauncherHolder implements Holder<Integer> {

    private AppCompatImageView mImageView = null; //图片控件

    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context); //初始化图片控件
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mImageView.setBackgroundResource(data); //给图片控件设置背景
    }
}
