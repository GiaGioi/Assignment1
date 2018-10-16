package com.poly.gia_gioi.assignment.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.poly.gia_gioi.assignment.database.Constant;
import com.poly.gia_gioi.assignment.database.DatabaseHelper;
import com.poly.gia_gioi.assignment.model.TypeBook;

import java.util.ArrayList;
import java.util.List;

public class TypeBookDAO implements Constant{
    private SQLiteDatabase database;
    private final DatabaseHelper databaseHelper;

    public TypeBookDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void inserTypeBook(TypeBook typeBook) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TB_COLUMN_ID, typeBook.getID());
        values.put(TB_COLUMN_NAME, typeBook.getName());
        values.put(TB_COLUMN_DES, typeBook.getDes());
        values.put(TB_COLUMN_POS, typeBook.getPos());

        long result = sqLiteDatabase.insert(TABLE_USER, null, values);

        Log.e("inserTypeBook", "inserTypeBook : " + result);

        sqLiteDatabase.close();

    }
    public long updateTypeBook(TypeBook typeBook) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TB_COLUMN_NAME, typeBook.getName());
        contentValues.put(TB_COLUMN_DES, typeBook.getDes());
        contentValues.put(TB_COLUMN_POS, typeBook.getPos());

        long result = sqLiteDatabase.update(TABLE_TYPE_BOOK,
                contentValues, TB_COLUMN_ID + "=?",
                new String[]{typeBook.getID()});

        sqLiteDatabase.close();

        return result;
    }
    public long deleteTypeBook(String typeBook) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        long result = sqLiteDatabase.delete(TABLE_TYPE_BOOK,
                TB_COLUMN_ID + "=?", new String[]{typeBook});

        sqLiteDatabase.close();

        return result;
    }
    public List<TypeBook> getAllTypeBook(){
        List<TypeBook> typeBooks = new ArrayList<>();
        String SELECT_ALL_TYPE_BOOK = "SELECT * FROM " + TABLE_TYPE_BOOK;
        Log.e("getAllTypeBook", SELECT_ALL_TYPE_BOOK);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_TYPE_BOOK, null);
        cursor.moveToFirst();
        do {
            String id = cursor.getString(cursor.getColumnIndex(TB_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(TB_COLUMN_NAME));
            String des = cursor.getString(cursor.getColumnIndex(TB_COLUMN_DES));
            String pos = cursor.getString(cursor.getColumnIndex(TB_COLUMN_POS));

            TypeBook books = new TypeBook();
            books.setID(id);
            books.setName(name);
            books.setDes(des);
            books.setPos(pos);

            typeBooks.add(books);


        } while (cursor.moveToNext());
        cursor.close();
        sqLiteDatabase.close();


        return typeBooks;
    }
    public TypeBook getTypeBookByID(String typeID) {
        TypeBook typeBook = null;
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_USER, new String[]{TB_COLUMN_ID, TB_COLUMN_NAME, TB_COLUMN_DES, TB_COLUMN_POS},
                TB_COLUMN_ID + "=?", new String[]{typeID}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            String id = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
            String des = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
            String pos = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));

            typeBook = new TypeBook();
            typeBook.setID(id);
            typeBook.setName(name);
            typeBook.setDes(des);
            typeBook.setPos(pos);
        }
        return typeBook;

    }
}
