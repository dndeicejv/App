package com.example.app;


import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Function {
    private static New_Activity act;

    public ArrayList<String> setlist(ConnectionClass connectionClass, String dbo, Activity activity,String column){
        ArrayList<String> arrayList = new ArrayList<String>();
    try{
        Connection con =  connectionClass.CONN();
        if(con==null )
            Toast.makeText(activity,"Error in Connection  with SQL server", Toast.LENGTH_LONG).show();

        else{
            String query  ="SELECT *  FROM "+dbo;

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                String newitem = rs.getString(column);
                arrayList.add(newitem);
            }


        }
    }catch (Exception e) {
        Toast.makeText(activity,e.getMessage().toString(), Toast.LENGTH_LONG).show();

    }
    return arrayList;
    }

    public static void finishActivity() {
        if (act != null) {
            act.finish();
        }

}}
