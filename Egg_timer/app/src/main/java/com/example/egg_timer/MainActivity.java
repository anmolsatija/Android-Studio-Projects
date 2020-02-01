package com.example.egg_timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    int seconds;
    int minutes;
    MediaPlayer mediaPlayer,mediaplayer1;
    TextView textView;
    CountDownTimer countDownTimer;
    int a;
    int counter=1;
    Button button;
    public void goo(View view)
    {
        //button.setVisibility(View.INVISIBLE);
        if(counter == 1)
        {
            seekBar.setVisibility(View.INVISIBLE);
            countDownTimer=new CountDownTimer(a * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    counter = 0;
                    button.setText("STOP");
                    int seconds1 = (int) (millisUntilFinished / 1000);
                    int minutes = (int) (seconds1 / 60);
                    int secondsf = (seconds1 - minutes * 60);
                    mediaplayer1.start();
                    String secondstring = String.valueOf(secondsf);
                    if (secondsf <= 9)
                        secondstring = "0" + secondstring;
                    textView.setText(String.valueOf(minutes) + ":" + secondstring);
                    seekBar.setProgress((int) (millisUntilFinished/1000));


                }

                @Override
                public void onFinish() {
                    seekBar.setVisibility(View.VISIBLE);
                    mediaPlayer.start();
                    button.setText("GO!");
                    //button.setVisibility(View.VISIBLE);

                }
            }.start();

        }

        else
        {
            seekBar.setVisibility(View.VISIBLE);
            button.setText("GO!");
            countDownTimer.cancel();

            counter=1;

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar= (SeekBar)findViewById(R.id.seekBar) ;
        textView = (TextView)findViewById(R.id.timertext) ;
        button =(Button)findViewById(R.id.button);
        seekBar.setMax(600);
        seekBar.setProgress(0);
        mediaPlayer = MediaPlayer.create(this,R.raw.horn);
        mediaplayer1=MediaPlayer.create(this,R.raw.ticktock);
        textView.setText("00:00");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                a=progress;
                minutes= (int)(progress/60);
                seconds=progress-minutes*60;
                String secondstring=String.valueOf(seconds);
                if (seconds<=9)
                    secondstring="0"+secondstring;
                textView.setText(String.valueOf(00+minutes)+":"+secondstring);



            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
               // seekBar.setVisibility(View.INVISIBLE);
            }
        });


    }
}
