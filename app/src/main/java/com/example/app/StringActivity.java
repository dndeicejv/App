package com.example.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StringActivity extends AppCompatActivity {
    ArrayList<String> arrayList2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string);

         final Spinner spinner = (Spinner)findViewById(R.id.spinner);
         final Spinner place = (Spinner)findViewById(R.id.spinner2);
        final DatePicker datePicker = (DatePicker)findViewById(R.id.datepicker);
        Button button = (Button)findViewById(R.id.btn);
        ConnectionClass connectionClass = new ConnectionClass();
        final  Insert is = (Insert) getApplicationContext();
        Function f = new Function();
        final ArrayList<String> arrayList = f.setlist(connectionClass,"Floor",StringActivity.this,"F_Name");
        final ArrayList<String> arrayList1 = f.setlist(connectionClass,"Floor",StringActivity.this,"F_dbo");
        ArrayAdapter<String> floorlist = new ArrayAdapter<>(StringActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                arrayList);
        spinner.setAdapter(floorlist);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               if (!arrayList.get(position).toString().equals(" ")) {
                   is.setFloor(parent.getSelectedItem().toString());
                   ConnectionClass connectionClass = new ConnectionClass();
                   Function f = new Function();
                   arrayList2 = f.setlist(connectionClass,arrayList1.get(position),StringActivity.this,"位置");
                   ArrayAdapter<String> placelist = new ArrayAdapter<>(StringActivity.this,
                           android.R.layout.simple_spinner_dropdown_item,
                           arrayList2);
                   place.setAdapter(placelist);
               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        place.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    is.setPlace(parent.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = datePicker.getYear();
                int month = datePicker.getMonth()+1;
                int day = datePicker.getDayOfMonth();
                String cdate = Integer.toString(year) + "-" + String.format("%02d",month) + "-" + String.format("%02d",day);
                is.setSql("SELECT *  FROM Detail WHERE 日期 = '" +cdate+"' and 樓層 = '" + is.getFloor()+"' and 位置 = '"+is.getPlace()+"'order by 日期 DESC");

                Intent intent  =new Intent();
                intent.setClass(StringActivity.this,Search_Activity.class);
                startActivity(intent);
                StringActivity.this.finish();
            }
        });

    }

}
