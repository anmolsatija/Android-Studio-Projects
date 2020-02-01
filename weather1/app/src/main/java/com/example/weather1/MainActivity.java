package com.example.weather1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public class downloadtask extends AsyncTask<String,Void,String>
    {
        String result="";
        HttpURLConnection connection=null;
        @Override
        protected String doInBackground(String... strings)
        {
            try
            {
                URL url= new URL(strings[0]);
                connection=(HttpURLConnection) url.openConnection();
                InputStream in =connection.getInputStream();
                InputStreamReader reader=new InputStreamReader(in);
                int data =reader.read();
                while(data!=-1)
                {
                    char current=(char)data;
                    result+=current;
                    reader.read();
                }
                Log.i("result",result);
                return result;
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
                Log.i("malformed url", String.valueOf(e));
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("ioexception",String.valueOf(e));
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result)
        {
            Log.i("result",result);
            super.onPostExecute(result);
        }
    }
    EditText city;
    String url;

    /*public void execute(View view)
    {
        String name=city.getText().toString();
        Log.i("cityname", name);
        url="https://api.openweathermap.org/data/2.5/weather?q="+name+"&appid=45460a0c9560996537513d18a8fd326f";
        Log.i("URL",url);
        downloadtask down =new downloadtask();
        try {
            down.execute(url).get();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
            Log.i("Execution Error",String.valueOf(e));
        } catch (InterruptedException e)
        {
            e.printStackTrace();
            Log.i("interrupted",String.valueOf(e));
        }

     */






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String result="";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        city=(EditText)findViewById(R.id.editText);
        url="https://api.openweathermap.org/data/2.5/weather?q=gurgaon&appid=45460a0c9560996537513d18a8fd326f";
        Log.i("URL",url);
        downloadtask down =new downloadtask();
        down.execute(url);

    }
}
