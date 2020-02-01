package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void clickfunction(View view)
    {
        @SuppressLint("WrongViewCast") EditText emailid = (EditText) findViewById(R.id.emailid);
        EditText password =(EditText) findViewById(R.id.password);
        Log.i("emailid",emailid.getText().toString());
        Log.i("password",password.getText().toString());




    }
}
