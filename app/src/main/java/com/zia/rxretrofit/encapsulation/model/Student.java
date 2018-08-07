package com.zia.rxretrofit.encapsulation.model;

import android.databinding.BaseObservable;

import com.zia.rxretrofit.BR;

import java.util.List;

public class Student extends BaseObservable{

    private String classNum;
    private String studentId;
    private String name;
    private String sex;
    private String classId;
    private String zyh;
    private String major;
    private String college;
    private String year;
    private String atSchool;

    @Override
    public String toString() {
        return "DataBean{" +
                "classNum='" + classNum + '\'' +
                ", studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", classId='" + classId + '\'' +
                ", zyh='" + zyh + '\'' +
                ", major='" + major + '\'' +
                ", college='" + college + '\'' +
                ", year='" + year + '\'' +
                ", atSchool='" + atSchool + '\'' +
                '}';
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.stu);
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getZyh() {
        return zyh;
    }

    public void setZyh(String zyh) {
        this.zyh = zyh;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAtSchool() {
        return atSchool;
    }

    public void setAtSchool(String atSchool) {
        this.atSchool = atSchool;
    }


    public static class StudentListWrapper extends ZzziaListWrapper<Student> {
        @Override
        public List<Student> getData() {
            return super.getData();
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}

