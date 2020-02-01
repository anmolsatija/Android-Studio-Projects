package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.GridLayout;
import androidx.gridlayout.widget.GridLayout; //important;

public class MainActivity extends AppCompatActivity {
    //0=blue 1=red
    int activeplayer=0;
    int  gameisactive=1;
    LinearLayout layout1;
    TextView winnermessage;
    //2 means unplayed
    int[] gamestate={2,2,2,2,2,2,2,2,2} ;
    int[][] winningposition={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropin(View view)
    {

        ImageView counter = (ImageView) view;
        //System.out.println(counter.getTag().toString());
        int  tappedcounter=Integer.parseInt(counter.getTag().toString());

        //if(gameisactive==1) {
           // checkgame check=new checkgame();
            //int a=check.checkgamee();
        //if (a ==1)
        //{
            if (gamestate[tappedcounter] == 2) {
                gamestate[tappedcounter] = activeplayer;
                counter.setTranslationY(-1000f);
                if (activeplayer == 0) {
                    counter.setImageResource(R.drawable.bluebutton);
                    activeplayer = 1;
                } else if (activeplayer == 1) {
                    counter.setImageResource(R.drawable.redbutton);
                    activeplayer = 0;
                }


                counter.animate().translationYBy(1000f).setDuration(300);
                for (int[] pos : winningposition) {
                    gameisactive = 0;
                    if (gamestate[pos[0]] == gamestate[pos[1]]
                            && gamestate[pos[0]] == gamestate[pos[2]]
                            && gamestate[pos[0]] != 2) {

                        String winner = "PLAYER1";

                        if (gamestate[pos[0]] == 1) {
                            winner = "PLAYER2";
                        }
                        winnermessage = (TextView) findViewById(R.id.winnermessage);
                        winnermessage.setText(winner + "has won");
                        Toast.makeText(MainActivity.this, winner + "has won", Toast.LENGTH_SHORT).show();
                        layout1 = (LinearLayout) findViewById(R.id.playagainlay);
                        if (layout1.getVisibility() == View.INVISIBLE) {
                            layout1.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }

        //}
      
    }
    public void play(View view)
    {
        gameisactive=1;
        activeplayer=0;
        layout1.setVisibility(View.INVISIBLE);
        for(int i=0;i<9;i++)
        {
            gamestate[i]=2;
        }

        GridLayout gridlayout=(GridLayout)findViewById(R.id.gridLayout);
        for (int i=0;i<gridlayout.getChildCount();i++)
        {
            ((ImageView) gridlayout.getChildAt(i)).setImageResource(0);


        }
    }
    public class checkgame
    {
        public int checkgamee()
        {
            if (gameisactive==0)
            {
                return 0;
            }
            else
                return 1;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
