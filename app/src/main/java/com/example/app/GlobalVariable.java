package com.example.app;


import android.app.Application;

public class GlobalVariable extends Application {
    private String id;
    private String sql = "SELECT *  FROM Detail ";
    private String place;
    private String floor;
    public void setId(String id ){
        this.id = id;
    }
    public  String getid(){
        return id;
    }
    public  void setsql(String sql){
        this.sql = sql;
    }
    public String getsql(){
        return sql;
    }
    public void setplace(String place){
        this.place = place;
    }
    public String getplcae(){
        return place;
    }
    public void setfloor(String floor){
        this.floor = floor;
    }
    public String getfloor(){
        return floor;
    }
}
