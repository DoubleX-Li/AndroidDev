package com.example.li.counttime;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputTime;
    private TextView showTime;
    private Button getTime, startCounting, stopCounting;
    private int i = 0;
    private Timer timer = null;
    private TimerTask timerTask = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTime = (EditText) findViewById(R.id.inputTime);
        showTime = (TextView) findViewById(R.id.showTime);
        getTime = (Button) findViewById(R.id.getTime);
        getTime.setOnClickListener(this);
        startCounting = (Button) findViewById(R.id.startCounting);
        startCounting.setOnClickListener(this);
        stopCounting = (Button) findViewById(R.id.stopCounting);
        stopCounting.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.getTime:
                showTime.setText(inputTime.getText().toString());
                i = Integer.parseInt(inputTime.getText().toString());
                break;
            case R.id.startCounting:
                startCounting();
                break;
            case R.id.stopCounting:
                stopCounting();
                break;

        }
    }
    private android.os.Handler mHandler = new android.os.Handler() {
        public void handleMessage(Message msg) {
            showTime.setText(msg.arg1 + "");
            startCounting();
        }
    };

    public void startCounting() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                i--;
                Message msg = mHandler.obtainMessage();
                msg.arg1 = i;
                mHandler.sendMessage(msg);
            }
        };
        timer.schedule(timerTask, 1000);
    }

    public void stopCounting() {
        timer.cancel();
    }

}
