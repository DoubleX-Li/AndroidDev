package com.example.li.learnintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnStartMyAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                // 显式Intent，直接指明要启动的Activity
//                Intent i = new Intent(MainActivity.this, MyAty.class);

                // 隐式Intent，通过在AndroidManifest中配置的action启动Activity
                Intent i = new Intent(MyAty.ACTION);
                startActivity(i);
            }
        });
    }
}

