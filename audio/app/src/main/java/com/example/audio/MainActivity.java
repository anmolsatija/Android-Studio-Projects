package com.example.audio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    public void play(View view)
    {
        //mediaPlayer.seekTo();
        mediaPlayer.start();
    }
    public void pause(View view)
    {
        mediaPlayer.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* MediaPlayer mediaPlayer= MediaPlayer.create(this,R.raw.getlow);
        mediaPlayer.start();
        MediaController mediaController=new MediaController(this);
        mediaPlayer.pause();*/

        mediaPlayer=MediaPlayer.create(this,R.raw.getlow);
        audioManager= (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxvolume=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curvolume=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar volumecontrol=(SeekBar)findViewById(R.id.seekBar);
        SeekBar seek=(SeekBar)findViewById(R.id.seekBar2);
        volumecontrol.setMax(maxvolume);
        seek.setMax(mediaPlayer.getDuration());

        volumecontrol.setProgress(curvolume);

        volumecontrol.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Log.i("seekbarvalue",Integer.toString(progress));
                //float a= progress;
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                Log.i("blah",Integer.toString(progress));
                mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                mediaPlayer.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                mediaPlayer.start();
            }
        });




    }

    @Override
    protected void onPause()
    {
        //When app closes song also closes with this fucntion
        super.onPause();
        mediaPlayer.release();
    }
}
