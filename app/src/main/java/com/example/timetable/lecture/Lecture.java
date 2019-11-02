package com.example.timetable.lecture;

import java.util.ArrayList;

public class Lecture {
    private String code;
    private String lecture;
    private String professor;
    private String location;
    private String start_time;
    private String end_time;
    private ArrayList<String> dayofweek;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public ArrayList<String> getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(ArrayList<String> dayofweek) {
        this.dayofweek = dayofweek;
    }
}
