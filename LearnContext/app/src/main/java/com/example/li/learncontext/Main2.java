package com.example.li.learncontext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Main2 extends AppCompatActivity {
    private TextView tv;
    private EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Main Activity onCreate");
        setContentView(R.layout.main2);
        tv = (TextView)findViewById(R.id.textView2);
        et = (EditText)findViewById(R.id.editText2);

        tv.setText("共享的数据是：" + getApp().getTextData());

        findViewById(R.id.btnSaveData2).setOnClickListener(new View.OnClickListener() {
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