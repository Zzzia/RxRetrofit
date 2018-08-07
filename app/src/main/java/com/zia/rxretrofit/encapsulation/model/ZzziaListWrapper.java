package com.zia.rxretrofit.encapsulation.model;

import java.util.List;

/**
 * Created By zia on 2018/8/6.
 */
public class ZzziaListWrapper<T> {
    private List<T> data;
    private int code;
    private boolean error;
    private String info;
    private String lastUpdate;


    @Override
    public String toString() {
        return "ZzziaListWrapper{" +
                "data=" + data +
                ", code=" + code +
                ", error=" + error +
                ", info='" + info + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                '}';
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
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
}
