package com.poly.gia_gioi.assignment.model;

public class SachBanChay {
    private String MaSach;
    private int SoLuong;
    private int Anh;

    public SachBanChay(String maSach, int soLuong, int anh) {
        MaSach = maSach;
        SoLuong = soLuong;
        Anh = anh;
    }

    public String getMaSach() {
        return MaSach;
    }

    public void setMaSach(String maSach) {
        MaSach = maSach;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public int getAnh() {
        return Anh;
    }

}
