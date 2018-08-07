package com.zia.rxretrofit.encapsulation.demo.retrofit.custom;

import java.util.List;

public class StudentBean {

    /**
     * code : 200
     * error : false
     * data : [{"classNum":"7","studentId":"2016211541","name":"姜子来","sex":"男","classId":"04011601","zyh":"0401","major":"计算机科学与技术","college":"计算机科学与技术学院","year":"2016","atSchool":"在校"}]
     * info : 嘤嘤嘤
     * lastUpdate : 2018/4/7
     */

    private int code;
    private boolean error;
    private String info;
    private String lastUpdate;
    private List<DataBean> data;

    @Override
    public String toString() {
        return "StudentBean{" +
                "code=" + code +
                ", error=" + error +
                ", info='" + info + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * classNum : 7
         * studentId : 2016211541
         * name : 姜子来
         * sex : 男
         * classId : 04011601
         * zyh : 0401
         * major : 计算机科学与技术
         * college : 计算机科学与技术学院
         * year : 2016
         * atSchool : 在校
         */

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
    }
}
