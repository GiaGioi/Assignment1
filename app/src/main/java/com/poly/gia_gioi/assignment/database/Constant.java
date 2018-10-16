package com.poly.gia_gioi.assignment.database;

import com.poly.gia_gioi.assignment.model.HoaDon;

public interface Constant {


    String TABLE_USER = "User";

    String COLUMN_USERNAME = "userName";
    String COLUMN_PASSWORD = "Password";
    String COLUMN_PHONE = "Phone";
    String COLUMN_NAME = "hoTen";

    String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "(" +

            COLUMN_USERNAME + " NVARCHAR(50) PRIMARY KEY," +

            COLUMN_PASSWORD + " NVARCHAR(30)," +

            COLUMN_PHONE + " NVARCHAR," +

            COLUMN_NAME + " NVARCHAR" +

            ")";

    //        Bang Book
//        bang Book Type
//          CREATE TABLE TpyeBook(MaTheLoai CHAR(5) PKIMARY KEY NOT NULL,)
//          typeName NVARCHAR(50) NOT NULL, Description NVARCHAR(255),Position INT;
    String TABLE_TYPE_BOOK = "typeBook";

    String TB_COLUMN_ID = "MaTheLoai";
    String TB_COLUMN_NAME = "TypeName";
    String TB_COLUMN_DES = "Description";
    String TB_COLUMN_POS = "Position";

    String CEATE_TABLE_TYPE_BOOK = " CREATE TABLE " + TABLE_TYPE_BOOK + "(" +
            "" + TB_COLUMN_ID + " CHAR(5) PRIMARY KEY NOT NULL, " +
            "" + TB_COLUMN_NAME + " NVARCHAR(50) NOT NULL, " +
            "" + TB_COLUMN_DES + " NVARCHAR(255), " +
            "" + TB_COLUMN_POS + " INT " +
            ")";

    //        Hóa đơn
    String TABLE_HOA_DON = " HoaDon ";
    //    Cot
    String B_ID = "MaHoaDon";
    String B_DATE = "NgayMua";

    String CREATE_TABLE_HOA_DON = "CREATE TABLE " + TABLE_HOA_DON + "(" +
            "" + B_ID + "NCHAR(7) PRIMARY KEY NOT NULL, " +
            "" + B_DATE + "LONG NOT NULL" +
            ")";


//        Sach

    String TABLE_NAME = "Sach";

    String S_MaSach = "MaSach";
    String S_MaTheLoai = "MaTheLoai";
    String S_TacGia = "TacGia";
    String S_NXB = "NXB";
    String S_GiaBia = "GiaBia";
    String S_SoLuong = "SoLuong";



    String CREATE_TABLE_NAME = "CREATE TABLE " + TABLE_NAME + "(" +
            "" + S_MaSach + "NCHAR(5) PRIMARY KEY NOT NULL, " +
            "" + S_MaTheLoai + "NCHAR(50), " +
            "" + S_TacGia + "NVARCHAR(50), " +
            "" + S_NXB + "NVARCHAR(50), " +
            "" + S_GiaBia + "FLOAT, " +
            "" + S_SoLuong + "INT" +
            ")";

    //    Seclect * from where
//    select sum(tongtien) from(select sum(books.giabia* bill detail.soluong mua)
//    from bill inner join books on books.masach = billDetail.masach
//              inner join billDetail on Bill.mahoadon=BillDetail.mahoadon
//    where  strytime("gY ",bill.ngaymua/lao,)
    String TABLE_BILL_DETAIL = "BillDetail";
    String DETAIL_ID = "MaHDCT";
    String DETAIL_BILL_ID = "MaHoaDon";
    String DETAIL_BOOK_ID = "MaSach";
    String DETAIL_SOLUONG = "SoLuongMua";

    String CREATE_BILL_DETAIL = "CREATE TABLE " + TABLE_BILL_DETAIL + "(" +
            "" + DETAIL_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT," +
            "" + DETAIL_BILL_ID + " NCHAR(7) NOT NULL ," +
            "" + DETAIL_BOOK_ID + " NCHAR(5) NOT NULL ," +
            "" + DETAIL_SOLUONG + "  INT NOT NULL" +
            ")";

}

