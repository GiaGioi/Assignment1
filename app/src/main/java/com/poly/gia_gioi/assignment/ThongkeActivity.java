package com.poly.gia_gioi.assignment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.poly.gia_gioi.assignment.database.DatabaseHelper;

public class ThongkeActivity extends AppCompatActivity {
    private TextView tvDoanhThuThang;
    private TextView tvDoanhThuNam;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke);

        databaseHelper = new DatabaseHelper(this);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbarThongke);
        toolbar.setTitle("Thống Kê");
        toolbar.setNavigationIcon(R.drawable.ic_undo);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThongkeActivity.this,HomeAtivity.class));
            }
        });
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView tvDoanhThuNgay = findViewById(R.id.tvDoanhThuNgay);
        tvDoanhThuThang = findViewById(R.id.tvDoanhThuThang);
        tvDoanhThuNam = findViewById(R.id.tvDoanhThuNam);

    }
}
