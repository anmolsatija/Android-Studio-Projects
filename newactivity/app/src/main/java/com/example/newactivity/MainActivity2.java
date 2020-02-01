package com.example.newactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    public void startactivity1(View view)
    {
        //Jumping to new activity
        Intent intent=new Intent(this.getApplicationContext(),MainActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent=getIntent();

        Toast.makeText(this,"hi "+intent.getStringExtra("name"),Toast.LENGTH_SHORT).show();
    }
}
