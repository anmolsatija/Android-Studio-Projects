package com.example.downloading_web_content;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    //do see/use the permission for internet in AndroidManifest.xml under manifests file
    public class DownloadTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings)
        {
            String result="";
            URL url;
            HttpURLConnection urlConnection=null;
            try
            {
               url=new URL(strings[0]) ;
               urlConnection=(HttpURLConnection) url.openConnection();
               InputStream in =urlConnection.getInputStream();
               InputStreamReader reader=new InputStreamReader(in);
               int data=reader.read();
               //Log.i("data",String.valueOf(data));
               while(data!=-1)
               {
                   char current =(char)data;
                   result+=current;
                   data=reader.read();

               }
               return result;
            }
            catch (Exception  e)
            {
                e.printStackTrace();
                return "failed";
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadTask task=new DownloadTask();
        String result = null;
        try
        {
            result = task.execute("https://fast.com/").get();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        Log.i("Content of URL",result);
    }
}
