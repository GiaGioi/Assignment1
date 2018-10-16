package com.poly.gia_gioi.assignment.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper implements Constant{


    public DatabaseHelper(Context context) {
        super(context,"BookManager", null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CEATE_TABLE_TYPE_BOOK);
        db.execSQL(CREATE_TABLE_HOA_DON);
        db.execSQL(CREATE_TABLE_NAME);
        db.execSQL(CREATE_BILL_DETAIL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_USER );
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_TYPE_BOOK );
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_HOA_DON);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_BILL_DETAIL);

        onCreate(db);
    }
}
