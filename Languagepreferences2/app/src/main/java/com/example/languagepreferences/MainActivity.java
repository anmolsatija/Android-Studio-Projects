package com.example.languagepreferences;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Please Select Your Language");
        builder.setMessage("Which language which would you like");
        builder.setPositiveButton("English", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                sharedPreferences.edit().putString("language","English" ).apply();
                Toast.makeText(MainActivity.this, "Language Set to English", Toast.LENGTH_SHORT).show();
                textView.setText("English");
            }
        }).setNeutralButton("Spanish", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                sharedPreferences.edit().putString("language","Spanish").apply();
                Toast.makeText(MainActivity.this,"Language Set to Spanish",Toast.LENGTH_SHORT).show();
                textView.setText("Spanish");
            }
        }); builder.show();
    }
    }
}
