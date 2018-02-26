package linchange.com.awesomeecommerce.generators;

import linchange.com.annotations.PayEntryGenerator;
import linchange.com.core.wechat.template.WXPayEntryTemplate;

/**
 * Created by lkmc2 on 2018/2/26.
 * 微信支付实体接口
 */

@PayEntryGenerator(
        packageName = "linchange.com.awesomeecommerce",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
