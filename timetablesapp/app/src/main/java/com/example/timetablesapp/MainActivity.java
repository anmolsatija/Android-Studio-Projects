package com.example.timetablesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    ListView listView;
    int a;
    public void createtable(int num)
    {
        ArrayList<String> arrayList =new ArrayList<String>();
        for(int i=1;i<11;i++)
        {
            arrayList.add(Integer.toString(a*i));

        }
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar =(SeekBar)findViewById(R.id.seekBar);
        listView=(ListView)findViewById(R.id.listview);
        seekBar.setMax(20);
        //seekBar.setProgress(10);
        final int min=1;



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress<min)
                {
                    seekBar.setProgress(min);

                }
                else
                {
                    a=progress;
                }
                createtable(a);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
        createtable(1);



    }


}
