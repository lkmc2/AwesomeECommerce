package linchange.com.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import linchange.com.core.delegates.bottom.BottomItemDelegate;
import linchange.com.ec.R;

/**
 * Created by lkmc2 on 2018/3/5.
 */

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {

    }
}
