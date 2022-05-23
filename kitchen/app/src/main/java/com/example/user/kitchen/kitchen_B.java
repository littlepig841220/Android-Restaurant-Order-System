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

public class kitchen_B extends AppCompatActivity {

    TextView ckeck,quantity1,quantity2,quantity3,quantity4;
    ListView show;
    Button change1,change2,monitor,reflash;

    int n0,n1=0;

    ArrayList<String> list=new ArrayList<>();
    ArrayAdapter<String> adapter;

    DatabaseReference dref,deletedref,uploaddref,downloaddref,updatedref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kitchen2);

        ckeck = (TextView)findViewById(R.id.textView);
        show=(ListView)findViewById(R.id.Listview);
        quantity1 = (TextView)findViewById(R.id.textView3);
        quantity2 = (TextView)findViewById(R.id.textView5);
        quantity3 = (TextView)findViewById(R.id.textView8);
        quantity4 = (TextView)findViewById(R.id.textView9);
        change1 = (Button)findViewById(R.id.button);
        change2 = (Button)findViewById(R.id.button1);
        monitor = (Button)findViewById(R.id.button3);
        reflash = (Button)findViewById(R.id.button4);

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,list);
        downloaddref= FirebaseDatabase.getInstance().getReference("及時統計資料/備料中");

        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        String area=bundle.getString("AREA");
        ckeck.setText(area);

        dref= FirebaseDatabase.getInstance().getReference(area);

        show.setOnItemClickListener(listViewOnItemClickListener);
        change1.setOnClickListener(buttonclicklistener);
        change2.setOnClickListener(buttonclicklistener);
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
                    if (data2[0].equals("炒飯")){
                        n0++;
                        String AC = String.valueOf(n0);
                        quantity1.setText(AC);
                        uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/下單中/炒飯");
                        uploaddref.setValue(AC);
                    }
                    else if (data2[0].equals("薯條")){
                        n1++;
                        String AC = String.valueOf(n1);
                        quantity2.setText(AC);
                        uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/下單中/薯條");
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
                if (point.equals("炒飯")){
                    String AC = dataSnapshot.getValue().toString();
                    quantity3.setText(AC);
                }
                else if (point.equals("薯條")){
                    String AC = dataSnapshot.getValue().toString();
                    quantity4.setText(AC);
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

            if (data[0].equals("炒飯")){
                n0--;
                String AC = String.valueOf(n0);
                quantity1.setText(AC);
                uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/下單中/炒飯");
                uploaddref.setValue(AC);

                int m0 = Integer.valueOf(quantity3.getText().toString());
                m0--;
                AC = String.valueOf(m0);
                quantity3.setText(AC);
                updatedref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/備料中/炒飯");
                updatedref.setValue(AC);
            }
            else if (data[0].equals("薯條")){
                n1--;
                String AC = String.valueOf(n1);
                quantity2.setText(AC);
                uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/下單中/薯條");
                uploaddref.setValue(AC);

                int m1 = Integer.valueOf(quantity4.getText().toString());
                m1--;
                AC = String.valueOf(m1);
                quantity4.setText(AC);
                updatedref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/備料中/薯條");
                updatedref.setValue(AC);
            }
            determine();
        }};

    public Button.OnClickListener buttonclicklistener = new Button.OnClickListener(){
        @Override
        public void onClick(View v){

            switch (v.getId()) {
                case R.id.button: {
                    AlertDialog.Builder editDialog = new AlertDialog.Builder(kitchen_B.this);
                    editDialog.setTitle("更變炒飯數量(請直接輸入增加的數量)");

                    final EditText editText = new EditText(kitchen_B.this);

                    editText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_CLASS_NUMBER);
                    editDialog.setView(editText);

                    editDialog.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                        // do something when the button is clicked
                        public void onClick(DialogInterface arg0, int arg1) {

                            int value = Integer.valueOf(quantity3.getText().toString());
                            int value2 = Integer.valueOf(editText.getText().toString());
                            int ansvalue = value + value2;

                            updatedref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/備料中/炒飯");
                            updatedref.setValue(ansvalue);

                            String s = String.valueOf(ansvalue);
                            quantity3.setText(s);

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
                    AlertDialog.Builder editDialog = new AlertDialog.Builder(kitchen_B.this);
                    editDialog.setTitle("更變薯條數量(請直接輸入增加的數量)");

                    final EditText editText = new EditText(kitchen_B.this);

                    editText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_CLASS_NUMBER);
                    editDialog.setView(editText);

                    editDialog.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                        // do something when the button is clicked
                        public void onClick(DialogInterface arg0, int arg1) {

                            int value = Integer.valueOf(quantity4.getText().toString());
                            int value2 = Integer.valueOf(editText.getText().toString());
                            int ansvalue = value + value2;

                            updatedref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/備料中/薯條");
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
        int a = Integer.valueOf(quantity3.getText().toString());
        int b = Integer.valueOf(quantity4.getText().toString());

        if (a-A>=3){
            change1.setBackgroundColor(Color.parseColor("#00DB00"));
            uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/數量狀態/炒飯");
            uploaddref.setValue("3");
        }
        else if (a-A>=1 & a-A<3){
            change1.setBackgroundColor(Color.parseColor("#F9F900"));
            uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/數量狀態/炒飯");
            uploaddref.setValue("2");
        }
        else if (a-A<1){
            change1.setBackgroundColor(Color.parseColor("#FF0000"));
            uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/數量狀態/炒飯");
            uploaddref.setValue("1");
        }
        if (b-B>=3){
            change2.setBackgroundColor(Color.parseColor("#00DB00"));
            uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/數量狀態/薯條");
            uploaddref.setValue("3");
        }
        else if (b-B>=1 & b-B<3){
            change2.setBackgroundColor(Color.parseColor("#F9F900"));
            uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/數量狀態/薯條");
            uploaddref.setValue("2");
        }
        else if (b-B<1){
            change2.setBackgroundColor(Color.parseColor("#FF0000"));
            uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/數量狀態/薯條");
            uploaddref.setValue("1");
        }
    }

    void valuecheck(){
        if (quantity1.getText().equals("載入中...")){
            quantity1.setText("0");
            uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/下單中/炒飯");
            uploaddref.setValue("0");
        }
        if (quantity2.getText().equals("載入中...")){
            quantity2.setText("0");
            uploaddref = FirebaseDatabase.getInstance().getReference("").child("及時統計資料/下單中/薯條");
            uploaddref.setValue("0");
        }
        if (quantity3.getText().equals("載入中...")){
            quantity3.setText("0");
        }
        if (quantity4.getText().equals("載入中...")){
            quantity4.setText("0");
        }
    }
}
