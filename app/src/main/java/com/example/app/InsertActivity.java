package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class InsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        final  Insert is = (Insert) getApplicationContext();
        final Activity activity  =this;
        ConnectionClass connectionClass = new ConnectionClass();
        Function f = new Function();

        Button back = (Button)findViewById(R.id.quit_btn);
        Button next = (Button)findViewById(R.id.next_btn);
        Button qr  = (Button)findViewById(R.id.qr);
        final Spinner floor  = (Spinner)findViewById(R.id.floorspin);
        final Spinner place  = (Spinner)findViewById(R.id.placespin);
        Spinner event  = (Spinner)findViewById(R.id.eventspin);

        final ArrayList<String> dbolist = f.setlist(InsertActivity.this,"Floor","F_dbo");
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

        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator  = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);

                integrator.setPrompt("scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this,"you cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            else{
                final  Insert is = (Insert) getApplicationContext();
                final Spinner floor  = (Spinner)findViewById(R.id.floorspin);
                final Spinner place  = (Spinner)findViewById(R.id.placespin);
                Function f = new Function();
                final String[] res =  result.getContents().toString().split("%");
                final ArrayList<String> dbolist = f.setlist(InsertActivity.this,"Floor","F_dbo");
                ArrayAdapter<String> flooradapter = f.setspinner(InsertActivity.this,"Floor","F_Name");
                floor.setAdapter(flooradapter);
                floor.setSelection(flooradapter.getPosition(res[0]));
                floor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Function f = new Function();
                        is.setFloor(parent.getSelectedItem().toString());
                        ArrayAdapter<String> placeadapter = f.setspinner(InsertActivity.this,dbolist.get(position).toString(),"位置");
                        place.setAdapter(placeadapter);
                        place.setSelection(placeadapter.getPosition(res[1]));
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

            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}
