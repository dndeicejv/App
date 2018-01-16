package com.example.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Hyper_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hyper_);

        getSupportActionBar().hide();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        final  Insert is = (Insert) getApplicationContext();

        ImageButton user_btn =(ImageButton)findViewById(R.id.user_btn);
        ImageButton search_btn = (ImageButton)findViewById(R.id.search_btn);
        ImageButton new_btn = (ImageButton)findViewById(R.id.neww_btn);

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
                is.setSql("SELECT  TOP(20) *  FROM Detail order by 日期 DESC");
                Intent intent = new Intent();
                intent.setClass(Hyper_Activity.this,Search_Activity.class);
                startActivity(intent);
            }
        });
        new_btn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Hyper_Activity.this,InsertActivity.class);
                startActivity(intent);
            }
        });
    }
}
