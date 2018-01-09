package com.example.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class New_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_new_);
        //getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);

        ConnectionClass connectionClass = new ConnectionClass();
        Function f = new Function();
       Button back = (Button)findViewById(R.id.quit);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                New_Activity.this.finish();
            }
        });

        Button next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(New_Activity.this,NewoneActivity.class);
                startActivity(intent);
            }
        });
        Spinner floor  = (Spinner)findViewById(R.id.floor_spin);
        final Spinner place  = (Spinner)findViewById(R.id.place_spin);
        Spinner event  = (Spinner)findViewById(R.id.event_spin);
        final ArrayList<String> arrayList = f.setlist(connectionClass,"Floor",New_Activity.this,"F_Name");
        final ArrayList<String> arrayList1 = f.setlist(connectionClass,"Floor",New_Activity.this,"F_dbo");
                ArrayAdapter<String> floorlist = new ArrayAdapter<>(New_Activity.this,
                android.R.layout.simple_spinner_dropdown_item,
                arrayList);
        /*try {
            final ArrayList<String> arrayList3 = f.setlist(connectionClass,"Event",New_Activity.this,"類型名稱");
            final ArrayAdapter<String> eventlist = new ArrayAdapter<>(New_Activity.this,
                    android.R.layout.simple_spinner_dropdown_item,
                    arrayList3);
            event.setAdapter(eventlist);
        }catch(Exception e){
            Toast.makeText(New_Activity.this,e.toString(), Toast.LENGTH_LONG).show();
        }*/


        floor.setAdapter(floorlist);



        floor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ConnectionClass connectionClass = new ConnectionClass();
                    Function f = new Function();
                    final ArrayList<String> arrayList2 = f.setlist(connectionClass,arrayList1.get(position),New_Activity.this,"位置");
                    ArrayAdapter<String> placelist = new ArrayAdapter<>(New_Activity.this,
                            android.R.layout.simple_spinner_dropdown_item,
                            arrayList2);
                    place.setAdapter(placelist);
                            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


}
