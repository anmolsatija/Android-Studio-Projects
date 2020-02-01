package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public void convert(View view)
    {
        EditText amount = (EditText) findViewById(R.id.dollaramount);
        Double dollaramountdouble =Double.parseDouble(amount.getText().toString());
        Log.i("amount",amount.getText().toString());
        Double rupeeamount = dollaramountdouble * 71.03;

        Toast.makeText(MainActivity.this,"rupees",Toast.LENGTH_LONG);
        Log.i("rupees",rupeeamount.toString());

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
