package com.example.li.learncontext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Main2 Activity onCreate");
//        tv = new TextView(MainActivity.this);
//        // TextView的构造方法中至少要传入一个Context，表明TextView需要访问全局信息
//        // MainActivity继承自Context，表明MainActivity有访问全局信息的能力
//
//        tv.setText(R.string.helloandroid);
//        ImageView iv = new ImageView(MainActivity.this);
//        iv.setImageResource(R.mipmap.ic_launcher);
        setContentView(R.layout.main1);

        tv = (TextView)findViewById(R.id.textView1);
        et = (EditText)findViewById(R.id.editText1);

        tv.setText("共享的数据是：" + getApp().getTextData());

        findViewById(R.id.btnSaveData1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((App)getApplicationContext()).setTextData(et.getText().toString());
                tv.setText("共享的数据是："+et.getText().toString());
            }
        });
    }

    public App getApp() {
        return (App)getApplicationContext();
    }
}
