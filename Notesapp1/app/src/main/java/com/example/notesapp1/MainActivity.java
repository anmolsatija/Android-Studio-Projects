package com.example.notesapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    static ArrayList<String> arrayList=new ArrayList<String>();
    static ArrayAdapter arrayAdapter;
    static ListView listView;
    static int noteid;
    static ArrayList<Integer> notearray= new ArrayList<Integer>();
    static  int counter ;
    SharedPreferences sharedPreferences;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

   @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        counter++;
        Intent intent=new Intent(getApplicationContext(),MainActivity2.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listeview);
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        sharedPreferences=this.getSharedPreferences("com.example.notesapp1", Context.MODE_PRIVATE);
            try {
                arrayList=(ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("note", ObjectSerializer.serialize( new ArrayList<String>())));
                noteid=arrayList.size()-1;
                Log.i("sze", arrayList.get(noteid));
                notearray=(ArrayList<Integer>)ObjectSerializer.deserialize(sharedPreferences.getString("noteid", ObjectSerializer.serialize(new ArrayList<Integer> ())));
                arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
                //arrayList.add("");
                listView.setAdapter(arrayAdapter);

            } catch (IOException e) {
                e.printStackTrace();
            }



        //arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<? > parent, View view, int position, long id)
            {
                Intent intent=new Intent(getApplicationContext(),MainActivity2.class);
                intent.putExtra("noteid",position);
                startActivity(intent);

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                Log.i("size", String.valueOf(arrayList.size()));
                final int pos =position;
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("DELETE")
                        .setMessage("Are you Sure you want to delete this NOTE")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                counter--;

                                arrayList.remove(pos);
                                notearray.remove(pos);
                                arrayAdapter.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this,"NOTE deleted !",Toast.LENGTH_SHORT).show();
                                try {
                                    sharedPreferences.edit().putString("note",ObjectSerializer.serialize(MainActivity.arrayList)).apply();
                                    //sharedPreferences.edit().putString("noteid",ObjectSerializer.serialize(MainActivity.arrayList)).apply();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        }).setNegativeButton("no",null)
                        .show();



                return true;
            }
        });
        //arrayList.add("Note");
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(arrayAdapter);

    }
}
