package com.example.user.management;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView data41,data45,data49,data53,data57,data61,data65,data69;
    TextView data42,data46,data50,data54,data58,data62,data66,data70;
    TextView data43,data47,data51,data55,data59,data63,data67,data71;
    TextView data74;

    int count = 4;

    Long startTime;
    Handler aHandler = new Handler();

    DatabaseReference dref,dref2,dref3,dref23,upload,uploaddata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data41 = (TextView)findViewById(R.id.textView41);
        data45 = (TextView)findViewById(R.id.textView45);
        data49 = (TextView)findViewById(R.id.textView49);
        data53 = (TextView)findViewById(R.id.textView53);
        data57 = (TextView)findViewById(R.id.textView57);
        data61 = (TextView)findViewById(R.id.textView61);
        data65 = (TextView)findViewById(R.id.textView65);
        data69 = (TextView)findViewById(R.id.textView69);

        data42 = (TextView)findViewById(R.id.textView42);
        data46 = (TextView)findViewById(R.id.textView46);
        data50 = (TextView)findViewById(R.id.textView50);
        data54 = (TextView)findViewById(R.id.textView54);
        data58 = (TextView)findViewById(R.id.textView58);
        data62 = (TextView)findViewById(R.id.textView62);
        data66 = (TextView)findViewById(R.id.textView66);
        data70 = (TextView)findViewById(R.id.textView70);

        data43 = (TextView)findViewById(R.id.textView43);
        data47 = (TextView)findViewById(R.id.textView47);
        data51 = (TextView)findViewById(R.id.textView51);
        data55 = (TextView)findViewById(R.id.textView55);
        data59 = (TextView)findViewById(R.id.textView59);
        data63 = (TextView)findViewById(R.id.textView63);
        data67 = (TextView)findViewById(R.id.textView67);
        data71 = (TextView)findViewById(R.id.textView71);

        data74 = (TextView)findViewById(R.id.textView74);

        aHandler.post(runnable);

        dref = FirebaseDatabase.getInstance().getReference("庫存");
        dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey().toString();
                if (key.equals("全雞")){
                    String datavalue = dataSnapshot.getValue().toString();
                    data41.setText(datavalue);
                }
                else if (key.equals("半雞")){
                    String datavalue = dataSnapshot.getValue().toString();
                    data45.setText(datavalue);
                }
                else if (key.equals("雞腿")){
                    String datavalue = dataSnapshot.getValue().toString();
                    data49.setText(datavalue);
                }
                else if (key.equals("炒飯")){
                    String datavalue = dataSnapshot.getValue().toString();
                    data53.setText(datavalue);
                }
                else if (key.equals("薯條")){
                    String datavalue = dataSnapshot.getValue().toString();
                    data57.setText(datavalue);
                }
                else if (key.equals("紅茶")){
                    String datavalue = dataSnapshot.getValue().toString();
                    data61.setText(datavalue);
                }
                else if (key.equals("咖啡")){
                    String datavalue = dataSnapshot.getValue().toString();
                    data65.setText(datavalue);
                }
                else if (key.equals("蔬菜湯")){
                    String datavalue = dataSnapshot.getValue().toString();
                    data69.setText(datavalue);
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

        dref2 = FirebaseDatabase.getInstance().getReference("及時統計資料/下單中");
        dref2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey().toString();
                if (key.equals("全雞")){
                    String datavalue = dataSnapshot.getValue().toString();
                    data43.setText(datavalue);
                }
                else if (key.equals("半雞")){
                    String datavalue = dataSnapshot.getValue().toString();
                    data47.setText(datavalue);
                }
                else if (key.equals("雞腿")){
                    String datavalue = dataSnapshot.getValue().toString();
                    data51.setText(datavalue);
                }
                else if (key.equals("炒飯")){
                    String datavalue = dataSnapshot.getValue().toString();
                    data55.setText(datavalue);
                }
                else if (key.equals("薯條")){
                    String datavalue = dataSnapshot.getValue().toString();
                    data59.setText(datavalue);
                }
                else if (key.equals("紅茶")){
                    String datavalue = dataSnapshot.getValue().toString();
                    data63.setText(datavalue);
                }
                else if (key.equals("咖啡")){
                    String datavalue = dataSnapshot.getValue().toString();
                    data67.setText(datavalue);
                }
                else if (key.equals("蔬菜湯")){
                    String datavalue = dataSnapshot.getValue().toString();
                    data71.setText(datavalue);
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

        dref3 = FirebaseDatabase.getInstance().getReference("及時統計資料/備料中");
        dref3.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey().toString();
                if (key.equals("全雞")){
                    String datavalue = dataSnapshot.getValue().toString();
                    data42.setText(datavalue);
                }
                else if (key.equals("半雞")){
                    String datavalue = dataSnapshot.getValue().toString();
                    data46.setText(datavalue);
                }
                else if (key.equals("雞腿")){
                    String datavalue = dataSnapshot.getValue().toString();
                    data50.setText(datavalue);
                }
                else if (key.equals("炒飯")){
                    String datavalue = dataSnapshot.getValue().toString();
                    data54.setText(datavalue);
                }
                else if (key.equals("薯條")){
                    String datavalue = dataSnapshot.getValue().toString();
                    data58.setText(datavalue);
                }
                else if (key.equals("紅茶")){
                    String datavalue = dataSnapshot.getValue().toString();
                    data62.setText(datavalue);
                }
                else if (key.equals("咖啡")){
                    String datavalue = dataSnapshot.getValue().toString();
                    data66.setText(datavalue);
                }
                else if (key.equals("蔬菜湯")){
                    String datavalue = dataSnapshot.getValue().toString();
                    data70.setText(datavalue);
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
    }

    private Runnable runnable = new Runnable(){
        public void run() {
            if (count > 0) {
                data74.setText(Integer.toString(count-1));
                count--;
                aHandler.postDelayed(runnable, 1000);
            }else{
                //aTextView.setText("碰");
                recreate();
            }
        }
    };
    @Override
    protected void onPause() {
        super.onPause();
        if (aHandler != null) {
            aHandler.removeCallbacks(runnable);
        }
    }
}
