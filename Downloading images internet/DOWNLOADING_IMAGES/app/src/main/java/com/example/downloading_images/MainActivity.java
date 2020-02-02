package com.example.downloading_images;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

//http://cdn1.picspussy.com/37/1/371f602bb.jpgy
public class MainActivity extends AppCompatActivity
{
    ImageView downloadimage;
    public void downloadimage(View view)
    {
        imagedowloader imagedownload=new imagedowloader();
        Bitmap myimage;
        try
        {
            myimage=imagedownload.execute("https://www.google.com/imgres?imgurl=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fen%2F5%2F5f%2FTomandJerryTitleCardc.jpg&imgrefurl=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FTom_and_Jerry&docid=k-v4h6Zq0CccIM&tbnid=nFVrdGRV9M9muM%3A&vet=10ahUKEwibxL3agtjlAhUV7HMBHVF0BD4QMwh6KAIwAg..i&w=350&h=263&bih=526&biw=1161&q=tom%20and%20jerry&ved=0ahUKEwibxL3agtjlAhUV7HMBHVF0BD4QMwh6KAIwAg&iact=mrc&uact=8").get();
            downloadimage.setImageBitmap(myimage);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downloadimage=(ImageView)findViewById(R.id.imageView);
    }
    public class imagedowloader extends AsyncTask<String,Void, Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... strings)
        {
            try
            {
                URL url=new URL(strings[0]);
                HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                connection.connect();
                InputStream inputStream=connection.getInputStream();
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);//converts the data into the image
                return bitmap;

            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;

        }

    }
}
