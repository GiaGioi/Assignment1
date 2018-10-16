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
import android.widget.Toast;

import com.poly.gia_gioi.assignment.adapter.LoaiSachAdapter;
import com.poly.gia_gioi.assignment.database.DatabaseHelper;
import com.poly.gia_gioi.assignment.model.TypeBook;
import com.poly.gia_gioi.assignment.sqlitedao.TypeBookDAO;

import java.util.ArrayList;

public class QuanLiLoaiSachActivity extends AppCompatActivity  {

    private LoaiSachAdapter loaiSachAdapter;
    private ArrayList<TypeBook> arrayList;
    private Toolbar toolbarLoaiSach;


    private DatabaseHelper databaseHelper;
    private TypeBookDAO typeBookDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanliloaisach);


        databaseHelper = new DatabaseHelper(this);
        typeBookDAO = new TypeBookDAO(databaseHelper);

        RecyclerView reclyquanlisach = findViewById(R.id.reclyquanliloaisach);
        arrayList = new ArrayList<>();
        arrayList.clear();
        toolbarLoaiSach = findViewById(R.id.toolbarQLLS);
        toolbarLoaiSach.setTitle("Thể Loại");
        toolbarLoaiSach.setTitleTextColor(Color.WHITE);
        toolbarLoaiSach.setNavigationIcon(R.drawable.ic_undo);
        toolbarLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuanLiLoaiSachActivity.this,HomeAtivity.class));
            }
        });
        setSupportActionBar(toolbarLoaiSach);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        arrayList.add(new TypeBook("1", "Công nghệ thông tin", "A", "1", R.drawable.cateicon));
        arrayList.add(new TypeBook("2", "Công nghệ thông tin", "A", "16", R.drawable.cateicon));
        arrayList.add(new TypeBook("3", "Công nghệ thông tin", "A", "4", R.drawable.cateicon));
        arrayList.add(new TypeBook("4", "Công nghệ thông tin", "A", "3", R.drawable.cateicon));
        arrayList.add(new TypeBook("5", "Công nghệ thông tin", "A", "5", R.drawable.cateicon));
        loaiSachAdapter = new LoaiSachAdapter(this, arrayList, typeBookDAO);

        reclyquanlisach.setLayoutManager(linearLayoutManager);
        reclyquanlisach.setHasFixedSize(true);
        reclyquanlisach.setAdapter(loaiSachAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.itemTTK) {
            final AlertDialog.Builder dialog1 = new AlertDialog.Builder(this);
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View dialogView = inflater.inflate(R.layout.dialog_add_typebook, null);
            dialog1.setView(dialogView);
            final Dialog dialog = dialog1.show();
            dialog.setTitle("Add User");

            final EditText edid;
            final EditText edname;
            final EditText eddes;
            final EditText edposi;

            edid = dialog.findViewById(R.id.edid);
            edname = dialog.findViewById(R.id.edname);
            eddes = dialog.findViewById(R.id.eddes);
            edposi = dialog.findViewById(R.id.edposi);

            dialog.findViewById(R.id.btSave).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    TypeBook typeBook = new TypeBook();
                    typeBook.setID(edid.getText().toString().trim());
                    typeBook.setName(edname.getText().toString().trim());
                    typeBook.setPos(eddes.getText().toString().trim());
                    typeBook.setDes(edposi.getText().toString().trim());
                    typeBookDAO.inserTypeBook(typeBook);


                    arrayList.add(0, typeBook);
                    loaiSachAdapter.notifyDataSetChanged();

                    Toast.makeText(QuanLiLoaiSachActivity.this,
                            getString(R.string.add_successful), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                }
            });
            dialog.findViewById(R.id.btnHuy).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        }

        return super.onOptionsItemSelected(item);

    }



}



