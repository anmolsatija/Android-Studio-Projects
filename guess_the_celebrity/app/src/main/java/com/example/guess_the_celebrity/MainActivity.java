package com.example.guess_the_celebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    Button b1,b2,b3,b4;
    ArrayList<String> celeburls=new ArrayList<String>();
    ArrayList<String> celebnames=new ArrayList<String>();
    int chosenceleb;
    public class downloadtask extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... urls)
        {
            String result="";
            URL url;
            HttpURLConnection urlconection=null;
            try
            {
                url=new URL(urls[0]);
                urlconection=(HttpURLConnection)url.openConnection();
                InputStream inputStream=urlconection.getInputStream();
                InputStreamReader reader=new InputStreamReader(inputStream);
                int data=reader.read();
                while(data!=-1)
                {
                    char current=(char) data;
                    result+=current;
                    data=reader.read();

                }
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    public class downloadimage extends AsyncTask<String,Void,Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... urls)
        {
            try
            {
                Bitmap bitmap;
                URL url=new URL(urls[0]);
                HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
                urlConnection.connect();
                InputStream inputStream=urlConnection.getInputStream();
                bitmap= BitmapFactory.decodeStream(inputStream);
                return bitmap;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    public void press(View view)
    {
        Random random=new Random();
        int r1,r2,r3,r4;
        r1=random.nextInt(celeburls.size());
        r2=random.nextInt(celeburls.size());
        r3=random.nextInt(celeburls.size());
        r4=random.nextInt(celeburls.size());
        b1.setText(Arrays.toString(new String[]{celebnames.get(r1)}));



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
        img=(ImageView)findViewById(R.id.celebimage);


        downloadtask task=new downloadtask();
        String result="";

        try {
            result=task.execute("http://www.posh24.se/kandisar").get();
            String[] splitresult=result.split(("div class=\"sidebarContainer\">"));
            Pattern p=Pattern.compile("img src=\"(.*?)\"");
            Matcher m=p.matcher(splitresult[0]);
            while(m.find())
            {
                celeburls.add(m.group(1));
            }
            p=Pattern.compile("alt=\"(.*?)\"");
            m=p.matcher(splitresult[0]);
            while(m.find())
            {
                celebnames.add(m.group(1));
            }
            Random random=new Random();
            chosenceleb=random.nextInt(celeburls.size());
            downloadimage downimg=new downloadimage();
            Bitmap bitmap;
            bitmap=downimg.execute(celeburls.get(chosenceleb)).get();
            img.setImageBitmap(bitmap);


            

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
