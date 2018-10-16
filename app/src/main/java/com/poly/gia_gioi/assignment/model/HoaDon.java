package com.poly.gia_gioi.assignment.model;

public class HoaDon {
    private String id;
    private long Date;

    public HoaDon(String id, long date) {
        this.id = id;
        Date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getDate() {
        return Date;
    }

    public void setDate(long date) {
        Date = date;
    }

}
