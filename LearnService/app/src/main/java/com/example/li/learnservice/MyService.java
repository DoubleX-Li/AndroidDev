package com.example.li.learnservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {

    private boolean serviceRunning = false;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        System.out.println("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("Service onCreate");

        serviceRunning = true;
        new Thread(){
            @Override
            public void run() {
                super.run();

                while (serviceRunning)
                {
                    System.out.println("服务正在运行");

                    try {
                        sleep(1000);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Service onDestory");
        serviceRunning = false;
    }
}
