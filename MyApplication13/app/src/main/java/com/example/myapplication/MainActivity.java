
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.util.LocaleData;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();

                }

            return result;
        }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            Log.i("website content ",result);
            try
            {
                JSONObject jsonObject=new JSONObject(result);
                String weatherinfo = jsonObject.getString("weather");
                Log.i("weather",weatherinfo);
                JSONArray arr=new JSONArray(weatherinfo);
                Log.i("arraylegth", String.valueOf(arr.length()));
                for(int i=0;i<arr.length();i++)
                {
                    JSONObject jsonpart=arr.getJSONObject(i);
                    Log.i("MAIN",jsonpart.gtring("main"));
                    Log.i("DESCRIPTION",jsonpart.getString("description"));
                }

            }
            catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadtask task=new downloadtask();
        task.execute("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");

    }

}
