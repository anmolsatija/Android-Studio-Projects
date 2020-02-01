package com.example.animations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void fade(View view)
    {
        ImageView monica=(ImageView) findViewById(R.id.monica);

       // monica.animate().alpha(0f).setDuration(2000); //for @fading image
       // monica.animate().translationY(-1000f).setDuration(2000);
       // monica.animate().scaleX(0.5f).scaleY(0.5f).setDuration(2000);//for shrinking image
       // monica.animate().rotation(3600f).setDuration(2000); //for rotating the image
       // monica.animate().scaleX(0.5f).scaleY(0.5f).setDuration(2000);
       // monica.animate().scaleX(2f).scaleY(2f).setDuration(20000);//for zooming in the image
        monica.animate().scaleX(0.5f)
                .scaleY(0.5f)
                .translationYBy(-2000f)
                .rotation(3600f)
                .setDuration(2000);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView monica=(ImageView) findViewById(R.id.monica);
        monica.setTranslationY(1000f);// to set image to some translation in runtime .

    }
}
