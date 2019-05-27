package com.amansingh.foxfire;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;
import java.util.Objects;

public class GeofenceRegistrationService extends IntentService {
    private static final String TAG = "GeoIntentService";


    public GeofenceRegistrationService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e(TAG, "onHandleIntent: inside onHandle");
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        Context activity = this;
        if (Objects.requireNonNull(geofencingEvent).hasError()) {
            Log.e(TAG, "GeofencingEvent error " + geofencingEvent.getErrorCode());
        } else {
            Log.e(TAG, "onHandleIntent: entered geofencing ");
            int transaction = geofencingEvent.getGeofenceTransition();
            List<Geofence> geofences = geofencingEvent.getTriggeringGeofences();
            Geofence geofence = geofences.get(0);

            if (transaction == geofence.GEOFENCE_TRANSITION_EXIT) {
                Log.e(TAG, "onHandleIntent: geo fencing exit");
                sendMessageToActivity("outside", activity);
            } else {
                Log.e(TAG, "onHandleIntent: geo fencing enter");
                sendMessageToActivity("inside", activity);
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
