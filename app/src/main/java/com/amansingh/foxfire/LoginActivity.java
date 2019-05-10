package com.amansingh.foxfire;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.amansingh.foxfire.APICALL.APIClient;
import com.amansingh.foxfire.APICALL.APIInterface;
import com.amansingh.foxfire.APICALL.UserData.UserData;
import com.amansingh.foxfire.Utils.Utils;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 0;
    private final String TAG = "LoginActivity";
    @BindView(R.id.userIdLayout)
    TextInputLayout userIdLayout;
    @BindView(R.id.userPassLayout)
    TextInputLayout userPassLayout;
    @BindView(R.id.button)
    Button button;
    private String uid, pass;
    private String deviceIMEI = "";

    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);

        deviceIMEI = telephonyManager.getDeviceId();
    }

    private void apiCall() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<UserData> call = apiInterface.getUserData(Integer.parseInt(uid));
        call.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(@NonNull Call<UserData> call, @NonNull Response<UserData> response) {
                UserData data = response.body();
                assert data != null;
                String password = data.user_password;
                String imei = data.imei;

                Utils.showLog(TAG, "pass, imei from API ", password + " " + imei);
                Utils.showLog(TAG, "device imei ", deviceIMEI);

                if (pass.equals(password)) {
                    if (imei.equals(""))
                        Utils.setIntent(LoginActivity.this, MainActivity.class);
                    else
                        Utils.showMessage(LoginActivity.this, "Device does not match with user");
                } else
                    Utils.showMessage(LoginActivity.this, "Password not correct");
            }

            @Override
            public void onFailure(@NonNull Call<UserData> call, @NonNull Throwable t) {
                Utils.showLog(TAG, "exception ", t.getMessage());
                Utils.showMessage(LoginActivity.this, "Details did not match with server");
            }
        });
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        Utils.showMessage(LoginActivity.this, "Please Wait....");
        if (checkFields()) {
            apiCall();
        } else {
            Toast.makeText(this, "Please fill both fields", Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkFields() {
        uid = Objects.requireNonNull(userIdLayout.getEditText()).getText().toString();
        pass = Objects.requireNonNull(userPassLayout.getEditText()).getText().toString();
        return !uid.isEmpty() || !pass.isEmpty();
    }
}
