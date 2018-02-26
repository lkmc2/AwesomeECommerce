package linchange.com.awesomeecommerce.generators;

import linchange.com.annotations.EntryGenerator;
import linchange.com.core.wechat.template.WXEntryTemplate;

/**
 * Created by lkmc2 on 2018/2/26.
 * 微信实体接口
 */

@EntryGenerator(
        packageName = "linchange.com.awesomeecommerce",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
