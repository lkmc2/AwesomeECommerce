package linchange.com.awesomeecommerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import linchange.com.core.app.Awesome;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Awesome.init(this) //初始化全局配置对象
                .withIcon(new FontAwesomeModule()) //配置图标
                .withApiHost("http://127.0.0.1/") //配置主机地址
                .configure(); //配置完成
    }
}
