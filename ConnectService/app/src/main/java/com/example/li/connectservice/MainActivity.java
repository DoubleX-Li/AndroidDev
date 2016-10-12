package com.example.li.connectservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {

    private EditText etData;
    private MyService.Binder binder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etData = (EditText)findViewById(R.id.etData);

        findViewById(R.id.btnStartService).setOnClickListener(this);

        findViewById(R.id.btnStopService).setOnClickListener(this);

        findViewById(R.id.btnBindService).setOnClickListener(this);

        findViewById(R.id.btnUnbindService).setOnClickListener(this);

        findViewById(R.id.btnSyncData).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStartService:
                Intent intent = new Intent(MainActivity.this, MyService.class);
                intent.putExtra("data", etData.getText().toString());
                startService(intent);
                break;
            case R.id.btnStopService:
                stopService(new Intent(MainActivity.this, MyService.class));
                break;
            case R.id.btnBindService:
                bindService(new Intent(MainActivity.this, MyService.class), this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnbindService:
                unbindService(this);
                break;
            case R.id.btnSyncData:
                if (binder != null) {
                    binder.setData(etData.getText().toString());
                }
                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        binder = (MyService.Binder)service;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
