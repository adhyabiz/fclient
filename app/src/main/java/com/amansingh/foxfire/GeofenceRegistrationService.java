package com.amansingh.foxfire;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

public class GeofenceRegistrationService extends IntentService {
    private static final String TAG = "GeoIntentService";


    public GeofenceRegistrationService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        Context activity = this;
        if (geofencingEvent.hasError()) {
            Log.e(TAG, "GeofencingEvent error " + geofencingEvent.getErrorCode());
        } else
            {
            int transaction = geofencingEvent.getGeofenceTransition();
            List<Geofence> geofences = geofencingEvent.getTriggeringGeofences();
            Geofence geofence = geofences.get(0);
            if (transaction == Geofence.GEOFENCE_TRANSITION_EXIT){
                sendMessageToActivity("inside", activity);
            } else {
                sendMessageToActivity("outside", activity);
            }
        }
    }

    //send data to main
    private static void sendMessageToActivity(String msg, Context context) {
        Intent intent = new Intent("Data");
        // You can also include some extra data.
        intent.putExtra("Status", msg);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }


}
