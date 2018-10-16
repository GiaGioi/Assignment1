package com.poly.gia_gioi.assignment.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.poly.gia_gioi.assignment.database.Constant;
import com.poly.gia_gioi.assignment.database.DatabaseHelper;
import com.poly.gia_gioi.assignment.model.BillDetail;

import java.util.ArrayList;
import java.util.List;

public class BillDetailDAO implements Constant {
    private final DatabaseHelper databaseHelper;

    public BillDetailDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }
    public List<BillDetail> getAllBillDetailByID(String billID) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        List<BillDetail> billDetails = new ArrayList<>();

        String SELECT_ALL_BILL_DETAIL_BY_BILL_ID = "SELECT * FROM " + TABLE_BILL_DETAIL +
                " WHERE " + DETAIL_BILL_ID + " = " + "'" + billID + "'";

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_BILL_DETAIL_BY_BILL_ID, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                String id = cursor.getString(cursor.getColumnIndex(DETAIL_ID));
                String book_id = cursor.getString(cursor.getColumnIndex(DETAIL_BOOK_ID));
                String bill_id = cursor.getString(cursor.getColumnIndex(DETAIL_BILL_ID));
                int quality = cursor.getInt(cursor.getColumnIndex(DETAIL_SOLUONG));

                BillDetail billDetail = new BillDetail();
                billDetail.id = id;
                billDetail.billID = bill_id;
                billDetail.bookID = book_id;
                billDetail.soluong = quality;
                billDetails.add(billDetail);

            } while (cursor.moveToNext());

        }
        sqLiteDatabase.close();
        return billDetails;
    }
    public long insertBillDetail(BillDetail billDetail) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DETAIL_ID, billDetail.id);
        contentValues.put(DETAIL_BILL_ID, billDetail.billID);
        contentValues.put(DETAIL_BOOK_ID, billDetail.bookID);
        contentValues.put(DETAIL_SOLUONG, billDetail.soluong);

        return sqLiteDatabase.insert(TABLE_BILL_DETAIL, null, contentValues);
    }

}
