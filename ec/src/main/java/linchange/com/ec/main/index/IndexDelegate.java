package linchange.com.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import linchange.com.core.delegates.bottom.BottomItemDelegate;
import linchange.com.ec.R;

/**
 * Created by lkmc2 on 2018/3/5.
 */

public class IndexDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {

    }
}
