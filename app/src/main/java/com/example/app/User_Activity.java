package com.example.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class User_Activity extends AppCompatActivity {
    private SharedPreferences settings;
    String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_);
        ConnectionClass connectionClass = new ConnectionClass();

        Insert is = (Insert) getApplicationContext();

        String idpass = is.getUser();

        TextView name = (TextView)findViewById(R.id.Name);
        TextView department = (TextView)findViewById(R.id.department);
        TextView tel = (TextView)findViewById(R.id.tel);
        TextView time = (TextView)findViewById(R.id.logintime);

        time.setText(is.getLogin());
        try{

            Connection con =  connectionClass.CONN();
            if(con==null ){
                message = "Error in Connection  with SQL server";
            Toast.makeText(User_Activity.this,message, Toast.LENGTH_LONG).show();}
            else{
                String query  ="SELECT *  FROM Employee WHERE ID = '"+idpass+"'";

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                if(rs.next()){
                            name.setText(rs.getString("Name"));
                        department.setText(rs.getString("單位"));
                        tel.setText(rs.getString("分機"));
                }
                else{
                    message  ="Invalid Credentials";
                }
            }

        }catch (Exception e) {
            message = e.getMessage().toString();
            Toast.makeText(User_Activity.this,message, Toast.LENGTH_LONG).show();
        }

        Button logout_btn  =(Button)findViewById(R.id.logout_btn);

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(User_Activity.this,LoginActivity.class);
                startActivity(intent);
                User_Activity.this.finish();
            }
        });
        Button back_btn = (Button)findViewById(R.id.back_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User_Activity.this.finish();
            }
        });
    }
}
