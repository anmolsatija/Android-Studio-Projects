package com.example.listview_practice1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView=(ListView)findViewById(R.id.listview);
        //creating arraylist
        //METHOD 1

        final ArrayList<String>myarraylist=new ArrayList<String>(asList("a","b","c","d","e","f"));

        //METHOD 2
        /*final ArrayList<String>myarraylist=new ArrayList<String>();
        myarraylist.add("a");
        myarraylist.add("b");
        myarraylist.add("c");
        myarraylist.add("d");
        myarraylist.add("e");
        myarraylist.add("f");

        */


        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myarraylist);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i("hello",myarraylist.get(position));
                Toast.makeText(getApplicationContext(),"hello "+myarraylist.get(position),Toast.LENGTH_SHORT).show();

            }
        });



    }
}
