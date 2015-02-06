package com.accessaccra.app.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.jessejojojohnson.residentwatch.util.GPSTracker;

/**
 * Created by bright on 10/18/14.
 */
public class ShowDirection {
    //an instance of the GPSTracker class
    private GPSTracker gps;
    private double latitude, longitude;

    /**
     * receives the context, String of latitude and longitude and get
     * the user's current location and shows the map
     */
    public void showMapToLocation(Context context, String lat, String lon) {
        gps = new GPSTracker(context);
        latitude = gps.getLatitude();
        longitude = gps.getLongitude();
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?   saddr=" + latitude + "," + longitude + "&daddr=" + lat + "," + lon));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        context.startActivity(intent);
    }
}
