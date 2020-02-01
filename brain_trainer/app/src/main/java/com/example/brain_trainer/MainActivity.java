package com.example.brain_trainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.gridlayout.widget.GridLayout;

import java.util.Random;

import static android.widget.Toast.LENGTH_SHORT;


public class MainActivity extends AppCompatActivity {
    CountDownTimer downTimer;
    TextView textView,time,ques,score,finalscore;
    Button button2,button3,button4,button5,goo;
    int answer1,buttonrand,score1=0,quesattempt=-1;



    int start=1;
    public void answer(View view)
    {

        quesattempt++;
        Button counter=(Button) view;
        int tappedcounter=Integer.parseInt(counter.getTag().toString());
        if (tappedcounter==100)
        {
            if(start==0)
                downTimer.cancel();
            start=1;
            finalscore.setText("YOUR FINAL SCORE :00/00");
        }
        if(start==0)
        {
            if (tappedcounter == buttonrand) {
                score1++;
                Log.i("score",String.valueOf(score1)+"/"+String.valueOf(quesattempt));
            }
            score.setText(String.valueOf(score1)+"/"+String.valueOf(quesattempt));


        }

        Random random=new Random();
        if(start==1) {
            score.setText("0/0");
            finalscore.setText("YOUR FINAL SCORE:00/00");

            start = 0;
            downTimer = new CountDownTimer(30000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    int a = (int) (millisUntilFinished / 1000);
                    time.setText(String.valueOf(a));

                }

                @Override
                public void onFinish()
                {
                    finalscore.setText(String.valueOf(score1)+"/"+String.valueOf(quesattempt));
                    start=1;
                    button2.setText("GO!");
                    button3.setText("GO!");
                    button4.setText("GO!");
                    button5.setText("GO!");
                    if(((float)(score1/quesattempt))>0.5)
                        Toast.makeText(getBaseContext(), "GOOD WORK", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getBaseContext(), "WORK HARD", Toast.LENGTH_LONG).show();


                }
            }.start();
        }
        int num1=random.nextInt(99);
        int num2=random.nextInt(99);
        //int rand1,rand2,rand3;
        ques.setText(String.valueOf(num1)+"+"+String.valueOf(num2));
        answer1=num1+num2;
        buttonrand=random.nextInt(4);//need to store buttonrand for score;
        if(buttonrand==0)
        {
            button2.setText(String.valueOf(answer1));
            button3.setText(String.valueOf(answer1+1));
            button4.setText(String.valueOf(answer1-1));
            button5.setText(String.valueOf(answer1+2));
        }
        else if(buttonrand==1)
        {
            button2.setText(String.valueOf(answer1-1));
            button3.setText(String.valueOf(answer1));
            button4.setText(String.valueOf(answer1+2));
            button5.setText(String.valueOf(answer1-2));
        }
        else if(buttonrand==2)
        {
            button2.setText(String.valueOf(answer1-2));
            button3.setText(String.valueOf(answer1-1));
            button4.setText(String.valueOf(answer1));
            button2.setText(String.valueOf(answer1+1));
        }
        else if(buttonrand==3)
        {
            button2.setText(String.valueOf(answer1+1));
            button3.setText(String.valueOf(answer1-1));
            button4.setText(String.valueOf(answer1-2));
            button5.setText(String.valueOf(answer1));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time= (TextView)findViewById(R.id.time);
        ques= (TextView)findViewById(R.id.ques);
        score= (TextView)findViewById(R.id.score);
        finalscore= (TextView)findViewById(R.id.finalscore);
        button2= (Button)findViewById(R.id.button2);
        button3= (Button) findViewById(R.id.button3);
        button4= (Button)findViewById(R.id.button4);
        button5= (Button)findViewById(R.id.button5);
        //goo= (Button)findViewById(R.id.button);






    }
}
