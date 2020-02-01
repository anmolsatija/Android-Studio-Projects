package com.example.timtable1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        ListView listView = (ListView) findViewById(R.id.listview);

        seekBar.setMax(20);
        seekBar.setProgress(10);
        /*seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {

                int min=1;
                int timestable;
                if(progress<min)
                {
                    timestable=min;
                    seekBar.setProgress(min);

                }
                else
                {
                    timestable=progress;
                }
                Log.i("number",Integer.toString(timestable));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        int timestable=10;
        ArrayList<String> arrayList=new ArrayList<String>();
        for(int i=1;i<1;i++)
        {
            arrayList.add(Integer.toString(i*timestable));

        }
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        */


    }
}
