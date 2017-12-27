package linchange.com.awesomeecommerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import linchange.com.core.app.Awesome;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Awesome.init(this)
                .configure();
    }
}
