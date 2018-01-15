package com.example.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Lower_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lower_);
        final  Insert is = (Insert) getApplicationContext();

        ImageButton user_btn =(ImageButton) findViewById(R.id.lubtn);
        ImageButton insert_btn = (ImageButton) findViewById(R.id.libtn);

        user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Lower_Activity.this,User_Activity.class);
                startActivity(intent);
            }
        });
       insert_btn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Lower_Activity.this,InsertActivity.class);
                startActivity(intent);
            }
        });
    }
}
