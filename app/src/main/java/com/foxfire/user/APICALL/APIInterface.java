package com.foxfire.user.APICALL;

import androidx.annotation.NonNull;

import com.foxfire.user.APICALL.UserData.UserData;
import com.foxfire.user.Notification.MyResponse;
import com.foxfire.user.Notification.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {
    //http://demoadhya.pythonanywhere.com/api/profile/{uid}/
    @GET("profile/{uid}")
    Call<UserData> getUserData(@Path("uid") int id);

    @NonNull
    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAAfOQNgiw:APA91bGlGALlqEMWF4G34NtZqM2N2jXr5xkOmhClCqSrfPXZ3KaLUOW3OSQTqGRGhtA5_zdOt3EahE4hH00WL-bRcFaSS7B7FGwiO4IPiADFTuty9FgFrdT_Jdia68DwINLXMKpqZuAj"
    })
    @POST("fcm/send")
    retrofit2.Call<MyResponse> sendNotification(@Body Sender body);

}
