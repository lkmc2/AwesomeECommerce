package linchange.com.awesomeecommerce.generators;

import linchange.com.annotations.AppRegisterGenerator;
import linchange.com.core.wechat.template.AppRegisterTemplate;

/**
 * Created by lkmc2 on 2018/2/26.
 * app注册接口
 */

@AppRegisterGenerator(
        packageName = "linchange.com.awesomeecommerce",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
