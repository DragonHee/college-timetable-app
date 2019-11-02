package com.example.timetable.lecture;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LectureList {
    @SerializedName("Items")
    private ArrayList<Lecture> items;
    @SerializedName("Count")
    private int count;
    @SerializedName("ScannedCount")
    private int scannedCount;

    public ArrayList<Lecture> getItems() {
        return items;
    }

    public void setItems(ArrayList<Lecture> items) {
        this.items = items;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getScannedCount() {
        return scannedCount;
    }

    public void setScannedCount(int scannedCount) {
        this.scannedCount = scannedCount;
    }
}
