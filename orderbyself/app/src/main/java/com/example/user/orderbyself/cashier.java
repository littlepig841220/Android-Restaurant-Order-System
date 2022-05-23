package com.example.user.orderbyself;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by USER on 2017/11/22.
 */

public class cashier extends AppCompatActivity {

    TextView time, waiter, table, name, price2, total;
    Button comfirm, canncel;

    DatabaseReference downloaddref,updatedref,deletdref;

    DatabaseReference procer = FirebaseDatabase.getInstance().getReference();

    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<Integer> price = new ArrayList<>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cashier);

        time = (TextView) findViewById(R.id.data1);
        waiter = (TextView) findViewById(R.id.data2);
        table = (TextView) findViewById(R.id.data3);
        name = (TextView) findViewById(R.id.name);
        price2 = (TextView) findViewById(R.id.price);
        total = (TextView) findViewById(R.id.total);
        comfirm = (Button) findViewById(R.id.confirm);
        canncel = (Button) findViewById(R.id.canncel);

        comfirm.setOnClickListener(next);
        canncel.setOnClickListener(back);

        Intent intent = this.getIntent();
        Bundle bundle2 = intent.getExtras();
        String tablenumber = bundle2.getString("NUMBER");
        String waitername = bundle2.getString("NAME");

        downloaddref = FirebaseDatabase.getInstance().getReference("餐點資料/" + tablenumber + "/" + waitername);

        downloaddref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String value = postSnapshot.getValue().toString();
                    list.add(value);
                    list2.add(value);
                }
                test();
                determind();
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
        determind();
    }
    @Override
        public void onBackPressed(){
            Toast Warnings1 = Toast.makeText(cashier.this,"操作錯誤!!", Toast.LENGTH_LONG);
            Warnings1.show();
        }

    void test() {
        TableLayout aa = (TableLayout) findViewById(R.id.layout);
        int m = list.size();
        time.setText(list.get(m - 1));
        waiter.setText(list.get(m - 2));
        table.setText(list.get(m - 3));

        list.remove(m - 1);
        list.remove(m - 2);
        list.remove(m - 3);
        list.trimToSize();
        int n = list.size();
        String namelist = "";
        String pricelist = "";

        for (int i = 0; i < n; i++) {
            if (list.get(i).equals("全雞")) {
                namelist = namelist + list.get(i) + "\n";
                pricelist = pricelist + "120" + "\n";
                price.add(120);
                name.setText(namelist);
                price2.setText(pricelist);
            } else if (list.get(i).equals("半雞")) {
                namelist = namelist + list.get(i) + "\n";
                pricelist = pricelist + "100" + "\n";
                price.add(100);
                name.setText(namelist);
                price2.setText(pricelist);
            } else if (list.get(i).equals("雞腿")) {
                namelist = namelist + list.get(i) + "\n";
                pricelist = pricelist + "80" + "\n";
                price.add(80);
                name.setText(namelist);
                price2.setText(pricelist);
            } else if (list.get(i).equals("炒飯")) {
                namelist = namelist + list.get(i) + "\n";
                pricelist = pricelist + "60" + "\n";
                price.add(60);
                name.setText(namelist);
                price2.setText(pricelist);
            } else if (list.get(i).equals("薯條")) {
                namelist = namelist + list.get(i) + "\n";
                pricelist = pricelist + "40" + "\n";
                price.add(40);
                name.setText(namelist);
                price2.setText(pricelist);
            } else if (list.get(i).equals("紅茶")) {
                namelist = namelist + list.get(i) + "\n";
                pricelist = pricelist + "20" + "\n";
                price.add(20);
                name.setText(namelist);
                price2.setText(pricelist);
            } else if (list.get(i).equals("咖啡")) {
                namelist = namelist + list.get(i) + "\n";
                pricelist = pricelist + "40" + "\n";
                price.add(40);
                name.setText(namelist);
                price2.setText(pricelist);
            } else if (list.get(i).equals("蔬菜湯")) {
                namelist = namelist + list.get(i) + "\n";
                pricelist = pricelist + "35" + "\n";
                price.add(35);
                name.setText(namelist);
                price2.setText(pricelist);
            }
        }

        int x = price.size();
        int y = 0;
        for (int i = 0; i < x; i++) {
            y = y + price.get(i);
            total.setText(String.valueOf(y));
        }
    }

    void determind(){
        if (list.isEmpty() == true) {
            comfirm.setEnabled(false);
        }
        else{
            comfirm.setEnabled(true);
        }
    };

    private Button.OnClickListener next = new Button.OnClickListener() {
        public void onClick(View v) {

            new AlertDialog.Builder(cashier.this).setTitle("餐點確認").setIcon(R.mipmap.ic_launcher).setMessage("確定後結帳")
                    .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String s1 = time.getText().toString();
                            String[] data = s1.split(":");

                            updatedref = procer.child("本日資料/" + data[0] + "/" + s1);

                            updatedref.setValue(list2);

                            deletdref = FirebaseDatabase.getInstance().getReference("餐點資料/" + table.getText().toString() + "/" + waiter.getText().toString());

                            deletdref.removeValue();

                            Intent intent=new Intent();
                            intent.setClass(cashier.this,MainActivity.class);

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

    private Button.OnClickListener back = new Button.OnClickListener() {
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(cashier.this,MainActivity.class);

            startActivity(intent);
        }
    };
}