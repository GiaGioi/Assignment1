package com.poly.gia_gioi.assignment.model;

public class TypeBook {
    private String ID;
    private String name;
    private String des;
    private String pos;
    private int anh;


    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }

    public TypeBook(){}
    public TypeBook(String ID, String name, String des, String pos,int anh) {
        this.ID = ID;
        this.name = name;
        this.des = des;
        this.pos = pos;
        this.anh = anh;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "TypeBook{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", des='" + des + '\'' +
                ", pos='" + pos + '\'' +
                '}';
    }
}
