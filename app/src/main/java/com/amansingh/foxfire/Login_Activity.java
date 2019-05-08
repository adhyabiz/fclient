package com.amansingh.foxfire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amansingh.foxfire.java.Home_Activity;
//import com.techacademy.demomaps.R;

public class Login_Activity extends AppCompatActivity {
    private EditText editText,editText1;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        editText=findViewById(R.id.editText);
        editText1=findViewById(R.id.editText1);
        button=findViewById(R.id.button);


//        public void showMyLocation(View view) {
//
//            startActivity(new Intent(getApplicationContext(),Home.class));
//
//        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login_Activity.this,Home_Activity.class);
                startActivity(intent);
            }
        });
    }

}
