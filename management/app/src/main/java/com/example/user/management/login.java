package com.example.user.management;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

/**
 * Created by USER on 2017/9/5.
 */

public class login extends AppCompatActivity{

    Button next,clear,plus;
    EditText acount,password;
    TextView test;

    String[] name = new String[] {"system"};
    String[] pass = new String[]{"0000"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        next = (Button)findViewById(R.id.button);
        clear = (Button)findViewById(R.id.button2);
        plus = (Button)findViewById(R.id.button3);
        acount = (EditText)findViewById(R.id.editText);
        password = (EditText)findViewById(R.id.editText2);
        test = (TextView)findViewById(R.id.textView2);

        next.setOnClickListener(btnDoClick);
        clear.setOnClickListener(btnDoClick2);
        plus.setOnClickListener(btnDoClick3);

    }

    private Button.OnClickListener btnDoClick = new Button.OnClickListener(){
        public void onClick(View v){

            Intent intent=new Intent();
            intent.setClass(login.this,Menu.class);

            String acc = acount.getText().toString();
            String pad = password.getText().toString();

            int data = Arrays.binarySearch(name,acc);
            int data2 = Arrays.binarySearch(pass,pad);

            if (data==0 & data2==0){
                Bundle bundle=new Bundle();
                bundle.putString("ACCOINT", acc);
                bundle.putString("PASSWORLD", pad);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        }
    };

    private Button.OnClickListener btnDoClick2 = new Button.OnClickListener(){
        public void onClick(View v){
            acount.setText("");
            password.setText("");
        }
    };

    private Button.OnClickListener btnDoClick3 = new Button.OnClickListener(){
        public void onClick(View v){

        }
    };
}
