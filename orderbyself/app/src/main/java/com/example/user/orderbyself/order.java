package com.example.user.orderbyself;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by USER on 2017/11/22.
 */

public class order extends AppCompatActivity {

    Button AC,HC,CL,FR,FF,RT,CF,VS,send,clear,cancel;
    ListView show;
    TextView ckeck,desk,waiter,time;
    String[] food= new String[] {"全雞","半雞","雞腿","炒飯","薯條","紅茶","咖啡","蔬菜湯"};
    String inf,infsend;

    ArrayList<String> list=new ArrayList<>();
    ArrayList<String> area1=new ArrayList<>();
    ArrayList<String> area2=new ArrayList<>();
    ArrayList<String> area3=new ArrayList<>();
    ArrayAdapter<String> adapter;

    DatabaseReference test = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mcondition;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);

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
        cancel = (Button)findViewById(R.id.button11);

        show = (ListView)findViewById(R.id.listview);

        ckeck = (TextView)findViewById(R.id.textView2);
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
        cancel.setOnClickListener(btnDoClick4);
        show.setOnItemClickListener(listViewOnItemClickListener);

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,list);

        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        String tablenumber=bundle.getString("NUMBER");
        String waitername=bundle.getString("NAME");
        inf = waitername + "," + tablenumber;
        infsend = tablenumber + "," + waitername;
        ckeck.setText(inf);
        desk.setText(tablenumber);
        waiter.setText(waitername);

        Calendar mCal = Calendar.getInstance();
        CharSequence s = DateFormat.format("kk:mm:ss", mCal.getTime());
        String data20 = s.toString();
        time.setText(data20);

    }
    @Override
    public void onBackPressed(){
        Toast Warnings1 = Toast.makeText(order.this,"操作錯誤!!",Toast.LENGTH_LONG);
        Warnings1.show();
    }

    private Button.OnClickListener btnDoClick = new Button.OnClickListener() {
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.button1: {
                    list.add(food[0]);
                    break;
                }
                case R.id.button2: {
                    list.add(food[1]);
                    break;
                }
                case R.id.button3:{
                    list.add(food[2]);
                    break;
                }
                case R.id.button4:{
                    list.add(food[3]);
                    break;
                }
                case R.id.button5:{
                    list.add(food[4]);
                    break;
                }
                case R.id.button6:{
                    list.add(food[5]);
                    break;
                }
                case R.id.button7:{
                    list.add(food[6]);
                    break;
                }
                case R.id.button8:{
                    list.add(food[7]);
                    break;
                }
            }
            show.setAdapter(adapter);
        }
    };

    private Button.OnClickListener btnDoClick2 = new Button.OnClickListener(){
        public void onClick(View v){

            final int m = list.size();

            String checkword = list.toString();

            new AlertDialog.Builder(order.this).setTitle("餐點確認").setIcon(R.mipmap.ic_launcher).setMessage(checkword)
                    .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            for (int i=0;i<m;i++){
                                String data = list.get(i);
                                switch (data){
                                    case "全雞":{
                                        String data2 = data + "," + infsend;
                                        area1.add(data2);
                                        break;
                                    }
                                    case "半雞":{
                                        String data2 = data + "," + infsend;
                                        area1.add(data2);
                                        break;
                                    }
                                    case "雞腿":{
                                        String data2 = data + "," + infsend;
                                        area1.add(data2);
                                        break;
                                    }
                                    case "炒飯":{
                                        String data2 = data + "," + infsend;
                                        area2.add(data2);
                                        break;
                                    }
                                    case "薯條":{
                                        String data2 = data + "," + infsend;
                                        area2.add(data2);
                                        break;
                                    }
                                    case "紅茶":{
                                        String data2 = data + "," + infsend;
                                        area3.add(data2);
                                        break;
                                    }
                                    case "咖啡":{
                                        String data2 = data + "," + infsend;
                                        area3.add(data2);
                                        break;
                                    }
                                    case "蔬菜湯":{
                                        String data2 = data + "," + infsend;
                                        area3.add(data2);
                                        break;
                                    }
                                }
                            }

                            String data10 = desk.getText().toString();
                            String data11 = waiter.getText().toString();
                            String data12 = time.getText().toString();
                            mcondition = test.child("餐點資料/" + data10 + "/" + data11 + "/waypoint");

                            list.add(data10);
                            list.add(data11);
                            list.add(data12);
                            mcondition.setValue(list);

                            mcondition = test.child("A區/" + data10);
                            mcondition.setValue(area1);

                            mcondition = test.child("B區/" + data10);
                            mcondition.setValue(area2);

                            mcondition = test.child("C區/" + data10);
                            mcondition.setValue(area3);

                            Intent intent=new Intent();
                            intent.setClass(order.this,MainActivity.class);

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

    private Button.OnClickListener btnDoClick4 = new Button.OnClickListener(){
        public void onClick(View v){
            Intent intent=new Intent();
            intent.setClass(order.this,MainActivity.class);

            startActivity(intent);
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
