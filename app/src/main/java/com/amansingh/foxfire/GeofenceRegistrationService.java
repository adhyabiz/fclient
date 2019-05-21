package com.amansingh.foxfire;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

public class GeofenceRegistrationService extends IntentService {
    private static final String TAG = "GeoIntentService";
    private Context activity;


    public GeofenceRegistrationService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        activity = this;
        if (geofencingEvent.hasError()) {
            Log.e(TAG, "GeofencingEvent error " + geofencingEvent.getErrorCode());
        } else
            {
            int transaction = geofencingEvent.getGeofenceTransition();
            List<Geofence> geofences = geofencingEvent.getTriggeringGeofences();
            Geofence geofence = geofences.get(0);
            if (transaction == Geofence.GEOFENCE_TRANSITION_ENTER && geofence.getRequestId().equals(Constant.GEOFENCE_ID_STAN_UNI)) {
                Log.e(TAG, "You are inside Stanford University");
                Toast.makeText(this, "inside", Toast.LENGTH_SHORT).show();
            } else if (transaction==Geofence.GEOFENCE_TRANSITION_EXIT&&geofence.getRequestId().equals(Constant.GEOFENCE_RADIUS_IN_METERS)){

                Log.e(TAG, "You are outside Stanford University");
                Toast.makeText(this, "outside", Toast.LENGTH_SHORT).show();
            }else {
                Log.e(TAG, "You are outside Stanford University");

            }
        }
    }

    //callbacks interface for communication with service clients!
    public interface Callbacks{
        public void updateClient(long data);
    }


}
