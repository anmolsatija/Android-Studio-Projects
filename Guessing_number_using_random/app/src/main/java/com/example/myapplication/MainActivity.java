package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int rnd1;
    public void SUBMIT(View view)
    {

    EditText guess =(EditText) findViewById(R.id.editText);
    int guessdouble= (int) Integer.parseInt(guess.getText().toString());
        if(guessdouble==rnd1)
        {
            Log.i("correct","good work");
            Toast.makeText(MainActivity.this,"Guessed correct",Toast.LENGTH_SHORT).show();
            Random rnd = new Random();
            rnd1 = rnd.nextInt(11);
        }
        else
        {
            Log.i("incorrect","try again");
            Toast.makeText(MainActivity.this,"guessed wrong guess again",Toast.LENGTH_SHORT).show();
        }
        if(guessdouble>rnd1)
        {
            Toast.makeText(MainActivity.this,"higher",Toast.LENGTH_SHORT).show();
        }
        else if(guessdouble<rnd1)
        {
            Toast.makeText(MainActivity.this,"lower",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rnd= new Random() ;
        int rnd1 = rnd.nextInt(11);
    }
}
