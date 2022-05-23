package com.example.user.waiter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * Created by USER on 2017/9/30.
 */

public class change extends AppCompatActivity {

    Button AC,HC,CL,FR,FF,RT,CF,VS,send,clear;
    ListView show;
    TextView check,desk,waiter,time;
    String[] food= new String[] {"全雞","半雞","雞腿","炒飯","薯條","紅茶","咖啡","蔬菜湯"};
    String data;
    String inf,infsend;
    int s1,s2,s3=0;

    ArrayList<String> list=new ArrayList<>();
    ArrayList<String> area1=new ArrayList<>();
    ArrayList<String> area2=new ArrayList<>();
    ArrayList<String> area3=new ArrayList<>();
    ArrayAdapter<String> adapter;

    DatabaseReference download,update;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change);

        AC = (Button) findViewById(R.id.button1);
        HC = (Button) findViewById(R.id.button2);
        CL = (Button) findViewById(R.id.button3);
        FR = (Button) findViewById(R.id.button4);
        FF = (Button) findViewById(R.id.button5);
        RT = (Button) findViewById(R.id.button6);
        CF = (Button) findViewById(R.id.button7);
        VS = (Button) findViewById(R.id.button8);
        send = (Button)findViewById(R.id.button9);
        clear = (Button)findViewById(R.id.button10);

        show = (ListView)findViewById(R.id.listview);

        check = (TextView)findViewById(R.id.textView2);
        desk = (TextView)findViewById(R.id.textView3);
        waiter = (TextView)findViewById(R.id.textView4);
        time = (TextView)findViewById(R.id.textView5);

        AC.setOnClickListener(btnDoClick);
        HC.setOnClickListener(btnDoClick);
        CL.setOnClickListener(btnDoClick);
        FR.setOnClickListener(btnDoClick);
        FF.setOnClickListener(btnDoClick);
        RT.setOnClickListener(btnDoClick);
        CF.setOnClickListener(btnDoClick);
        VS.setOnClickListener(btnDoClick);
        send.setOnClickListener(btnDoClick2);
        clear.setOnClickListener(btnDoClick3);
        show.setOnItemClickListener(listViewOnItemClickListener);

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,list);

        show.setOnItemClickListener(listViewOnItemClickListener);

        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        String tablenumber=bundle.getString("NUMBER");
        String waitername=bundle.getString("NAME");

        download = FirebaseDatabase.getInstance().getReference("餐點資料/" + tablenumber + "/" + waitername);
        update = FirebaseDatabase.getInstance().getReference();
        download.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    list.add(postSnapshot.getValue().toString());
                }
                int m =list.size();
                check.setText(list.get(m-2) + list.get(m-3));
                desk.setText(list.get(m-3));
                waiter.setText(list.get(m-2));
                time.setText(list.get(m-1));
                list.remove(m-1);
                list.remove(m-2);
                list.remove(m-3);
                show.setAdapter(adapter);
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

    private Button.OnClickListener btnDoClick = new Button.OnClickListener() {
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.button1: {
                    data = food[0];
                    data = data +  "," + s1;
                    s1++;
                    list.add(data);
                    break;
                }
                case R.id.button2: {
                    data = food[1];
                    data = data +  "," + s1;
                    s1++;
                    list.add(data);
                    break;
                }
                case R.id.button3:{
                    data = food[2];
                    data = data +  "," + s1;
                    s1++;
                    list.add(data);
                    break;
                }
                case R.id.button4:{
                    data = food[3];
                    data = data +  "," + s2;
                    s2++;
                    list.add(data);
                    break;
                }
                case R.id.button5:{
                    data = food[4];
                    data = data +  "," + s2;
                    s2++;
                    list.add(data);
                    break;
                }
                case R.id.button6:{
                    data = food[5];
                    data = data +  "," + s3;
                    s3++;
                    list.add(data);
                    break;
                }
                case R.id.button7:{
                    data = food[6];
                    data = data +  "," + s3;
                    s3++;
                    list.add(data);
                    break;
                }
                case R.id.button8:{
                    data = food[7];
                    data = data +  "," + s3;
                    s3++;
                    list.add(data);
                    break;
                }
            }
            show.setAdapter(adapter);
        }
    };

    private Button.OnClickListener btnDoClick2 = new Button.OnClickListener(){
        public void onClick(View v){

            String checkword = list.toString();

            new AlertDialog.Builder(change.this).setTitle("餐點確認").setIcon(R.mipmap.ic_launcher).setMessage(checkword)
                    .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String data10 = desk.getText().toString();
                            String data11 = waiter.getText().toString();
                            String data12 = time.getText().toString();
                            update.child("餐點資料/" + data10 + "/" + data11 + "/waypoint");

                            list.add(data10);
                            list.add(data11);
                            list.add(data12);
                            update.setValue(list);

                            update.child("A區/" + data10);
                            update.setValue(area1);

                            update.child("B區/" + data10);
                            update.setValue(area2);

                            update.child("C區/" + data10);
                            update.setValue(area3);

                            Intent intent=new Intent();
                            intent.setClass(change.this,MainActivity.class);

                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("修改", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();
        }
    };

    private Button.OnClickListener btnDoClick3 = new Button.OnClickListener(){
        public void onClick(View v){
            recreate();
        }
    };

    private ListView.OnItemClickListener listViewOnItemClickListener
            = new ListView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {

            String strSelectedItem = parent.getItemAtPosition(position).toString();

            adapter.remove(strSelectedItem);
            adapter.notifyDataSetChanged();

        }};
}
