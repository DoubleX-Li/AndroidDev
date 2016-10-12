package com.example.li.androidactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
          setContentView(R.layout.my_layout);
          findViewById(R.id.btnStartAnotherAty).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  startActivity(new Intent(MainActivity.this, AnotherAty.class));
//                  startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/ncr")));
              }
          });
    }
}
