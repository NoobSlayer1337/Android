//package com.apps.poom.dontsleepproject;
//
//import android.app.Activity;
//import android.content.Context;
//import android.hardware.Sensor;
//import android.hardware.SensorEvent;
//import android.hardware.SensorEventListener;
//import android.hardware.SensorManager;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.TextView;
//
//import java.util.List;
//
//public class SensorActivity extends Activity implements SensorEventListener {
//    private SensorManager mSensorManager;
//    private Sensor mPressure;
//    private Sensor mTemp;
//    TextView temperature = null;
//
//    @Override
//    public final void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
////        mPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
//        temperature.setVisibility(View.GONE);
//
//        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        List<Sensor> mList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
//
//        for (int i = 1; i < mList.size(); i++) {
//            temperature.setVisibility(View.VISIBLE);
//            temperature.append("\n" + mList.get(i).getName() + "\n" + mList.get(i).getVendor() + "\n" + mList.get(i).getVersion());
//        }
//    }
//    public SensorActivity() {
////        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
////        mTemp = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
//    }
//
//    @Override
//    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
//    }
//
//    @Override
//    public final void onSensorChanged(SensorEvent event) {
//        float millibars_of_pressure = event.values[0];
//    }
//
//    @Override
//    protected void onResume() {
//        // Register a listener for the sensor.
//        super.onResume();
//        mSensorManager.registerListener(this, mTemp, SensorManager.SENSOR_DELAY_NORMAL);
////        temperature.setText(Sensor.STRING_TYPE_AMBIENT_TEMPERATURE);
//    }
//
//    @Override
//    protected void onPause() {
//        // Be sure to unregister the sensor when the activity pauses.
//        super.onPause();
//        mSensorManager.unregisterListener(this);
//    }
//
//}
