package com.poly.gia_gioi.assignment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.poly.gia_gioi.assignment.adapter.SachAdapter;
import com.poly.gia_gioi.assignment.database.DatabaseHelper;
import com.poly.gia_gioi.assignment.model.Book;
import com.poly.gia_gioi.assignment.sqlitedao.SachDAO;

import java.util.ArrayList;
import java.util.List;

public class QuanLiSachActivity extends AppCompatActivity {

    private SachAdapter sachAdapter;
    private EditText edMaSach, edTenSach, edNXB, edTacGia, edGiaBia, edSoLuong;
    private Spinner spnTheLoai;
    private List<Book> arrayList;
    private Toolbar toolbarSach;

    private SachDAO sachDAO;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_quanlisach);

        databaseHelper = new DatabaseHelper(this);
        sachDAO = new SachDAO(databaseHelper);

        toolbarSach=findViewById(R.id.toolbarQLS);
        toolbarSach.setTitle("BookManager");
        toolbarSach.setNavigationIcon(R.drawable.ic_undo);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarSach.setTitleTextColor(Color.WHITE);
        toolbarSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuanLiSachActivity.this,HomeAtivity.class));
            }
        });
        setSupportActionBar(toolbarSach);
        RecyclerView reclysach = findViewById(R.id.reclyquanlisach);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        arrayList=new ArrayList<>();
        for (int i=0;i<20;i++){
            Book book =new Book("123","12345","Gia Gioi","Ha Noi",15000,20);
            arrayList.add(book);
        }


        sachAdapter =new SachAdapter(this,arrayList,sachDAO);

        reclysach.setLayoutManager(linearLayoutManager);
        reclysach.setHasFixedSize(true);
        reclysach.setAdapter(sachAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.them,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.them){
            final AlertDialog.Builder dialog1 = new AlertDialog.Builder(this);
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View dialogView = inflater.inflate(R.layout.dialog_themsach, null);
            dialog1.setView(dialogView);
            final Dialog dialog = dialog1.show();
            dialog.setTitle("Add User");


            spnTheLoai = findViewById(R.id.spnTheLoai);
            edMaSach =  findViewById(R.id.edMaSach);
            edTenSach =  findViewById(R.id.edTenSach);
            edNXB =     findViewById(R.id.edNXB);
            edTacGia =  findViewById(R.id.edTacGia);
            edGiaBia =  findViewById(R.id.edGiaBia);
            edSoLuong = findViewById(R.id.edSoLuong);

            dialog.findViewById(R.id.btnAddBook).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book book = new Book();
                    book.setID(edMaSach.getText().toString().trim());
                    book.setMaTheLoai(edTenSach.getText().toString().trim());
                    book.setTenTacGia(edTacGia.getText().toString().trim());
                    book.setNXB(edNXB.getText().toString().trim());
                    book.setGiaBia(Float.parseFloat(edGiaBia.getText().toString()));
                    book.setSoLuong(Integer.parseInt(edSoLuong.getText().toString()));
                    sachDAO.insertSach(book);

                    arrayList.add(0, book);
                    sachAdapter.notifyDataSetChanged();

                    Toast.makeText(QuanLiSachActivity.this,
                            getString(R.string.add_successful), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                }
            });
                dialog.show();

        }
        return super.onOptionsItemSelected(item);
    }
}
