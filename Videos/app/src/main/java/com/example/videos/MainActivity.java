package com.example.videos;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VideoView videoview1=  findViewById(R.id.abc);
        String videopath=("android.resource://"+getPackageName()+"/"+R.raw.bcd);
        videoview1.setVideoPath(videopath);
        videoview1.start();
        MediaController mediaController=new MediaController(this);
        mediaController.setAnchorView(videoview1);
        videoview1.setMediaController(mediaController);
        videoview1.start();


    }
}
