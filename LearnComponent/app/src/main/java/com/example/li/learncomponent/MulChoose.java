package com.example.li.learncomponent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MulChoose extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;

    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mul_choose);

        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
        tv = (TextView) findViewById(R.id.tvResult);

        checkBox1.setOnCheckedChangeListener(this);
        checkBox2.setOnCheckedChangeListener(this);
        checkBox3.setOnCheckedChangeListener(this);
        checkBox4.setOnCheckedChangeListener(this);


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String str = "你喜欢";

        if (checkBox1.isChecked()) {
            str += checkBox1.getText() + ",";
        }
        if (checkBox2.isChecked()) {
            str += checkBox2.getText() + ",";
        }
        if (checkBox3.isChecked()) {
            str += checkBox3.getText() + ",";
        }
        if (checkBox4.isChecked()) {
            str += checkBox4.getText();
        }
        tv.setText(str);
    }
}
