package com.example.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;

public class SearchString extends AppCompatActivity {
     int pos;

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_string);

        final LinearLayout datelayout = (LinearLayout)findViewById(R.id.datelayout);
        final LinearLayout placelayout = (LinearLayout)findViewById(R.id.placelayout);
        final LinearLayout idlayout = (LinearLayout)findViewById(R.id.idlayout);
        final LinearLayout emptylayout = (LinearLayout)findViewById(R.id.emptylayout);
        final int pos;
        final ArrayList<String> eventlist = new ArrayList<String>();
        eventlist.add("時間");
        eventlist.add("位置");
        eventlist.add("編號");


        final  Insert is = (Insert) getApplicationContext();
        final Function f = new Function();
        final Button src = (Button)findViewById(R.id.src);
        Button next = (Button)findViewById(R.id.yes);
        final EditText editText = (EditText)findViewById(R.id.edittext1);
        final DatePicker datePicker1 = (DatePicker)findViewById(R.id.datepicker1);
        final DatePicker datePicker2 = (DatePicker)findViewById(R.id.datepicker2);

        Spinner floor = (Spinner)findViewById(R.id.spinner1);
        final  Spinner place = (Spinner)findViewById(R.id.spinner2);
        final ArrayList placelist = f.setlist(SearchString.this,"Floor","F_dbo");
        floor.setAdapter(f.setspinner(SearchString.this,"Floor","F_Name"));

        floor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                is.setFloor(parent.getSelectedItem().toString());
                place.setAdapter(f.setspinner(SearchString.this,placelist.get(position).toString(),"位置"));
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



        src.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(SearchString.this)
                        .setSingleChoiceItems(eventlist.toArray(new String[eventlist.size()]), 0,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setPos(which);
                            }
                        })
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (eventlist.get(getPos()).toString()){
                                    case "時間":
                                        datelayout.setVisibility(View.VISIBLE);
                                        placelayout.setVisibility(View.GONE);
                                        idlayout.setVisibility(View.GONE);
                                        emptylayout.setVisibility(View.GONE);
                                        src.setText("時間");

                                        break;
                                    case "位置":
                                        datelayout.setVisibility(View.GONE);
                                        placelayout.setVisibility(View.VISIBLE);
                                        idlayout.setVisibility(View.GONE);
                                        emptylayout.setVisibility(View.GONE);
                                        src.setText("位置");
                                        break;
                                    case "編號":
                                        datelayout.setVisibility(View.GONE);
                                        placelayout.setVisibility(View.GONE);
                                        idlayout.setVisibility(View.VISIBLE);
                                        emptylayout.setVisibility(View.GONE);
                                        src.setText("編號");
                                        break;
                                }
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (src.getText().toString()){
                    case "時間":
                        int year = datePicker1.getYear();
                        int month = datePicker2.getMonth()+1;
                        int day = datePicker2.getDayOfMonth();
                        String cdate = Integer.toString(year) + "-" + String.format("%02d",month) + "-" + String.format("%02d",day);
                        year = datePicker2.getYear();
                        month = datePicker2.getMonth()+1;
                        day = datePicker2.getDayOfMonth();
                        String ddate = Integer.toString(year) + "-" + String.format("%02d",month) + "-" + String.format("%02d",day);
                        String sql = "select * from Detail where convert(datetime,日期) >= convert(datetime,'"+cdate+"') and convert(datetime,日期) <= convert(datetime,'"+ddate+"') order by 日期 DESC";
                        is.setSql(sql);
                        break;
                    case "位置":
                        is.setSql("select * from Detail where 樓層 = '"+is.getFloor()+"' and 位置 ='"+is.getPlace()+"'order by 日期 DESC");
                        break;
                    case "編號":
                        is.setSql("select * from Detail where 編號='"+editText.getText().toString()+"'");
                        break;
                }

                Intent intent  =new Intent();
                intent.setClass(SearchString.this,Search_Activity.class);
                startActivity(intent);
                SearchString.this.finish();
            }
        });

    }

}
