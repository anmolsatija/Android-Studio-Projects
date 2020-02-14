package com.example.newsreader;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
        ArrayAdapter arrayAdapter;
        ArrayList<String> content=new ArrayList<String >();
        ArrayList<String> titles=new ArrayList<String >();
        SQLiteDatabase articlesdb;
        public void updatelistvew()
        {
            Cursor c=articlesdb.rawQuery("SELECT * FROM articles",null);
            int contentindex=c.getColumnIndex("content");
            int titleindex=c.getColumnIndex("title");
            if(c.moveToNext())
            {
                titles.clear();
                content.clear();
                do {
                    titles.add(c.getString(titleindex));
                    content.add(c.getString(contentindex));
                }
                while(c.moveToNext());
                arrayAdapter.notifyDataSetChanged();

            }

        }
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
                    JSONArray jsonArray=new JSONArray(result);
                    int length =20;
                    if(jsonArray.length()<20)
                    {
                        length=jsonArray.length();
                    }

                    articlesdb.execSQL("DELETE FROM articles ");
                    for(int i=2;i<4;i++)
                    {
                        String result1="";
                        String articleid=jsonArray.getString(i);
                        url=new URL("https://hacker-news.firebaseio.com/v0/item/"+articleid+".json?print=pretty");
                        HttpURLConnection urlConnection1= (HttpURLConnection) url.openConnection();
                        InputStream inputStream1=urlConnection1.getInputStream();
                        InputStreamReader reader1=new InputStreamReader(inputStream1);
                        int data1=reader1.read();
                        while(data1!=-1)
                        {
                            char current1=(char)data1;
                            result1+=current1;
                            data1=reader1.read();
                        }
                        Log.i("result1",result1);
                        JSONObject jsonObject=new JSONObject(result1);
                        //Log.i("URL",jsonObject.getString("url"));
                        if(!jsonObject.isNull("title") && !jsonObject.isNull("url"))
                        {
                            String result2="";
                            String articleurl=jsonObject.getString("url");
                            String articletitle=jsonObject.getString("title");
                            url=new URL(articleurl);
                            Log.i("url",url.toString());
                            HttpURLConnection urlConnection2= (HttpURLConnection) url.openConnection();
                            InputStream inputStream2=urlConnection2.getInputStream();
                            InputStreamReader reader2=new InputStreamReader(inputStream2);
                            int data2=reader2.read();
                            while(data2!=-1)
                            {
                                char current2=(char)data2;
                                result2+=current2;
                                data2=reader2.read();
                            }
                            String sql="INSERT INTO articles (id,title,content)VALUES(?,?,?)";
                            SQLiteStatement statement=articlesdb.compileStatement(sql);
                            statement.bindString(1,articleid);
                            statement.bindString(2,articletitle);
                            statement.bindString(3,result2);
                            statement.execute();


                        }

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                updatelistvew();
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView=(ListView)findViewById(R.id.listview);
        articlesdb=this.openOrCreateDatabase("Articles",MODE_PRIVATE,null);
        articlesdb.execSQL("CREATE TABLE IF NOT EXISTS articles(id INTEGER PRIMARY KEY,title VARCHAR,content VARCHAR)");
        updatelistvew();
        downloadtask download=new downloadtask();
        download.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,titles);
        listView.setAdapter(arrayAdapter);
    }
}
