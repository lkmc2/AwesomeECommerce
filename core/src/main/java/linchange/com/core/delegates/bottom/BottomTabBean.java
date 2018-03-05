package linchange.com.core.delegates.bottom;

/**
 * Created by lkmc2 on 2018/3/5.
 * 底栏子项
 */

public final class BottomTabBean {

    private final CharSequence ICON; //图标
    private final CharSequence TITLE; //标题

    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }

}
