package linchange.com.core.util.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by lkmc2 on 2017/12/31.
 * 启动控制器创造者
 */

public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder> {
    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder(); //返回启动控制器
    }
}
