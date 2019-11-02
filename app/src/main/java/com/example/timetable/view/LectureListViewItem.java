package com.example.timetable.view;

public class LectureListViewItem {
    private String subjectName;
    private String startTime;
    private String endTime;
    private String dayOfWeek;
    private String classCode;
    private String professorName;
    private String location;

    public LectureListViewItem(){

    }
    public LectureListViewItem(String subjectName, String startTime, String endTime, String dayOfWeek, String classCode, String professorName, String location) {
        this.subjectName = subjectName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = dayOfWeek;
        this.classCode = classCode;
        this.professorName = professorName;
        this.location = location;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

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

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCOde) {
        this.classCode = classCOde;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
