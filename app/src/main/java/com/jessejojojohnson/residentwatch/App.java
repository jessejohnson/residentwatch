package com.jessejojojohnson.residentwatch;

import android.app.Application;
import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.jessejojojohnson.residentwatch.util.GPSTracker;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;


/**
 * Created by odette on 2/6/15.
 */
public class App extends Application {

    public static double longi, lati;
    GoogleApiClient mGoogleApiClient;
    @Override
    public void onCreate() {
        super.onCreate();

        //start smartlocation service
//        SmartLocation.with(getApplicationContext())
//                .location().start(new OnLocationUpdatedListener() {
//            @Override
//            public void onLocationUpdated(Location location) {
//                longi = location.getLongitude();
//                lati = location.getLatitude();
//            }
//        });

        //set curr location
        GPSTracker gpsTracker = new GPSTracker(getApplicationContext());
        longi = gpsTracker.getLongitude();
        lati = gpsTracker.getLatitude();
    }
}
