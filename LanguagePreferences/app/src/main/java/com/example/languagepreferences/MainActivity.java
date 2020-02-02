package com.example.languagepreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView textView;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch ((item).getItemId())
        {
            case(R.id.setspanish):
                sharedPreferences.edit().putString("language","Spanish" ).apply();
                Toast.makeText(MainActivity.this, "Language Set to Spanish", Toast.LENGTH_SHORT).show();
                textView.setText("Spanish");
                return true;
            case(R.id.setenglish):
                sharedPreferences.edit().putString("language","English" ).apply();
                Toast.makeText(MainActivity.this, "Language Set to English", Toast.LENGTH_SHORT).show();
                textView.setText("English");
                return true;
            default:
                return false;
        }



    }

    String language=" ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textView);
        sharedPreferences=this.getSharedPreferences("com.example.languagepreferences", Context.MODE_PRIVATE);
        if(sharedPreferences.getString("language","") !="")
        {
            //String a=sharedPreferences.getString("language","");
            Log.i("Language",sharedPreferences.getString("language",""));
            textView.setText(sharedPreferences.getString("language",""));
        }
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
