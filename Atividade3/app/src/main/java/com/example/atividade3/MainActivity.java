package com.example.atividade3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements SensorEventListener
{

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private EditText X;
    private EditText Y;
    private EditText Z;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        X = (EditText) findViewById(R.id.editTextText);
        Y = (EditText) findViewById(R.id.editTextText1);
        Z = (EditText) findViewById(R.id.editTextText2);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()== Sensor.TYPE_ACCELEROMETER) {
            float sensorX = event.values[0];
            float sensorY = event.values[1];
            float sensorZ = event.values[2];
            X.setText(String.valueOf(sensorX));
            Y.setText(String.valueOf(sensorY));
            Z.setText(String.valueOf(sensorZ));

            if(sensorY<0 && sensorZ<0)
            {
                launchSecondActivity();
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }

    public void launchSecondActivity()
    {
        Intent intent = new Intent(this, Activity2.class);

        startActivity(intent);
    }

}