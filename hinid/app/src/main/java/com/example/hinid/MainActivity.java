package com.example.hinid;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp;

    public void speak(View view)
    {
        Log.i("buttontapped",view.getTag().toString());
        int tag= Integer.parseInt((String) view.getTag());
        int id=view.getId();
        String ourid="";
        ourid=view.getResources().getResourceEntryName(id);
        int resouceid=getResources().getIdentifier(ourid,"raw","com.example.hinid");
        mp= MediaPlayer.create(this,resouceid);
        mp.start();

        /*if (tag==1)
        {
            mp=MediaPlayer.create(this,R.raw.namaste);
        }
        else if(tag==2)
        {
            mp=MediaPlayer.create(this,R.raw.badhaiho);
        }
        else if(tag==3)
        {
            mp=MediaPlayer.create(this,R.raw.bathroomkhahai);
        }

        else if(tag==4)
        {
            mp=MediaPlayer.create(this,R.raw.bachao);
        }

        else if(tag==5)
        {
            mp=MediaPlayer.create(this,R.raw.kyaaapkepaashai);
        }
        else
        {
            mp=MediaPlayer.create(this,R.raw.khardkhardkhard);
        }

        mp.start();
        */

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
