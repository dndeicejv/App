package com.example.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewoneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newone);
        ConnectionClass connectionClass = new ConnectionClass();
        Function f = new Function();
        Button back = (Button)findViewById(R.id.quit);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewoneActivity.this.finish();
            }
        });

        Button next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Function f = new Function();
                f.finishActivity();
                NewoneActivity.this.finish();
            }
        });

    }
}
