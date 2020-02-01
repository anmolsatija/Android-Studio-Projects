package com.example.numbershapes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText num;
    int num1,flag1=0,flag2=0;

    public class number
    {
        public int istriangular() {
            int triangular = 1, x = 1;
            while (triangular < num1) {
                x++;
                triangular += x;
            }
            if (triangular == num1) {
                Log.i("true", "its trinagular num");
                //Toast.makeText(MainActivity.this, "It's a triangular number", Toast.LENGTH_SHORT).show();
                flag1=1;
            } else {
                Log.i("false", "its not traingular num");
                //Toast.makeText(MainActivity.this, "It's not a triangular number", Toast.LENGTH_SHORT).show();
                flag1=0;
            }
            return flag1;

        }

        public int issquare()
            {
                double square1= Math.sqrt(num1);
                if(square1==Math.floor(square1))
                {
                    Log.i("true","It's a square number");
                    //Toast.makeText(MainActivity.this,"It's a square number",Toast.LENGTH_SHORT).show();
                    flag2=1;
                }
                else
                {
                    Log.i("false","It's not a square number");
                    //Toast.makeText(MainActivity.this,"It's not a square number",Toast.LENGTH_SHORT).show();
                    flag2=0;
                }


        return flag2;
        }




    }

    public void submit(View view)
    {
        num =(EditText) findViewById(R.id.number);
        if(num.getText().toString().isEmpty())
        {
            Toast.makeText(MainActivity.this,"please enter a number",Toast.LENGTH_SHORT).show();
        }
        else
        {
            num1 = Integer.parseInt(num.getText().toString());
            Log.i("usersnumber", num.getText().toString());
            number num2 = new number();
            num2.istriangular();
            num2.issquare();
            if (flag1==0 && flag2==0)
            {
                Toast.makeText(MainActivity.this,"nor triangular neither square",Toast.LENGTH_SHORT).show();
            }
            else if(flag1==1 && flag2==0 )

            {
                Toast.makeText(MainActivity.this,"triangular but not squared",Toast.LENGTH_SHORT).show();
            }
            else if (flag1==0&&flag2==1)
            {
                Toast.makeText(MainActivity.this,"not triangular but squared ",Toast.LENGTH_SHORT).show();
            }
            else if (flag1==1&&flag2==1)
            {
                Toast.makeText(MainActivity.this,"number is triangular and squared both",Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
