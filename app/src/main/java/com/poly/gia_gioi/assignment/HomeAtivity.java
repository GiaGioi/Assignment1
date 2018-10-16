package com.poly.gia_gioi.assignment;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Objects;

public class HomeAtivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private LinearLayout layoutNguoidung;
    private LinearLayout layoutLoaisach;
    private LinearLayout layoutsach;
    private LinearLayout layoutsachbanchay;
    private LinearLayout layouthoadon;
    private LinearLayout layoutthongke;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_ativity);

        Toolbar toolbar = findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Quản Lý Sách");
        drawerLayout =  findViewById(R.id.drawerLayout);
        navigationView =  findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }

        layoutNguoidung = findViewById(R.id.layout_Nguoidung);
        layoutLoaisach = (LinearLayout) findViewById(R.id.layout_Loaisach);
        layoutsach = (LinearLayout) findViewById(R.id.layout_Sach);
        layoutsachbanchay = (LinearLayout) findViewById(R.id.layout_Sachbanchay);
        layouthoadon = (LinearLayout) findViewById(R.id.layout_Hoadon);
        layoutthongke = (LinearLayout) findViewById(R.id.layout_Thongke);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(navigationView)) {
                    drawerLayout.closeDrawers();
                } else {
                    drawerLayout.openDrawer(navigationView);
                }
            }
        });
        layoutNguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeAtivity.this,ListUserActivity.class));
            }
        });

        layoutLoaisach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeAtivity.this,QuanLiLoaiSachActivity.class));
            }
        });

        layoutsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeAtivity.this,QuanLiSachActivity.class));
            }
        });

        layoutsachbanchay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeAtivity.this,SachBanChayActivity.class));
            }
        });

        layoutthongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeAtivity.this,ThongkeActivity.class));
            }
        });

        layouthoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeAtivity.this,HoaDonActivity.class));
            }
        });
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.ic_QuanLiLoaiSach:
                startActivity(new Intent(HomeAtivity.this, QuanLiLoaiSachActivity.class));
                break;

            case R.id.ic_QuanLiSach:
                startActivity(new Intent(HomeAtivity.this, QuanLiSachActivity.class));
                break;

            case R.id.ic_QuanLiThongKe:
                startActivity(new Intent(HomeAtivity.this, ThongkeActivity.class));
                break;

            case R.id.ic_DangXuat:
                startActivity(new Intent(HomeAtivity.this, LoginActivity.class));
                finish();
                break;

            case R.id.ic_Thoat:
                Exit();
                break;
        }
        drawerLayout.closeDrawer(Gravity.START);
        return true;

    }

    private void Exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thoát");
        builder.setMessage("Bạn có muốn thoát không?");
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();


    }
}
