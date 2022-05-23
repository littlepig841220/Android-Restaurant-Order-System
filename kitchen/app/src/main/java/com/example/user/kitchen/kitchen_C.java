package com.example.user.kitchen;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by USER on 2017/8/27.
 */

public class kitchen_C extends AppCompatActivity {
    TextView ckeck,quantity1,quantity2,quantity3,quantity4,quantity5,quantity6;
    ListView show;
    Button change1,change2,change3,monitor,reflash;

    int n0,n1,n2=0;

    ArrayList<String> list=new ArrayList<>();
    ArrayAdapter<String> adapter;

    DatabaseReference dref,deletedref,uploaddref,downloaddref,updatedref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kitchen3);

        ckeck = (TextView)findViewById(R.id.textView);
        show=(ListView)findViewById(R.id.Listview);
        quantity1 = (TextView)findViewById(R.id.textView3);
        quantity2 = (TextView)findViewById(R.id.textView5);
        quantity3 = (TextView)findViewById(R.id.textView7);
        quantity4 = (TextView)findViewById(R.id.textView8);
        quantity5 = (TextView)findViewById(R.id.textView9);
        quantity6 = (TextView)findViewById(R.id.textView11);
        change1 = (Button)findViewById(R.id.button);
        change2 = (Button)findViewById(R.id.button1);
        change3 = (Button)findViewById(R.id.button2);
        monitor = (Button)findViewById(R.id.button3);
        reflash = (Button)findViewById(R.id.button4);

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,list);

        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        String area=bundle.getString("AREA");
        ckeck.setText(area);

        dref= FirebaseDatabase.getInstance().getReference(area);
        downloaddref= FirebaseDatabase.getInstance().getReference("及時統計資料/備料中");

        show.setOnItemClickListener(listViewOnItemClickListener);
        change1.setOnClickListener(buttonclicklistener);
        change2.setOnClickListener(buttonclicklistener);
        change3.setOnClickListener(buttonclicklistener);
        monitor.setOnClickListener(monitorlistner);
        reflash.setOnClickListener(reflashlistner);


        dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    String key = postSnapshot.getKey().toString();
                    String data1 = postSnapshot.getValue().toString();
                    String data2[] = data1.split(",");

                    list.add(data1 + "," + key);
                    show.setAdapter(adapter);
                    if (data2[0].equals("紅茶")){
                        n0++;
                        String AC = String.valueOf(n0);
                        quantity1.setText(AC);
                        uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/下單中/紅茶");
                        uploaddref.setValue(AC);
                    }
                    else if (data2[0].equals("咖啡")){
                        n1++;
                        String AC = String.valueOf(n1);
                        quantity2.setText(AC);
                        uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/下單中/咖啡");
                        uploaddref.setValue(AC);
                    }
                    else if (data2[0].equals("蔬菜湯")){
                        n2++;
                        String AC = String.valueOf(n2);
                        quantity3.setText(AC);
                        uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/下單中/蔬菜湯");
                        uploaddref.setValue(AC);
                    }
                }
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

        downloaddref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String point = dataSnapshot.getKey().toString();
                if (point.equals("紅茶")){
                    String AC = dataSnapshot.getValue().toString();
                    quantity4.setText(AC);
                }
                else if (point.equals("咖啡")){
                    String AC = dataSnapshot.getValue().toString();
                    quantity5.setText(AC);
                }
                else if (point.equals("蔬菜湯")){
                    String AC = dataSnapshot.getValue().toString();
                    quantity6.setText(AC);
                }
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
        valuecheck();
    }

    private ListView.OnItemClickListener listViewOnItemClickListener
            = new ListView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {

            String strSelectedItem = parent.getItemAtPosition(position).toString();

            list.remove(strSelectedItem);
            adapter.notifyDataSetChanged();

            String[] data = strSelectedItem.split(",");
            String areadata = ckeck.getText().toString();

            deletedref = FirebaseDatabase.getInstance().getReference(areadata + "/" + data[1]);
            deletedref.child(data[3]).removeValue();

            if (data[0].equals("紅茶")){
                n0--;
                String AC = String.valueOf(n0);
                quantity1.setText(AC);
                uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/下單中/紅茶");
                uploaddref.setValue(AC);

                int m0 = Integer.valueOf(quantity4.getText().toString());
                m0--;
                AC = String.valueOf(m0);
                quantity4.setText(AC);
                updatedref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/備料中/紅茶");
                updatedref.setValue(AC);
            }
            else if (data[0].equals("咖啡")){
                n1--;
                String AC = String.valueOf(n1);
                quantity2.setText(AC);
                uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/下單中/咖啡");
                uploaddref.setValue(AC);

                int m1 = Integer.valueOf(quantity5.getText().toString());
                m1--;
                AC = String.valueOf(m1);
                quantity5.setText(AC);
                updatedref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/備料中/咖啡");
                updatedref.setValue(AC);
            }
            else if (data[0].equals("蔬菜湯")){
                n2--;
                String AC = String.valueOf(n2);
                quantity3.setText(AC);
                uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/下單中/蔬菜湯");
                uploaddref.setValue(AC);

                int m2 = Integer.valueOf(quantity6.getText().toString());
                m2--;
                AC = String.valueOf(m2);
                quantity6.setText(AC);
                updatedref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/備料中/蔬菜湯");
                updatedref.setValue(AC);
            }
            determine();
        }};

    public Button.OnClickListener buttonclicklistener = new Button.OnClickListener(){
        @Override
        public void onClick(View v){

            switch (v.getId()) {
                case R.id.button: {
                    AlertDialog.Builder editDialog = new AlertDialog.Builder(kitchen_C.this);
                    editDialog.setTitle("更變紅茶數量(請直接輸入增加的數量)");

                    final EditText editText = new EditText(kitchen_C.this);

                    editText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_CLASS_NUMBER);
                    editDialog.setView(editText);

                    editDialog.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                        // do something when the button is clicked
                        public void onClick(DialogInterface arg0, int arg1) {

                            int value = Integer.valueOf(quantity4.getText().toString());
                            int value2 = Integer.valueOf(editText.getText().toString());
                            int ansvalue = value + value2;

                            updatedref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/備料中/紅茶");
                            updatedref.setValue(ansvalue);

                            String s = String.valueOf(ansvalue);
                            quantity4.setText(s);

                            determine();
                        }
                    });
                    editDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        // do something when the button is clicked
                        public void onClick(DialogInterface arg0, int arg1) {
                            //...
                        }
                    });
                    editDialog.show();
                    break;
                }
                case R.id.button1: {
                    AlertDialog.Builder editDialog = new AlertDialog.Builder(kitchen_C.this);
                    editDialog.setTitle("更變咖啡數量(請直接輸入增加的數量)");

                    final EditText editText = new EditText(kitchen_C.this);

                    editText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_CLASS_NUMBER);
                    editDialog.setView(editText);

                    editDialog.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                        // do something when the button is clicked
                        public void onClick(DialogInterface arg0, int arg1) {

                            int value = Integer.valueOf(quantity5.getText().toString());
                            int value2 = Integer.valueOf(editText.getText().toString());
                            int ansvalue = value + value2;

                            updatedref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/備料中/咖啡");
                            updatedref.setValue(ansvalue);

                            String s = String.valueOf(ansvalue);
                            quantity5.setText(s);

                            determine();
                        }
                    });
                    editDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        // do something when the button is clicked
                        public void onClick(DialogInterface arg0, int arg1) {
                            //...
                        }
                    });
                    editDialog.show();
                    break;
                }
                case R.id.button2:{
                    AlertDialog.Builder editDialog = new AlertDialog.Builder(kitchen_C.this);
                    editDialog.setTitle("更變蔬菜湯數量(請直接輸入增加的數量)");

                    final EditText editText = new EditText(kitchen_C.this);

                    editText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_CLASS_NUMBER);
                    editDialog.setView(editText);

                    editDialog.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                        // do something when the button is clicked
                        public void onClick(DialogInterface arg0, int arg1) {

                            int value = Integer.valueOf(quantity6.getText().toString());
                            int value2 = Integer.valueOf(editText.getText().toString());
                            int ansvalue = value + value2;

                            updatedref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/備料中/蔬菜湯");
                            updatedref.setValue(ansvalue);

                            String s = String.valueOf(ansvalue);
                            quantity6.setText(s);

                            determine();
                        }
                    });
                    editDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        // do something when the button is clicked
                        public void onClick(DialogInterface arg0, int arg1) {

                        }
                    });
                    editDialog.show();
                    break;
                }
            }
        }
    };

    public Button.OnClickListener monitorlistner = new Button.OnClickListener(){
        @Override
        public void onClick(View v){
            determine();
        }
    };

    public Button.OnClickListener reflashlistner = new Button.OnClickListener(){
        @Override
        public void onClick(View v){
            recreate();
        }
    };

    void determine(){
        int A = Integer.valueOf(quantity1.getText().toString());
        int B = Integer.valueOf(quantity2.getText().toString());
        int C = Integer.valueOf(quantity3.getText().toString());
        int a = Integer.valueOf(quantity4.getText().toString());
        int b = Integer.valueOf(quantity5.getText().toString());
        int c = Integer.valueOf(quantity6.getText().toString());

        if (a-A>=3){
            change1.setBackgroundColor(Color.parseColor("#00DB00"));
            uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/數量狀態/紅茶");
            uploaddref.setValue("3");
        }
        else if (a-A>=1 & a-A<3){
            change1.setBackgroundColor(Color.parseColor("#F9F900"));
            uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/數量狀態/紅茶");
            uploaddref.setValue("2");
        }
        else if (a-A<1){
            change1.setBackgroundColor(Color.parseColor("#FF0000"));
            uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/數量狀態/紅茶");
            uploaddref.setValue("1");
        }
        if (b-B>=3){
            change2.setBackgroundColor(Color.parseColor("#00DB00"));
            uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/數量狀態/咖啡");
            uploaddref.setValue("3");
        }
        else if (b-B>=1 & b-B<3){
            change2.setBackgroundColor(Color.parseColor("#F9F900"));
            uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/數量狀態/咖啡");
            uploaddref.setValue("2");
        }
        else if (b-B<1){
            change2.setBackgroundColor(Color.parseColor("#FF0000"));
            uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/數量狀態/咖啡");
            uploaddref.setValue("1");
        }
        if (c-C>=3){
            change3.setBackgroundColor(Color.parseColor("#00DB00"));
            uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/數量狀態/蔬菜湯");
            uploaddref.setValue("3");
        }
        else if (c-C>=1 & c-C<3){
            change3.setBackgroundColor(Color.parseColor("#F9F900"));
            uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/數量狀態/蔬菜湯");
            uploaddref.setValue("2");
        }
        else if (c-C<1){
            change3.setBackgroundColor(Color.parseColor("#FF0000"));
            uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/數量狀態/蔬菜湯");
            uploaddref.setValue("1");
        }
    }

    void valuecheck(){
        if (quantity1.getText().equals("載入中...")){
            quantity1.setText("0");
            uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/下單中/紅茶");
            uploaddref.setValue("0");
        }
        if (quantity2.getText().equals("載入中...")){
            quantity2.setText("0");
            uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/下單中/咖啡");
            uploaddref.setValue("0");
        }
        if (quantity3.getText().equals("載入中...")){
            quantity3.setText("0");
            uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/下單中/蔬菜湯");
            uploaddref.setValue("0");
        }
        if (quantity4.getText().equals("載入中...")){
            quantity4.setText("0");
        }
        if (quantity5.getText().equals("載入中...")){
            quantity5.setText("0");
        }
        if (quantity6.getText().equals("載入中...")){
            quantity6.setText("0");
        }
    }
}
