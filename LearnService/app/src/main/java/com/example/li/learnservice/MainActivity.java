package com.example.li.learnservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(MainActivity.this, MyService.class);

        findViewById(R.id.btnStartService).setOnClickListener(this);

        findViewById(R.id.btnStopService).setOnClickListener(this);

        findViewById(R.id.btnBindService).setOnClickListener(this);

        findViewById(R.id.btnUnbindService).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStartService:
                startService(intent);
                break;
            case R.id.btnStopService:
                stopService(intent);
                break;
            case R.id.btnBindService:
                bindService(intent, this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnbindService:
                unbindService(this);
                break;
        }
    }


    // 服务绑定之后执行
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        System.out.println("Service connnected.");
    }

    // 服务所在进程崩溃或者被杀掉时执行
    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
