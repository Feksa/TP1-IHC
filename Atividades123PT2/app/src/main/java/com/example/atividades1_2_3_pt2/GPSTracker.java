package com.example.atividades1_2_3_pt2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class GPSTracker implements LocationListener {
    Context context;
    public GPSTracker(Context c)
    {
        context = c;
    }

    public Location getLocation()
    {
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(context, "Permissão à localização negada", Toast.LENGTH_LONG).show();
            return null;
        }
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if(isGPSEnabled)
        {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
            Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return loc;
        }
        else
        {
            Toast.makeText(context, "Por favor, habilite a localização", Toast.LENGTH_LONG).show();
        }
        return null;
    }


    @Override
    public void onLocationChanged(Location location) {
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
    @Override
    public void onProviderEnabled(String provider) {
    }
    @Override
    public void onProviderDisabled(String provider) {
    }

}
