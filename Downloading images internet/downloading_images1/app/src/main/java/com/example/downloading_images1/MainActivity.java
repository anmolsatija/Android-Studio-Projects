package com.example.downloading_images1;

        import androidx.appcompat.app.AppCompatActivity;

        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.provider.ContactsContract;
        import android.view.View;
        import android.widget.ImageView;

        import java.io.IOException;
        import java.io.InputStream;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;

public class MainActivity extends AppCompatActivity
{
    ImageView downloadimage;
    public void downloadimage(View view)
    {
        imagedownloader imagedownload=new imagedownloader();
        Bitmap myimage;
        try
        {
            myimage=imagedownload.execute("https://www.cbronline.com/wp-content/uploads/2016/06/what-is-URL-770x503.jpg").get();
            downloadimage.setImageBitmap(myimage);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public class imagedownloader extends AsyncTask<String,Void,Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... strings)
        {
            try
            {
                URL url=new URL(strings[0]);
                HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
                urlConnection.connect();
                InputStream inputStream=urlConnection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
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
            return  null;

        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downloadimage=(ImageView)findViewById(R.id.imageView);

    }
}
