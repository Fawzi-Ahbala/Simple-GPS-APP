package com.example.gps_tracking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


public class MainActivity extends AppCompatActivity {
    TextView tv_lat , tv_lon, tv_altitude, tv_accuracy, tv_speed, tv_updates, tv_sensor;
    Switch sw_locationsupdates, sw_gps;

    LocationRequest locationRequest;

    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_lat =findViewById(R.id.tv_lat);
        tv_lon =findViewById(R.id.tv_lon);
        tv_altitude=findViewById(R.id.tv_altitude);
        tv_accuracy=findViewById(R.id.tv_accuracy);
        tv_speed=findViewById(R.id.tv_speed);
        tv_updates=findViewById(R.id.tv_updates);
        tv_sensor=findViewById(R.id.tv_sensor);
        sw_locationsupdates=findViewById(R.id.sw_locationsupdates);
        sw_gps=findViewById(R.id.sw_gps);

        locationRequest = new LocationRequest();
        locationRequest.setFastestInterval(DEFAULT_UPDATE_INTERVAL() *1000);
        locationRequest.setFastestInterval(FAST_UPDATE_INTERVAL() *1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        sw_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sw_gps.isChecked()){
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    tv_sensor.setText("Using GPS sensors");
                }else{
                    locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                    tv_sensor.setText("Using Towers + WIFI");
                }


            }
        });
    }
    private void updateGPS() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {

                }
            }


        }

        }


    }

    private int FAST_UPDATE_INTERVAL() {
        return 50;
    }

    private int DEFAULT_UPDATE_INTERVAL() {
        return 30;
    }
}