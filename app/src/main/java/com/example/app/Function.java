package com.example.app;


import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Function {


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


    public void insertsql(
            String id, String date, String day, String user, String floor,String time, String place, String classs, String msg
    ){
        ConnectionClass connectionClass =new ConnectionClass();
        try {
            Connection con =  connectionClass.CONN();
            PreparedStatement ps = con.prepareStatement("INSERT INTO DETAIL (編號,日期,星期,輪值者編號,樓層,時間,位置,分類,備註) VALUES(?,?,?,?,?,?,?,?,?)");
            ps.setString(1,id);
            ps.setString(2,date);
            ps.setString(3,day);
            ps.setString(4,user);
            ps.setString(5,floor);
            ps.setString(6,"test");
            ps.setString(7,place);
            ps.setString(8,classs);
            ps.setString(9,msg);
            ps.executeUpdate();
            con.close();
        }catch (Exception e){

        }

    }
    public ArrayAdapter<String> setspinner( Activity activity, String dbo, String column){
        ConnectionClass connectionClass = new ConnectionClass();
        ArrayList<String> arrayList = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity,
                android.R.layout.simple_spinner_dropdown_item,
                arrayList);

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
        return adapter;
    }
    public String getid(){
        String id = "";
        int count = 1;
        ConnectionClass connectionClass  =new ConnectionClass();
        try{
            Connection con =  connectionClass.CONN();
            String query  ="SELECT *  FROM Detail WHERE 日期 = '"+getdate()+"'";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                count++;
            }
        }catch (Exception e){

        }
        Calendar mCal = Calendar.getInstance();
        CharSequence s = DateFormat.format("yyyyMMdd", mCal.getTime());
        id = s.toString()+String.format("%02d",count);
        return id;
    }
    public String getdate(){
        Calendar mCal = Calendar.getInstance();
        CharSequence s = DateFormat.format("yyyy-MM-dd", mCal.getTime());
        String cur = s.toString();
        return cur;
    }
    public String getday(){
        Calendar mCal = Calendar.getInstance();
        int week = mCal.get(Calendar.DAY_OF_WEEK);
       String day = "";
        switch (week){
            case Calendar.SUNDAY:
                day = "星期日";
                break;
            case Calendar.MONDAY:
                day = "星期一";
                break;
            case Calendar.TUESDAY:
                day = "星期二";
                break;
            case Calendar.WEDNESDAY:
                day = "星期三";
                break;
            case Calendar.THURSDAY:
                day = "星期四";
                break;
            case Calendar.FRIDAY:
                day = "星期五";
                break;
            case Calendar.SATURDAY:
                day = "星期六";
                break;
        }

        return day;
    }

}

