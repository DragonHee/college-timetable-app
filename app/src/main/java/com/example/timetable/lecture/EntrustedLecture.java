package com.example.timetable.lecture;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EntrustedLecture {

    @SerializedName("Items")
    private ArrayList<String> items;
    @SerializedName("Count")
    private int count;
    @SerializedName("ScannedCount")
    private int scannedCount;

    public ArrayList<String> getItems() {
        return items;
    }

    public void setItems(ArrayList<String> items) {
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
