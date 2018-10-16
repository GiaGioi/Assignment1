package com.poly.gia_gioi.assignment.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.poly.gia_gioi.assignment.database.Constant;
import com.poly.gia_gioi.assignment.database.DatabaseHelper;
import com.poly.gia_gioi.assignment.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO implements Constant {

    private final DatabaseHelper databaseHelper;

    public UserDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void insertUser(User user) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_PHONE, user.getSDT());
        values.put(COLUMN_NAME, user.getTenNguoiDung());

        long id = sqLiteDatabase.insert(TABLE_USER, null, values);
        Log.e("insertUser" , "insertUser : " + id);

        sqLiteDatabase.close();
    }


    public User getUserByUserName(String username) {
        User user = null;

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_USER,
                new String[]{COLUMN_USERNAME, COLUMN_PASSWORD, COLUMN_PHONE, COLUMN_NAME},
                COLUMN_USERNAME + "=?",
                new String[]{username}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {

            String user_name = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
            String phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));

            user = new User();
            user.setUsername(user_name);
            user.setPassword(password);
            user.setTenNguoiDung(name);
            user.setSDT(phone);

        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        String SELECT_ALL_USER = " SELECT * FROM " + TABLE_USER;

        Log.e("getAllUsers", SELECT_ALL_USER);

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_USER, null);

        cursor.moveToFirst();
        do {

            String user_name = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
            String phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));

            User user = new User();
            user.setUsername(user_name);
            user.setPassword(password);
            user.setTenNguoiDung(name);
            user.setSDT(phone);

            userList.add(user);

        } while (cursor.moveToNext());
        cursor.close();
        sqLiteDatabase.close();


        return userList;
    }

    public int deleteUser(String username) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        return database.delete(TABLE_USER,
                COLUMN_USERNAME + "=?", new String[]{username});
    }

    public int updateUser(User user) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_PHONE, user.getSDT());
        values.put(COLUMN_NAME, user.getTenNguoiDung());

        return sqLiteDatabase.update(TABLE_USER, values,
                COLUMN_USERNAME + "=?", new String[]{user.getUsername()});

    }



//    public int changePasswordNguoiDung(User user) {
//        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put("username", user.getUsername());
//        values.put("password", user.getPassword());
//        int change = sqLiteDatabase.update(TABLE_USER, values, COLUMN_PASSWORD + "=?", new
//                String[]{user.getUsername()});
//        if (change == 0) {
//            return -1;
//        }
//        return 1;
//    }
}
