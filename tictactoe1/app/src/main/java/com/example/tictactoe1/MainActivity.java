package com.example.tictactoe1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView counter;
    //0=blue 1=red
    int player=0;
    int[] gamestate = {2,2,2,2,2,2,2,2,2};
    int[][] winningpos={{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};

    public void dropin(View view) {

        counter = (ImageView) view;
        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tappedcounter] == 2) {
            gamestate[tappedcounter]=player;
            counter.setTranslationY(-500f);
            if(player==0)
            {
                counter.setImageResource(R.drawable.bluebutton);
                player=1;
            }
            else
            {
                counter.setImageResource(R.drawable.redbutton);
                player=0;
            }
            counter.animate().translationY(15f).setDuration(300);

        }
        for( int[] pos:winningpos)
        {
            if(winningpos[pos[0]]==winningpos[pos[1]] &&
                    winningpos[pos[1]]==winningpos[pos[2]] &&
                    gamestate[pos[0]]!=2)
            {
                System.out.println(gamestate[pos[0]]);

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
