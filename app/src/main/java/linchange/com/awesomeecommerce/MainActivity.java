package linchange.com.awesomeecommerce;

import android.os.TokenWatcher;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import linchange.com.core.app.Awesome;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(Awesome.getApplication(), "传入了Context", Toast.LENGTH_SHORT).show();
    }
}
