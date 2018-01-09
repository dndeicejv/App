package com.example.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Hyper_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hyper_);
        final GlobalVariable gv = (GlobalVariable)getApplicationContext();

                Button user_btn =(Button)findViewById(R.id.user_btn);
                Button search_btn = (Button)findViewById(R.id.search_btn);
                Button new_btn = (Button)findViewById(R.id.neww_btn);

        user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Hyper_Activity.this,User_Activity.class);
                startActivity(intent);
            }
        });
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gv.setsql("SELECT *  FROM Detail");
                Intent intent = new Intent();
                intent.setClass(Hyper_Activity.this,Search_Activity.class);
                startActivity(intent);
            }
        });
        new_btn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Hyper_Activity.this,New_Activity.class);
                startActivity(intent);
            }
        });
    }
}
