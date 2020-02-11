package com.example.notesapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final SharedPreferences sharedPreferences=this.getSharedPreferences("com.example.notesapp1", Context.MODE_PRIVATE);
        EditText editText = (EditText) findViewById(R.id.editText);
        Intent intent=getIntent();
        MainActivity.noteid=intent.getIntExtra("noteid",-1);
        if(MainActivity.noteid!=-1)
        {

            editText.setText(MainActivity.arrayList.get(MainActivity.noteid));
        }
        else if(MainActivity.noteid==-1)
        {
            MainActivity.arrayList.add("");
            MainActivity.noteid=MainActivity.arrayList.size()-1;
            Log.i("noteid", String.valueOf(MainActivity.noteid));
            MainActivity.notearray.add(MainActivity.noteid);


        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                MainActivity.arrayList.set(MainActivity.noteid,s.toString());
                MainActivity.arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        try {
            Log.i("noteid1", String.valueOf(MainActivity.noteid));
            sharedPreferences.edit().putString("note",ObjectSerializer.serialize(MainActivity.arrayList)).apply();
            sharedPreferences.edit().putString("noteid",ObjectSerializer.serialize(MainActivity.notearray)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

