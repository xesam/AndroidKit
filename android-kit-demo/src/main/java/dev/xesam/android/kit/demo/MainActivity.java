package dev.xesam.android.kit.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import dev.xesam.android.androidkit.demo.R;
import dev.xesam.android.kit.util.NetUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("MainActivity", NetUtils.getNetInfo(this).toString());
    }
}
