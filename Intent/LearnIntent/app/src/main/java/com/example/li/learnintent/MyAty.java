package com.example.li.learnintent;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Li on 2016/9/10 0010.
 */
public class MyAty extends Activity{

    public static final String ACTION = "com.example.li.learnintent.intent.action.MyAty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myaty);
    }
}
