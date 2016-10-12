package com.example.li.learnlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout root;
    private Button btnClickMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        setContentView(root);
        for (int i = 0; i < 5; i++)
        {
            btnClickMe = new Button(this);
            btnClickMe.setText("Remove Me");
            btnClickMe.setOnClickListener(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.weight = 1;
            root.addView(btnClickMe, lp);
        }

//        root.addView(btnClickMe);
//        root.addView(btnClickMe, 300, 200);
//        root.addView(btnClickMe, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        root.addView(btnClickMe, lp);
    }

    @Override
    public void onClick(View v) {
        root.removeView(v);
    }
}
