package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public class downloadtask extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings)
        {
            String result="";
            URL url;
            HttpURLConnection urlConnection=null;
            try {

                url=new URL(strings[0]);
                urlConnection= (HttpURLConnection) url.openConnection();
                InputStream inputStream=urlConnection.getInputStream();
                InputStreamReader reader=new InputStreamReader(inputStream);
                int data =reader.read();
                //String result="";
                while(data!=-1)
                {
                 char current=(char)data;
                 result+=current;
                 data=reader.read();
                }
                return  result;

            } catch (MalformedURLException e) {
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
            //Log.i("content",result.toString());
            try
            {
                JSONObject object=new JSONObject(result);
                String weather = null;
                String weathernifo=object.getString("weather");
                JSONObject object1=object.getJSONObject("main");
                int tempk=(int)object1.getInt("temp");
                int tempc=tempk-273;
                //Log.i("temp", String.valueOf(tempc));


                //Log.i("weatherinfo",weathernifo);
                //Log.i("tempinfo",tempinfo);
                JSONArray arrw=new JSONArray(weathernifo);
                //JSONArray arrt=new JSONArray(tempinfo);
                //Log.i("length", String.valueOf(arrt.length()));
                for (int i = 0; i < arrw.length(); i++)
                {
                    JSONObject jsonpart1 = arrw.getJSONObject(i);
                    weather = jsonpart1.getString("main");
                    //Log.i("main",weather);
                }

                String tempcs=String.valueOf(tempc)+"°C";
                Toast.makeText(getApplicationContext(),tempcs,Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),weather.toString(),Toast.LENGTH_SHORT).show();
                resultview.setText("WEATHER:"+weather);
                tempview.setText("TEMPERATURE:"+tempcs);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"COULD NOT FIND WEATHER",Toast.LENGTH_SHORT).show();
                //Log.i("error", String.valueOf(e));
            }


        }
    }

    public void geturl(View view)
    {
        String result="";


        //mgr.hideSoftInputFromWindow();

        String cityname;
        cityname = city.getText().toString();
        Log.i("city",cityname);
        //Log.i("cityname",cityname);
        //-----> To Hide The Keyboard
        InputMethodManager mgr=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(city.getWindowToken(),0);
        //--|
        //cityname=cityname.replaceAll("\\s", "");
        String encodedcityname= URLEncoder.encode(cityname);
        String url= "https://api.openweathermap.org/data/2.5/weather?q="+cityname+"&appid=45460a0c9560996537513d18a8fd326f";
        //Log.i("url",url);
        if(cityname!=null||cityname!="")
        {
            downloadtask task = new downloadtask();
            try {
                result = task.execute(url).get();
                //Log.i("result",result);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "COULD NOT FIND WEATHER", Toast.LENGTH_SHORT).show();
            }
        }
    }
    EditText city;
    TextView resultview,tempview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        city=(EditText)findViewById(R.id.cityname);
        resultview=(TextView)findViewById(R.id.result);
        tempview=(TextView)findViewById(R.id.temp);



    }
}
