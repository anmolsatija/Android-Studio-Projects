package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences =this.getSharedPreferences("com.example.sharedpreferences;", Context.MODE_PRIVATE);
        /*sharedPreferences.edit().putString("username","anmol").apply();
        String usernmae=sharedPreferences.getString("username","");
        Log.i("Username",usernmae);

         */
        ArrayList<String> arrayList=new ArrayList<String>();
        arrayList.add("chandler");
        arrayList.add("monica");
        try {
            sharedPreferences.edit().putString("friends",ObjectSerializer.serialize(arrayList)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> friends=new ArrayList<String>();
        try {
            friends= (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends",ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("frieds",friends.toString());
    }
}
