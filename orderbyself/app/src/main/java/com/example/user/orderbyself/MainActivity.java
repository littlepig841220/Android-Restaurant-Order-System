package com.example.user.orderbyself;

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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button next,clear,change;
    Spinner waiter;
    EditText table;
    TextView waitername;
    TextView t7;

    String[] name= new String[] {"alpha","baker","Charlie","delta"};

    ArrayList<String> list=new ArrayList<>();

    DatabaseReference check;

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

        t7 = (TextView) findViewById(R.id.textView7);

        ArrayAdapter<String> adaptername=new ArrayAdapter<String>
                (this,android.R.layout.simple_spinner_item,name);

        adaptername.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        waiter.setAdapter(adaptername);

        next.setOnClickListener(btnDoClick);
        change.setOnClickListener(btnDoClick3);
        waiter.setOnItemSelectedListener(spnPreferListener);

        check = FirebaseDatabase.getInstance().getReference("餐點資料/01");
        check.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    list.add(postSnapshot.getValue().toString());
                }

                String data[] = list.get(0).toString().split(",");

                int x = data.length;

                t7.setText(data[x - 2].toString());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onBackPressed(){
        Toast Warnings1 = Toast.makeText(MainActivity.this,"操作錯誤!!",Toast.LENGTH_LONG);
        Warnings1.show();
    }


    private Button.OnClickListener btnDoClick = new Button.OnClickListener(){
        public void onClick(View v){

            Intent intent=new Intent();
            intent.setClass(MainActivity.this,order.class);

            String tablenumber = table.getText().toString();
            String name = waitername.getText().toString();

            if (list.isEmpty()){
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
            else{
                Toast message = Toast.makeText(MainActivity.this,"您以點過餐點，請通知服務生",Toast.LENGTH_LONG);
                message.show();
            }
        }
    };

    private Button.OnClickListener btnDoClick3 = new Button.OnClickListener(){
        public void onClick(View v){

            Intent intent=new Intent();
            intent.setClass(MainActivity.this,cashier.class);

            String tablenumber = table.getText().toString();
            String name = waitername.getText().toString();

            if (tablenumber.equals("")){
                Toast message = Toast.makeText(MainActivity.this,"桌號不可為空，請輸入桌號",Toast.LENGTH_LONG);
                message.show();
            }

            else {
                Bundle bundle2=new Bundle();
                bundle2.putString("NUMBER", tablenumber);
                bundle2.putString("NAME", name);
                intent.putExtras(bundle2);

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
