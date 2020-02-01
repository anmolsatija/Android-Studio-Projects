package com.example.showing_hiding_ui_elements;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.FileObserver;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Switch switch1;
    TextView textView;
    int counter=1;
    public void bleh(View view)
    {
        textView=(TextView)findViewById(R.id.textView);
        if(counter==1)
        {
            counter=0;
            textView.setVisibility(View.INVISIBLE);
        }
        else
        {
            counter=1;
            textView.setVisibility(View.VISIBLE);
        }

    }

    /*public void switchh(View view)
    {
        textView=(TextView)findViewById(R.id.textView);
        switch1=(Switch)findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    textView.setVisibility(View.VISIBLE);
                    Toast.makeText(getBaseContext(),"on",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    textView.setVisibility(View.INVISIBLE);
                    Toast.makeText(getBaseContext(),"off",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
