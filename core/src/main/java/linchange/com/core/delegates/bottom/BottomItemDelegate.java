package linchange.com.core.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import linchange.com.core.delegates.AwesomeDelegate;

/**
 * Created by lkmc2 on 2018/2/27.
 * 底部按钮子项界面
 */

public abstract class BottomItemDelegate
                            extends AwesomeDelegate
                            implements View.OnKeyListener{

    private long mExitTime = 0; //记录上次点击返回键的时间
    private static final int EXIT_TIME = 2000; //退出等待时间

    @Override
    public void onResume() { //此处的作用是防止第二次进入Fragment点击事件不生效
        super.onResume();
        final View rootView = getView();
        if (rootView != null) {
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus(); //获取焦点
            rootView.setOnKeyListener(this); //设置键盘点击事件
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) { //按下返回键
            if ((System.currentTimeMillis() - mExitTime) > EXIT_TIME) { //超过两秒按下两次返回键
                Toast.makeText(getContext(), "双击退出" + getString(com.wang.avi.R.string.app_name), Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis(); //记录点击返回键时间
            } else { //两秒内按下两次返回键
                _mActivity.finish(); //结束应用
                if (mExitTime != 0) {
                    mExitTime = 0;
                }
            }
            return true; //本view处理该事件
        }
        return false;
    }
}
