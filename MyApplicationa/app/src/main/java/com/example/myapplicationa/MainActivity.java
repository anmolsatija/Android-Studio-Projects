package com.example.myapplicationa;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public void newimage(View view)
    {
        ImageView image = (ImageView) findViewById(R.id.image);

        image.setImageResource(R.drawable.abc);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
