package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public void clickfunc(View view)
    {
        EditText emailid = (EditText) findViewById(R.id.emailid);
        EditText password = (EditText) findViewById(R.id.password);
        Log.i("username",emailid.getText().toString());
        Log.i("password",password.getText().toString());
        Toast.makeText(MainActivity.this,"fuck you bitch",Toast.LENGTH_LONG).show();
        Toast.makeText(MainActivity.this,password.getText().toString(),Toast.LENGTH_LONG).show();


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
