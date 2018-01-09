package com.example.app;

import android.app.Application;


public class Insert extends Application {
    String id;
    String date;
    String day;
    String user;
    String floor;
    String time;
    String place;
    String classs;
    String msg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void insertsql(
            String id,
            String date,
            String day,
            String user,
            String floor,
            String time,
            String place,
            String classs,
            String msg
    ){
        ConnectionClass connectionClass =new ConnectionClass();
        this.id = id;
        this.date = date;
        this.day = day;
        this.user = user;
        this.floor = floor;
        this.time = time;
        this.place = place;
        this.classs =classs;
        this.msg = msg;
    }
}
