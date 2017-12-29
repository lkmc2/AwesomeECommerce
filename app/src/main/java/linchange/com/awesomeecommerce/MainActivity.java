package linchange.com.awesomeecommerce;

import linchange.com.core.activities.ProxyActivity;
import linchange.com.core.delegates.AwesomeDelegate;


public class MainActivity extends ProxyActivity {

    //设置根代理
    @Override
    public AwesomeDelegate setRootDelegate() {
        return new ExampleDelegate();
    }

}
