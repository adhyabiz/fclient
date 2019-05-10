package com.amansingh.foxfire.APICALL;

import com.amansingh.foxfire.APICALL.UserData.UserData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {
    //http://demoadhya.pythonanywhere.com/api/profile/{uid}/
    @GET("profile/{uid}")
    Call<UserData> getUserData(@Path("uid") int id);
}
