package com.example.app;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    String driver = "net.sourceforge.jtds.jdbc.Driver";
    String url  ="jdbc:jtds:sqlserver://114.44.135.30:1433/App";
    String account  = "owner";
    String password = "owner";


    @SuppressLint("NewApi")
       public Connection CONN(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {

            Class.forName(driver);

            conn = DriverManager.getConnection(url,account,password);
        }catch (Exception e){
            Log.e("ERRO",e.getMessage());
        }
        return  conn;
    }

}
