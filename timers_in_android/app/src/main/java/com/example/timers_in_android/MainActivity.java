package com.example.timers_in_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //METHOD 1

        final Handler handler=new Handler();//code can be delayed using it ,it controls the timing of the events
        Runnable run1 = new Runnable() {
            @Override
            public void run()
            {
               //insert code to be run every second
                Log.i("Runnable has run","Two second must have past...... ");
                handler.postDelayed(this,10000); //Time delayed to run this command
            }
        };
        handler.post(run1);




        //METHOD 2

/*        new CountDownTimer(10000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                Log.i("Seconds left",String.valueOf(millisUntilFinished/1000));//new method to convert to string
            }

            @Override
            public void onFinish()
            {
                Log.i("Done !","Countdown");
            }
        }.start();
*/

    }
}
