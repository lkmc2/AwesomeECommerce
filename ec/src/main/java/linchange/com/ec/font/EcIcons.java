package linchange.com.ec.font;

import com.joanzapata.iconify.Icon;

/**
 * Created by lkmc2 on 2017/12/27.
 * 图标枚举
 */

public enum EcIcons implements Icon {
    icon_scan('\ue606'), //扫描图标
    icon_ali_pay('\ue606') //支付图标

    ; //枚举类要写java代码需加一行分号

    private char character; //字符

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
