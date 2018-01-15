package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.nio.IntBuffer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {
    ConnectionClass connectionClass;
    private Button qr_btn;
    private Button login_btn;
    private  EditText edtid;
    private  EditText edtpassword;

   @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       getSupportActionBar().hide();
       getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

       final  Insert is = (Insert) getApplicationContext();
        connectionClass = new ConnectionClass();
        edtid  =(EditText)findViewById(R.id.id);
        edtpassword  =(EditText)findViewById(R.id.password);
        login_btn = (Button)findViewById(R.id.login_btn);
        qr_btn =(Button)findViewById(R.id.qr_btn);

        final Activity activity  =this;
        qr_btn.setOnClickListener(new View.OnClickListener() {
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
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtid.getText().toString();
                String password = edtpassword.getText().toString();
                String message = "";
                if(id.trim().equals("")||password.trim().equals("")){
                    message = "Please Enter your ID And Password";

                }
                else{
                message = loginfun(id,password,is);

                }
                Toast.makeText(LoginActivity.this,message, Toast.LENGTH_LONG).show();
            }
        });

    }
    private  String loginfun(String id,String password,Insert is){
       String message = "";
        try{

            Connection con =  connectionClass.CONN();
            if(con==null )
                message = "Error in Connection  with SQL server";
            else{
                String query  ="SELECT *  FROM Login WHERE account = '" + id.toString()+"' and password = '"+password.toString()+"'";

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);



                if(rs.next()){
                    String idpass = rs.getString("ID");
                    is.setUser(idpass);
                    String query2 = "SELECT * FROM Employee WHERE ID ='"+idpass+"'";
                    ResultSet rs2 = stmt.executeQuery(query2);
                    if(rs2.next()){
                        message = "Welcome ~"+"\t"+rs2.getString("Name");
                    }
                    changeactivity(idpass,rs2.getString("Name"));
                }
                else{
                    message  ="Invalid Credentials";
                }
            }
        }catch (Exception e) {
            message = e.getMessage().toString();
        }
        return  message;
    };
    private  String loginfqr(String id,Insert is){
        String message = "";
        try{

            Connection con =  connectionClass.CONN();
            if(con==null )
                message = "Error in Connection  with SQL server";
            else{
                String query  ="SELECT *  FROM Login WHERE ID = '" + id.toString()+"'";

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);



                if(rs.next()){
                    String idpass = rs.getString("ID");
                    is.setUser(idpass);
                    String query2 = "SELECT * FROM Employee WHERE ID ='"+idpass+"'";
                    ResultSet rs2 = stmt.executeQuery(query2);
                    if(rs2.next()){
                        message = "Welcome ~"+"\t"+rs2.getString("Name");
                    }
                    changeactivity(idpass,rs2.getString("Name"));
                }
                else{
                    message  ="Invalid Credentials";
                }
            }
        }catch (Exception e) {
            message = e.getMessage().toString();
        }
        return  message;
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this,"you cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            else{
                final  Insert is = (Insert) getApplicationContext();
                String message = "";
                message = loginfqr(result.getContents().toString(),is);
                Toast.makeText(LoginActivity.this,message, Toast.LENGTH_LONG).show();
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    private  void changeactivity(String id,String name){
        Intent intent = new Intent();
        if(name.equals("開發者")){
            intent.setClass(LoginActivity.this,Hyper_Activity.class);
        }else if(name.equals("使用者")){
            intent.setClass(LoginActivity.this,Lower_Activity.class);
        }
        startActivity(intent);
        LoginActivity.this.finish();
    }
}
