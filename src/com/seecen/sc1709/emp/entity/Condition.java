package com.seecen.sc1709.emp.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ASUS on 2018/1/21.
 */
public class Condition {
    private String startTime;
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public Date getStartDate(){
        if(startTime!=null && startTime.length()>0){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return df.parse(startTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public Date getEndDate(){
        if(endTime!=null && endTime.length()>0){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return df.parse(endTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
