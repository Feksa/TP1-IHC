package com.example.atividades1_2_3_pt2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.Manifest;

public class MainActivity extends AppCompatActivity implements SensorEventListener
{

    private SensorManager mSensorManager;
    private Sensor mLightSensor;
    private Sensor mTemperatureSensor;
    private Sensor mLatLongSensor;

    private EditText lightValue;
    private EditText temperatureValue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mLightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mTemperatureSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        lightValue = (EditText) findViewById(R.id.editTextLight);
        temperatureValue = (EditText) findViewById(R.id.editTextTemp);

        if(mLightSensor != null)
        {
            mSensorManager.registerListener(MainActivity.this, mLightSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }else
        {
            lightValue.setText(String.valueOf("Light sensor not supported"));
        }

        if(mTemperatureSensor != null)
        {
            mSensorManager.registerListener(MainActivity.this, mTemperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }else
        {
            temperatureValue.setText(String.valueOf("Temperature sensor not supported"));
        }

        Button GPSBtn = (Button) findViewById(R.id.GPSbtn);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        GPSBtn.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        GPSTracker gps = new GPSTracker(getApplicationContext());
                        Location loc = gps.getLocation();
                        if(loc!=null)
                        {
                            double lat = loc.getLatitude();
                            double longi = loc.getLongitude();
                            Toast.makeText(getApplicationContext(), "LAT: " + lat + "LONG: " + longi, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mSensorManager.registerListener(this, mLightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mTemperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if(event.sensor.getType()== Sensor.TYPE_LIGHT) {
            lightValue.setText(String.valueOf(event.values[0] + " Lux"));
        }

        if(event.sensor.getType()== Sensor.TYPE_AMBIENT_TEMPERATURE) {
            temperatureValue.setText(String.valueOf(event.values[0] + " °C"));
        }

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }
}