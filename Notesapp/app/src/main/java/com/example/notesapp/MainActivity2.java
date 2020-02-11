package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {
    int noteid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        EditText editText =(EditText)findViewById(R.id.editText);
        Intent intent=getIntent();
        noteid=intent.getIntExtra("noteid",-1);
        if(noteid!=-1)
        {
            editText.setText(MainActivity.note.get(noteid));
        }
        else {
            noteid=MainActivity.note.size()-1;


            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    MainActivity.note.add(MainActivity.note.size() , s.toString());
                    MainActivity.arrayAdapter.notifyDataSetChanged();

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        }

    }
}
