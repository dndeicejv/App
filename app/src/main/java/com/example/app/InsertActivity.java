package com.example.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class InsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        final  Insert is = (Insert) getApplicationContext();
        ConnectionClass connectionClass = new ConnectionClass();
        Function f = new Function();

        Button back = (Button)findViewById(R.id.quit_btn);
        Button next = (Button)findViewById(R.id.next_btn);
        Spinner floor  = (Spinner)findViewById(R.id.floorspin);
        final Spinner place  = (Spinner)findViewById(R.id.placespin);
        Spinner event  = (Spinner)findViewById(R.id.eventspin);

        final ArrayList<String> dbolist = f.setlist(connectionClass,"Floor",InsertActivity.this,"F_dbo");
        ArrayAdapter<String> flooradapter = f.setspinner(InsertActivity.this,"Floor","F_Name");
        floor.setAdapter(flooradapter);
        ArrayAdapter<String> eventadapter  =f.setspinner(InsertActivity.this,"Event","類型名稱");
        event.setAdapter(eventadapter);

        floor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Function f = new Function();
                is.setFloor(parent.getSelectedItem().toString());
                ArrayAdapter<String> placeadapter = f.setspinner(InsertActivity.this,dbolist.get(position).toString(),"位置");
                place.setAdapter(placeadapter);
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
        event.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                is.setClasss(parent.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(InsertActivity.this,NewoneActivity.class);
                startActivity(intent);
                InsertActivity.this.finish();

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertActivity.this.finish();
            }
        });

    }
}
