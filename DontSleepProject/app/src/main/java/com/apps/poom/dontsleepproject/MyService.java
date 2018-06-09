package com.apps.poom.dontsleepproject;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.BatteryManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.EventListener;

public class MyService extends Service implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mTempSensor;
    private static String TAG = "Service";
    public static String BROADCAST_ACTION = "com.apps.poom.dontsleeproject.MainActivity";
    private Handler handler = new Handler();
    Intent intent;
    int counter = 0;


    public MyService() {
    }

    @Override
    public void onCreate(){
        super.onCreate();

        intent = new Intent(BROADCAST_ACTION);

        Toast.makeText(this, "Service Created", Toast.LENGTH_LONG).show();

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mTempSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Service, Started", Toast.LENGTH_LONG).show();

        mSensorManager.registerListener(this, mTempSensor, SensorManager.SENSOR_DELAY_NORMAL);

        return START_NOT_STICKY;
    }


    @Override
    public void onDestroy(){
        super.onDestroy();

        mSensorManager.unregisterListener(this);

        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
