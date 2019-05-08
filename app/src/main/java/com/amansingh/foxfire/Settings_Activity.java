package com.amansingh.foxfire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

//import com.techacademy.demomaps.R;

public class Settings_Activity extends AppCompatActivity {
    private EditText editText,editText1;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_);

        editText=findViewById(R.id.editText);
        editText1=findViewById(R.id.editText1);
        button=findViewById(R.id.button);
    }
}
