package com.poly.gia_gioi.assignment.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.poly.gia_gioi.assignment.database.Constant;
import com.poly.gia_gioi.assignment.database.DatabaseHelper;
import com.poly.gia_gioi.assignment.model.Book;
import com.poly.gia_gioi.assignment.model.HoaDon;

import java.util.ArrayList;
import java.util.List;

public class SachDAO implements Constant {
    private final DatabaseHelper databaseHelper;

    public SachDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void insertSach(Book book) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("MaSach", book.getID());
        values.put("MaTheLoai", book.getMaTheLoai());
        values.put("TacGia", book.getTenTacGia());
        values.put("NXB", book.getNXB());
        values.put("GiaBia", book.getGiaBia());
        values.put("SoLuong", book.getSoLuong());

        long result = sqLiteDatabase.insert(TABLE_NAME, null, values);

        Log.e("inserSach", "inserSach : " + result);

        sqLiteDatabase.close();
    }

    public long updateSach(Book book) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("MaTheLoai", book.getMaTheLoai());
        values.put("TacGia", book.getTenTacGia());
        values.put("NXB", book.getNXB());
        values.put("GiaBia", book.getGiaBia());
        values.put("SoLuong", book.getSoLuong());

        long result = sqLiteDatabase.update(TABLE_NAME,
                values, S_MaSach + "MaSach=?",
                new String[]{book.getID()});
        sqLiteDatabase.close();
        return result;
    }

    public int deleteSach(String book) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        return sqLiteDatabase.delete(TABLE_NAME, S_MaSach + "=?",
                new String[]{book});
    }

    public List<Book> getAllSach() {
        List<Book> books = new ArrayList<>();
        String SELECT_ALL_SACH = " SELECT * FROM " + TABLE_NAME;
        Log.e("getAllSach", SELECT_ALL_SACH);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_SACH, null);
        cursor.moveToFirst();
        do {
            String masach = cursor.getString(0);
            String matheloai = cursor.getString(1);
            String tacgia = cursor.getString(2);
            String nxb = cursor.getString(3);
            long giabia = cursor.getLong(4);
            int soluong = cursor.getInt(5);

            Book book = new Book();
            book.setID(masach);
            book.setMaTheLoai(matheloai);
            book.setTenTacGia(tacgia);
            book.setNXB(nxb);
            book.setGiaBia(giabia);
            book.setSoLuong(soluong);

            books.add(book);


        } while (cursor.moveToNext());
        cursor.close();
        sqLiteDatabase.close();

        return books;
    }

    public Book getBookByMaSach(String BookMaSach) {
        Book book = null;
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[]{S_MaSach, S_MaTheLoai, S_TacGia, S_NXB, S_GiaBia, S_SoLuong},
                S_MaSach + "=?", new String[]{BookMaSach}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            String masach = cursor.getString(0);
            String matheloai = cursor.getString(1);
            String tacgia = cursor.getString(2);
            String nxb = cursor.getString(3);
            long giabia = cursor.getLong(4);
            int soluong = cursor.getInt(5);

            Book book1 = new Book();
            book1.setID(masach);
            book1.setMaTheLoai(matheloai);
            book1.setTenTacGia(tacgia);
            book1.setNXB(nxb);
            book1.setGiaBia(giabia);
            book1.setSoLuong(soluong);
        }
        return book;

    }

    public boolean checkPrimaryKey(String strPrimaryKey) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        //SELECT
        String[] columns = {"masach"};
        //WHERE clause
        String selection = "masach=?";
        //WHERE clause arguments
        String[] selectionArgs = {strPrimaryKey};
        Cursor c = null;
        try {
            c = sqLiteDatabase
                    .query(TABLE_NAME, columns, selection, selectionArgs, null, null,
                            null);
            c.moveToFirst();
            int i = c.getCount();
            c.close();
            if (i <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Book checkBook(String strPrimaryKey) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        Book s = new Book();
        //SELECT
        String[] columns = {"masach"};
        //WHERE clause
        String selection = "masach=?";
        //WHERE clause arguments
        String[] selectionArgs = {strPrimaryKey};
        Cursor c = null;
        try {
        c = sqLiteDatabase.query(TABLE_NAME, columns, selection, selectionArgs, null, null,
                null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            s.setID(c.getString(0));
            s.setMaTheLoai(c.getString(1));
            s.setTenTacGia(c.getString(2));
            s.setNXB(c.getString(3));
            s.setGiaBia(c.getFloat(4));
            s.setSoLuong(c.getInt(5));
            Log.d("//=====",s.toString());
            break;
        }
        c.close();
        return s;
    }catch(Exception e){
        e.printStackTrace();
        return null;
    }
    }
//    public List<Book> getSachTop10(String month){
//        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
//        List<Book> dsSach = new ArrayList<>();
//        if (Integer.parseInt(month)<10){
//            month = "0"+month;
//        }
//        String sSQL = "SELECT maSach, SUM(soLuong) as soluong FROM HoaDonChiTiet
//        INNER JOIN HoaDon " +
//        "ON HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon WHERE
//        strftime('%m',HoaDon.Date) = '"+month+"' " +
//        "GROUP BY maSach ORDER BY soluong DESC LIMIT 10";
//        Cursor c = sqLiteDatabase.rawQuery(sSQL, null);
//        c.moveToFirst();
//        while (c.isAfterLast()==false){
//            Log.d("//=====",c.getString(0));
//            Book s = new Book();
//            s.setID(c.getString(0));
//            s.setSoLuong(c.getInt(1));
//            s.setGiaBia(0);
//            s.setMaTheLoai("");
//            s.setTenTacGia("");
//            s.setNXB("");
//            dsSach.add(s);
//            c.moveToNext();
//        }
//        c.close();
//        return dsSach;
//    }
}