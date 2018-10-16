package com.poly.gia_gioi.assignment.model;

public class User {
    private String username;
    private String password;
    private String TenNguoiDung;
    private int AnhNguoiDung;
    private String SDT;

    public User(){
    }
    public User(String username, String password, String tenNguoiDung, int anhNguoiDung, String SDT) {
        this.username = username;
        this.password = password;
        TenNguoiDung = tenNguoiDung;
        AnhNguoiDung = anhNguoiDung;
        this.SDT = SDT;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTenNguoiDung() {
        return TenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        TenNguoiDung = tenNguoiDung;
    }

    public int getAnhNguoiDung() {
        return AnhNguoiDung;
    }

    public void setAnhNguoiDung(int anhNguoiDung) {
        AnhNguoiDung = anhNguoiDung;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", TenNguoiDung='" + TenNguoiDung + '\'' +
                ", AnhNguoiDung=" + AnhNguoiDung +
                ", SDT='" + SDT + '\'' +
                '}';
    }
}
