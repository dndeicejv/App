package com.example.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Search_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_);

         ArrayList<String> arrayList  =new ArrayList<String>();
        final ArrayList<String> number  =new ArrayList<String>();
        ConnectionClass connectionClass = new ConnectionClass();
        final  Insert is = (Insert) getApplicationContext();

        ListView src = (ListView)findViewById(R.id.src_list);
        Button quit = (Button)findViewById(R.id.quit);
        Button next = (Button)findViewById(R.id.next);

        ArrayAdapter   adapter  =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
                src.setAdapter(adapter);
        search(connectionClass,arrayList,number,adapter,is.getSql());




        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Search_Activity.this.finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =new Intent();
                intent.setClass(Search_Activity.this,StringActivity.class);
                startActivity(intent);
                Search_Activity.this.finish();

            }
        });


        src.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                is.setIdp(number.get(index));
                Intent intent = new Intent();
                intent.setClass(Search_Activity.this,Detail_Activity.class);
                startActivity(intent);

            }
        });
   }




    private void search(ConnectionClass connectionClass,ArrayList arrayList,ArrayList number,ArrayAdapter adapter,String string){
        try{

            Connection con =  connectionClass.CONN();
            if(con==null ){
                Toast.makeText(Search_Activity.this,"Error in Connection  with SQL server", Toast.LENGTH_LONG).show();}
            else{
                String query  =string   ;

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()){
                    String newitem = "日期:"+"\t"+rs.getString("日期")+"\n"+
                            "輪值者:"+"\t"+rs.getString("輪值者編號")+"\n"+
                            "位置:"+"\t"+rs.getString("位置");
                    String num = rs.getString("編號");

                    arrayList.add(newitem);
                    number.add(num);
                    adapter.notifyDataSetChanged();
                }


            }
        }catch (Exception e) {
            Toast.makeText(Search_Activity.this,e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }

    }
}


