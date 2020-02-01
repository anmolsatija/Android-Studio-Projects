package com.example.newactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public void startactivity2(View view)
    {
        Intent intent=new Intent(this.getApplicationContext(),MainActivity2.class);
        //passing value from one activity to other
        //intent.putExtra("username","anmol");
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView=(ListView)findViewById(R.id.listview);
        final ArrayList<String> friends=new ArrayList<String>();
        friends.add("Monica");
        friends.add("Ross");
        friends.add("Joey");
        friends.add("Chandler");
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,friends);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent =new Intent(getApplicationContext(),MainActivity2.class);
                intent.putExtra("name",friends.get(position).toString());
                startActivity(intent);
            }
        });



    }
}
