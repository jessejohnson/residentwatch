package com.jessejojojohnson.residentwatch;

import android.app.Application;
import android.location.Location;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

/**
 * Created by odette on 2/6/15.
 */
public class App extends Application {

    public static double longi, lati;
    @Override
    public void onCreate() {
        super.onCreate();

        //start smartlocation service
        SmartLocation.with(getApplicationContext())
                .location().start(new OnLocationUpdatedListener() {
            @Override
            public void onLocationUpdated(Location location) {
                longi = location.getLongitude();
                lati = location.getLatitude();
            }
        });
    }
}
