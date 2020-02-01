package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView=(ListView)findViewById(R.id.listview);
        final ArrayList<String> myfamily=new ArrayList<String>();
        myfamily.add("a");
        myfamily.add("b");
        myfamily.add("c");
        myfamily.add("d");
        myfamily.add("e");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myfamily);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                Log.i("listtapped",myfamily.get(position));
                Toast.makeText(getApplicationContext(),"hello "+myfamily.get(position),Toast.LENGTH_SHORT).show();
                //parent.setVisibility(View.GONE);
            }
        });

    }
}
