package com.poly.gia_gioi.assignment.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.poly.gia_gioi.assignment.database.Constant;
import com.poly.gia_gioi.assignment.database.DatabaseHelper;
import com.poly.gia_gioi.assignment.model.HoaDon;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class HoaDonDAO implements Constant{
    private final DatabaseHelper databaseHelper;

    public HoaDonDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }
    public long insertHoaDon(HoaDon hoaDon){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(B_ID,hoaDon.getId());
        values.put(B_DATE,hoaDon.getDate());

        long result = sqLiteDatabase.insert(TABLE_HOA_DON,null,values);

        sqLiteDatabase.close();
        return result;
    }
    public long updateHoaDon(HoaDon hoaDon){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put("mahoadon",hoaDon.getId());
        values.put(B_DATE,hoaDon.getDate());

        long result =sqLiteDatabase.update(TABLE_HOA_DON,values,B_ID+"=?",new String[]{hoaDon.getId()});
        sqLiteDatabase.close();
        return result;
    }
    public long deleteHoaDon(String id){
        List<HoaDon> hoaDons = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        long result = sqLiteDatabase.delete(TABLE_HOA_DON,B_ID+"=?",new String[]{id});
        sqLiteDatabase.close();
        return result;
    }
    public List<HoaDon> getAllHoaDon(){
        List<HoaDon> hoaDons = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(" SELECT * FROM " + TABLE_HOA_DON ,null);
//        Kiểm tra cursor có dữ liệu hay k
        if (cursor!= null){
            if (cursor.getCount()>0){
//                Thiết lập con trỏ về vị trí đầu tiên
                cursor.moveToFirst();
                do {
                    String id = cursor.getString(0);
                    long date = cursor.getLong(1);

                    HoaDon hoaDon =new HoaDon(id,date);

                    hoaDons.add(hoaDon);


                }while (cursor.moveToNext());
                cursor.close();
                sqLiteDatabase.close();
            }

        }
        return hoaDons;

    }
    public HoaDon getHoaDonByID(String id){
        HoaDon hoaDon = null ;
        SQLiteDatabase sqLiteDatabase =databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_TYPE_BOOK, new String[]{B_ID, B_DATE},
                B_ID + "=?", new String[]{id}, null, null, null);

        if (cursor!=null){
            if (cursor.getCount()>0){
                cursor.moveToFirst();
                String id_ =cursor.getString(0);
                long date=cursor.getLong(1);

                hoaDon = new HoaDon(id,date);
                hoaDon.setId(id);
                hoaDon.setDate(date);


            }
        }
        return hoaDon;
    }

}
