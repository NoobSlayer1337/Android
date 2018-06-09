package com.apps.poom.dontsleepproject;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {


    TextView temp;
    TextView power;
    Button infoButton;

    String NOTI_CHANNEL = "";
    PowerManager powerManager;

    private BroadcastReceiver mReciever;
    private IntentFilter mIntent;
    Sensor temper;
    SensorManager sm;
    private Intent intent;

    private static final String TAG = "MainActivity";


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            powerManager = (PowerManager) getSystemService(POWER_SERVICE);
            temp = (TextView) findViewById(R.id.Temperature);
            power = (TextView)findViewById(R.id.CurrentPower);

//            sm = (SensorManager)getSystemService(SENSOR_SERVICE);
//            sm.registerListener(this, temper, SensorManager.SENSOR_DELAY_NORMAL);
            intent = new Intent(this, MyService.class);
        }

        private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String type = intent.getStringExtra("message");
                sm = (SensorManager)getSystemService(SENSOR_SERVICE);
                temper = sm.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
                Log.d(TAG, "Status: " +type);
                if (type == "1"){
                    Toast.makeText(getApplication(), "Lock", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplication(), "UnLock", Toast.LENGTH_LONG).show();
                }
            }
        };

        public void startService(View view) {

            startService(new Intent(getBaseContext(), MyService.class));
            //create remote view
            RemoteViews collapsedView = new RemoteViews(getPackageName(),
                    R.layout.service_bar);
            RemoteViews expandedView = new RemoteViews(getPackageName(),
                    R.layout.service_extended);
            //intent for start click
            Intent startIntent = new Intent(this, ButtonController.class);
            startIntent.setAction("stop");
            expandedView.setOnClickPendingIntent(R.id.left_button,
                    PendingIntent.getService(this, 0, startIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT));
//action for stop click
            Intent stopIntent = new Intent(this, ButtonController.class);
            stopIntent.setAction("start");
            expandedView.setOnClickPendingIntent(R.id.right_button,
                    PendingIntent.getService(this, 1, stopIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT));

            //Intent
            Intent mainIntent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mainIntent, PendingIntent.FLAG_CANCEL_CURRENT);

            startService(new Intent(getBaseContext(), MyService.class));
            NotificationChannel channel = new NotificationChannel(NOTI_CHANNEL, "Music Channel", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTI_CHANNEL)
                    .setSmallIcon(R.drawable.android_bat)
                    .setContentIntent(pendingIntent)
                    .setCustomContentView(collapsedView)
                    .setCustomBigContentView(expandedView);
            //Notification
            Notification notification = builder.build();
            NotificationManager notificationManager = (android.app.NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
            notificationManager.notify(0, notification);

            notification.flags = Notification.DEFAULT_LIGHTS | Notification.FLAG_AUTO_CANCEL;

            LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(broadcastReceiver, new IntentFilter("NOW"));

        }

        // Method to stop the service
        public void stopService(View view) {
            stopService(new Intent(getBaseContext(), MyService.class));
            super.onPause();
            unregisterReceiver(broadcastReceiver);
        }
}