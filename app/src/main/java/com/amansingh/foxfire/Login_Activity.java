package com.amansingh.foxfire;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

//import com.amansingh.foxfire.java.Home_Activity;
//import com.techacademy.demomaps.R;

public class Login_Activity extends AppCompatActivity {
    private EditText editText,editText1;
    private Button button;
    String url;
    ProgressDialog progressDialog;
    ArrayList<List> listArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        editText=findViewById(R.id.editText);
        editText1=findViewById(R.id.editText1);
        button=findViewById(R.id.button);

        url="http://demoadhya.pythonanywhere.com/api/profile/"+editText.getText();
        progressDialog.setMessage("wait.....");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        editText.setText(jsonObject.getString("name"));
                        editText1.setText(jsonObject.getString("user_password"));
//                        Toast.makeText(Login_Activity.this, ""+response, Toast.LENGTH_SHORT).show();
//                        List list=new List();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login_Activity.this, "singh", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login_Activity.this,Settings_Activity.class);
                startActivity(intent);
            }
        });
    }

}
