package com.example.newsreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter arrayAdapter;
    ArrayList<String> titles=new ArrayList<String >();
    public class downloadtask extends AsyncTask<String,Void,String>
    {

        String result="";
        HttpURLConnection urlConnection=null;

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url=new URL(strings[0]);
                urlConnection=(HttpURLConnection)url.openConnection();
                InputStream inputStream=urlConnection.getInputStream();
                InputStreamReader reader=new InputStreamReader(inputStream);
                int data = reader.read();
                while (data!=-1)
                {
                    char current=(char)data;
                    result+=current;
                    data=reader.read();
                }
                Log.i("result",result);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView=(ListView)findViewById(R.id.listview);
        downloadtask download=new downloadtask();
        download.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,titles);
        listView.setAdapter(arrayAdapter);

   }
}
