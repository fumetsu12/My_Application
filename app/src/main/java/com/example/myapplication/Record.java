package com.example.myapplication;

import java.util.List;

public class Record {
    private String current_time;
    private List<String> timelist;

    public Record(String current_time) {
        this.current_time = current_time;
    }

    public Record(String current_time, List<String> timelist) {
        this.current_time = current_time;
        this.timelist = timelist;
    }

    public String getCurrent_time() {
        return current_time;
    }

    public List<String> getTimelist() {
        return timelist;
    }

    public void setCurrent_time(String current_time) {
        this.current_time = current_time;
    }

    public void setTimelist(List<String> timelist) {
        this.timelist = timelist;
    }

}

