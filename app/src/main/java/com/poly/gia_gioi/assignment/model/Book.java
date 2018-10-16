package com.poly.gia_gioi.assignment.model;

public class Book {
    private String ID;
    private String MaTheLoai;
    private String TenTacGia;
    private String NXB;
    private float GiaBia;
    private int SoLuong;

    public Book(){

    }
    public Book(String ID, String maTheLoai, String tenTacGia, String NXB, float giaBia, int soLuong) {
        this.ID = ID;
        MaTheLoai = maTheLoai;
        TenTacGia = tenTacGia;
        this.NXB = NXB;
        GiaBia = giaBia;
        SoLuong = soLuong;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMaTheLoai() {
        return MaTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        MaTheLoai = maTheLoai;
    }

    public String getTenTacGia() {
        return TenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        TenTacGia = tenTacGia;
    }

    public String getNXB() {
        return NXB;
    }

    public void setNXB(String NXB) {
        this.NXB = NXB;
    }

    public float getGiaBia() {
        return GiaBia;
    }

    public void setGiaBia(float giaBia) {
        GiaBia = giaBia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ID='" + ID + '\'' +
                ", MaTheLoai='" + MaTheLoai + '\'' +
                ", TenTacGia='" + TenTacGia + '\'' +
                ", NXB='" + NXB + '\'' +
                ", GiaBia=" + GiaBia +
                ", SoLuong=" + SoLuong +
                '}';
    }
}
