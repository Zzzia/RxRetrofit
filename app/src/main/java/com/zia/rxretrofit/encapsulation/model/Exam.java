package com.zia.rxretrofit.encapsulation.model;

import java.util.List;

/**
 * Created by skylineTan on 2016/4/22 19:44.
 */
public class Exam {
    public String week;
    public String weekday;
    public String student;
    public String course;
    public String classroom;
    public String status;
    public String begin_time;
    public String end_time;
    public String date;
    public String time;

    @Override
    public String toString() {
        return "Exam{" +
                "week='" + week + '\'' +
                ", weekday='" + weekday + '\'' +
                ", student='" + student + '\'' +
                ", course='" + course + '\'' +
                ", classroom='" + classroom + '\'' +
                ", status='" + status + '\'' +
                ", begin_time='" + begin_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static class ExamWrapper extends RedRockWrapper<List<Exam>> {
        @Override
        public List<Exam> getData() {
            return super.getData();
        }
    }
}
