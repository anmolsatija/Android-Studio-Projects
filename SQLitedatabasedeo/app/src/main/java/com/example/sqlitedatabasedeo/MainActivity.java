package com.example.sqlitedatabasedeo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    static int counter =1;
    SQLiteDatabase mydatabse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            this.deleteDatabase("events");
        }
        catch (Exception e)
        {

        }

        try
        {
            counter=1;
            mydatabse=this.openOrCreateDatabase("events",MODE_PRIVATE,null);

            mydatabse.execSQL("CREATE TABLE IF NOT EXISTS event (eventname VARCHAR ,year INT(4) )");
            mydatabse.execSQL("INSERT INTO event(eventname,year)VALUES('sex1',2020)");
            mydatabse.execSQL("INSERT INTO event(eventname,year)VALUES('sex2',2021)");
            Cursor cursor=mydatabse.rawQuery("SELECT * FROM event",null);
            int eventid= cursor.getColumnIndex("eventname");
            int dateid=cursor.getColumnIndex("year");
            cursor.moveToFirst();
            while(cursor!=null)
            {
                Log.i("EVENT",cursor.getString(eventid));
                Log.i("DATE",cursor.getString(dateid));
                cursor.moveToNext();
            }

        }
        catch (Exception e)
        {

        }
    }
}
