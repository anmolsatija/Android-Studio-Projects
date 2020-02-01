package com.example.memorableplaces1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ArrayList<String> arrayList=new ArrayList<String>();
    static ArrayList<LatLng> locations=new ArrayList<LatLng>();
    static ArrayAdapter arrayAdapter;
    static ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=(ListView)findViewById(R.id.listview);
        ArrayList<String> latitude=new ArrayList<String>();
        ArrayList<String> longitude=new ArrayList<String>();
        SharedPreferences sharedPreferences=this.getSharedPreferences("com.example.memorableplaces",Context.MODE_PRIVATE);
        arrayList.clear();
        locations.clear();
        latitude.clear();
        longitude.clear();
        try {
            arrayList=(ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("location", ObjectSerializer.serialize( new ArrayList<String>())));
            latitude=(ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("latitude", ObjectSerializer.serialize( new ArrayList<String>())));
            longitude=(ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("longitude", ObjectSerializer.serialize( new ArrayList<String>())));

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(arrayList.size()>0&&latitude.size()>0&&longitude.size()>0)
        {
            if(arrayList.size()==latitude.size()&&latitude.size()==longitude.size())
            {
                for(int i=0;i<latitude.size();i++)
                {
                    locations.add(new LatLng(Double.parseDouble(latitude.get(i)),Double.parseDouble(longitude.get(i))));
                }
            }
        }

        else
        {
            arrayList.add("ADD MEMORABLE PLACES");
            locations.add(new LatLng(0, 0));
        }
        arrayAdapter =new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                String tappedvalue = arrayList.get(position).toString();
                intent.putExtra("PlaceNumber", position);
                startActivity(intent);
            }
        });


    }
}
