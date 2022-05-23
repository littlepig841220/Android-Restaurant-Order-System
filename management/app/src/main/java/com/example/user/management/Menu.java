package com.example.user.management;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by USER on 2017/10/19.
 */

public class Menu extends AppCompatActivity {

    Button Purchase,realtimedata;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        Purchase = (Button)findViewById(R.id.button1);
        realtimedata = (Button)findViewById(R.id.button2);

        Purchase.setOnClickListener(btnDoClick);
        realtimedata.setOnClickListener(btnDoClick2);
    }

    private Button.OnClickListener btnDoClick = new Button.OnClickListener(){
        public void onClick(View v){
            Intent intent=new Intent();
            intent.setClass(Menu.this,Menu.class);

            startActivity(intent);
        }
    };

    private Button.OnClickListener btnDoClick2 = new Button.OnClickListener(){
        public void onClick(View v){
            Intent intent2=new Intent();
            intent2.setClass(Menu.this,MainActivity.class);

            startActivity(intent2);
        }
    };
}
