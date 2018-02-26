package linchange.com.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by lkmc2 on 2018/2/26.
 * 实体生成器
 */

@Target(ElementType.TYPE) //声明应用在类上
@Retention(RetentionPolicy.SOURCE) //源码阶段处理，运行时或打包成apk就不再使用该类
public @interface EntryGenerator {

    String packageName(); //包名
    Class<?> entryTemplate(); //值

}
