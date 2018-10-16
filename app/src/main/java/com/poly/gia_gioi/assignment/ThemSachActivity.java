package com.poly.gia_gioi.assignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.poly.gia_gioi.assignment.adapter.SachAdapter;
import com.poly.gia_gioi.assignment.model.Book;
import com.poly.gia_gioi.assignment.sqlitedao.SachDAO;

import java.util.ArrayList;
import java.util.List;

public class ThemSachActivity extends AppCompatActivity {

    private SachDAO sachDAO;
    private SachAdapter sachAdapter;
    private EditText edMaSach;
    private EditText edTenSach;
    private EditText edNXB;
    private EditText edTacGia;
    private EditText edSoLuong;
    String maTheLoai = "";
    private final List<Book> listBook = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sach);
        setTitle("THÊM SÁCH");
        Spinner spnTheLoai = findViewById(R.id.spnTheLoai);
        edMaSach = (EditText) findViewById(R.id.edMaSach);
        edTenSach = (EditText) findViewById(R.id.edTenSach);
        edNXB = (EditText) findViewById(R.id.edNXB);
        edTacGia = (EditText) findViewById(R.id.edTacGia);
        EditText edGiaBia = (EditText) findViewById(R.id.edGiaBia);
        edSoLuong = (EditText)findViewById(R.id.edSoLuong);
    }


    public void addBook(View view) {
        Book book = new Book("123","12345","Gia Gioi","Ha Noi",15000,20);
        book.setID(edMaSach.getText().toString().trim());
        book.setMaTheLoai(edTenSach.getText().toString().trim());
        book.setTenTacGia(edTacGia.getText().toString().trim());
        book.setNXB(edNXB.getText().toString().trim());
        sachDAO.insertSach(book);
        listBook.add(0, book);
        sachAdapter.notifyDataSetChanged();
    }

}
