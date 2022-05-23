package com.example.user.kitchen;

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

public class MainActivity extends AppCompatActivity {

    Button next;
    Spinner waiter;
    TextView test;

    String[] name= new String[] {"A區","B區","C區"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next = (Button)findViewById(R.id.button);
        waiter = (Spinner)findViewById(R.id.spinner);
        test = (TextView)findViewById(R.id.textView5);

        ArrayAdapter<String> adaptername=new ArrayAdapter<String>
                (this,android.R.layout.simple_spinner_item,name);

        adaptername.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        waiter.setAdapter(adaptername);

        next.setOnClickListener(btnDoClick);
        waiter.setOnItemSelectedListener(spnPreferListener);
    }

    private Button.OnClickListener btnDoClick = new Button.OnClickListener(){
        public void onClick(View v){

            String area = test.getText().toString();
            Intent intent=new Intent();

            switch (area){
                case "A區":
                    intent.setClass(MainActivity.this,kitchen_A.class);
                    break;
                case "B區":
                    intent.setClass(MainActivity.this,kitchen_B.class);
                    break;
                case "C區":
                    intent.setClass(MainActivity.this,kitchen_C.class);
                    break;
            }
            Bundle bundle=new Bundle();
            bundle.putString("AREA", area);
            intent.putExtras(bundle);
            startActivity(intent);

        }
    };

    private Spinner.OnItemSelectedListener spnPreferListener=
            new Spinner.OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String sel=parent.getSelectedItem().toString();
                    test.setText(sel);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub
                }
            };
}
