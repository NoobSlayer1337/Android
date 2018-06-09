package com.apps.poom.dontsleepproject;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;

public class ButtonController extends IntentService {

    public ButtonController() {
        super("NotificationServiceIntent");
        startService(new Intent(getBaseContext(), MyService.class));

    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        switch (intent.getAction()) {
            case "stop":
                Handler leftHandler = new Handler(Looper.getMainLooper());
                leftHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        startService(new Intent(getBaseContext(), MyService.class));
                    }
                });
                break;
            case "start":
                Handler rightHandler = new Handler(Looper.getMainLooper());
                rightHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        stopService(new Intent(getBaseContext(), MyService.class));
                    }
                });
                break;
        }
    }
}
