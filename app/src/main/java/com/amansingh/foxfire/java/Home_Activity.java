package com.amansingh.foxfire.java;

import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amansingh.foxfire.R;
import com.amansingh.foxfire.directionhelper.FetchURL;
import com.amansingh.foxfire.directionhelper.TaskLoadedCallback;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class Home_Activity extends AppCompatActivity implements OnMapReadyCallback, TaskLoadedCallback {

    private GoogleMap mMap;

    private MarkerOptions place1, place2;

    private Button getDirection;

    private Polyline currentPolyline;
    private Point point;
    String url;


    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        getDirection = findViewById(R.id.btnGetDirection);
        getDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = getUrl(place1.getPosition(), place2.getPosition(), "driving");
                new FetchURL(Home_Activity.this).execute(url, "driving");
            }

        });

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapNearBy);
        mapFragment.getMapAsync(this);

        //27.658143,85.3199503

        //27.667491,85.3208583

        place1 = new MarkerOptions().position(new LatLng(27.658143, 85.3199503)).title("Location 1");

        place2 = new MarkerOptions().position(new LatLng(27.667491, 85.3208583)).title("Location 2");


    }


    @Override

    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

//        Log.d("mylog", "Added Markers");

        mMap.addMarker(place1);

        mMap.addMarker(place2);

    }


    private String getUrl(LatLng origin, LatLng dest, String directionMode) {

        // Origin of route

        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route

        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Mode

        String mode = "mode=" + directionMode;

        // Building the parameters to the web service

        String parameters = str_origin + "&" + str_dest + "&" + mode;

        // Output format

        String output = "json";

        // Building the url to the web service

        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);

        return url;

    }


    @Override

    public void onTaskDone(Object... values) {

        if (currentPolyline != null)

            currentPolyline.remove();

        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);

    }

//   mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//
//        @Override
//        public void onMapClick(LatLng point) {
//            Toast.makeText(getApplicationContext(), point.toString(), Toast.LENGTH_SHORT).show();
//        }
//    };
}