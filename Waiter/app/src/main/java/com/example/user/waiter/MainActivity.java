package com.example.user.waiter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button next,clear,change;
    Spinner waiter;
    EditText table;
    TextView waitername;

    String[] name= new String[] {"alpha","baker","Charlie","delta"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next = (Button)findViewById(R.id.button);
        clear = (Button)findViewById(R.id.button2);
        change = (Button)findViewById(R.id.button3);
        waiter = (Spinner)findViewById(R.id.spinner);
        table = (EditText)findViewById(R.id.editText);
        waitername = (TextView)findViewById(R.id.textView5);

        ArrayAdapter<String> adaptername=new ArrayAdapter<String>
                (this,android.R.layout.simple_spinner_item,name);

        adaptername.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        waiter.setAdapter(adaptername);

        next.setOnClickListener(btnDoClick);
        clear.setOnClickListener(btnDoClick2);
        change.setOnClickListener(btnDoClick3);
        waiter.setOnItemSelectedListener(spnPreferListener);
    }

    private Button.OnClickListener btnDoClick = new Button.OnClickListener(){
        public void onClick(View v){

            Intent intent=new Intent();
            intent.setClass(MainActivity.this,order.class);

            String tablenumber = table.getText().toString();
            String name = waitername.getText().toString();

            if (tablenumber.equals("")){
                Toast message = Toast.makeText(MainActivity.this,"桌號不可為空，請輸入桌號",Toast.LENGTH_LONG);
                message.show();
            }

            else {
                Bundle bundle=new Bundle();
                bundle.putString("NUMBER", tablenumber);
                bundle.putString("NAME", name);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        }
    };

    private Button.OnClickListener btnDoClick2 = new Button.OnClickListener(){
        public void onClick(View v){
            table.setText("");
        }
    };

    private Button.OnClickListener btnDoClick3 = new Button.OnClickListener(){
        public void onClick(View v){

            Intent intent=new Intent();
            intent.setClass(MainActivity.this,change.class);

            String tablenumber = table.getText().toString();
            String name = waitername.getText().toString();

            if (tablenumber.equals("")){
                Toast message = Toast.makeText(MainActivity.this,"桌號不可為空，請輸入桌號",Toast.LENGTH_LONG);
                message.show();
            }

            else {
                Bundle bundle=new Bundle();
                bundle.putString("NUMBER", tablenumber);
                bundle.putString("NAME", name);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        }
    };

    private Spinner.OnItemSelectedListener spnPreferListener=
            new Spinner.OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String sel=parent.getSelectedItem().toString();
                    waitername.setText(sel);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub
                }
            };
}
