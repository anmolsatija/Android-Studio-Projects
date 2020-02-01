package com.example.usingsensors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    Sensor accelerometer,gyrometer;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"oncreate:initialising sensor services");
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d(TAG,"oncreate:registered accelerometer listener");


       /* Log.d(TAG,"oncreate :initialising sensor services");
        sensorManager=(SensorManager)getSystemService((Context.SENSOR_SERVICE));
        gyrometer=sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorManager.registerListener(this,gyrometer,SensorManager.SENSOR_DELAY_NORMAL);
        Log.d(TAG,"oncreate registered gyrometer listener");

        */





    }
    /*private void addentry(SensorEvent event)
    {


    }

     */

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
       // addentry(event);
        Log.d(TAG,"On sensor changed : X:"+event.values[0]+"Y:"+event.values[1]+"Z:"+event.values[2]);
        //Log.d("on sensor changed","x:"+event.values[0]+"y:"+event.values[1]+"z:"+event.values[2]);


    }
}

