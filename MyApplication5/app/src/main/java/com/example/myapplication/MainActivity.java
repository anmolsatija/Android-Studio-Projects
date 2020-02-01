package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    /*public void onclickfunction(View view)
    {

        EditText emailid =(EditText) (findViewById(R.id.emailid));
        EditText password =(EditText)(findViewById(R.id.password));
        Log.i("emailid",emailid.getText().toString());
        Log.i("password",password.getText().toString());

        //TOAST-Displaying Message
        Toast.makeText(MainActivity.this, emailid.getText().toString()+" fuck you", Toast.LENGTH_LONG).show();

*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}
