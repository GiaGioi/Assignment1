package com.poly.gia_gioi.assignment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.poly.gia_gioi.assignment.adapter.HoaDonChiTietAdapter;
import com.poly.gia_gioi.assignment.database.Constant;
import com.poly.gia_gioi.assignment.database.DatabaseHelper;
import com.poly.gia_gioi.assignment.model.BillDetail;
import com.poly.gia_gioi.assignment.model.Book;
import com.poly.gia_gioi.assignment.sqlitedao.BillDetailDAO;
import com.poly.gia_gioi.assignment.sqlitedao.SachDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public class HoaDonChiTietActivity extends AppCompatActivity implements Constant {
    private FloatingActionButton floatingActionButton;
    private List<BillDetail> hoaDonList;
    private HoaDonChiTietAdapter adapter;

    private String billID;

    private DatabaseHelper databaseHelper;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);

        billID = getIntent().getStringExtra(B_ID);
        Toolbar toolbarHoaDonChiTiet = findViewById(R.id.toolbarHoaDonChiTiet);
        toolbarHoaDonChiTiet.setNavigationIcon(R.drawable.ic_undo);
        toolbarHoaDonChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(toolbarHoaDonChiTiet);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbarHoaDonChiTiet.setTitleTextColor(Color.WHITE);
        toolbarHoaDonChiTiet.setTitle("Hóa Đơn Chi Tiết");
        floatingActionButton = findViewById(R.id.fbtn_HoaDonChiTiet);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogHoaDonChitiet();
            }
        });

        databaseHelper = new DatabaseHelper(this);
        BillDetailDAO billDetailDAO = new BillDetailDAO(databaseHelper);
        toolbarHoaDonChiTiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HoaDonChiTietActivity.this,HoaDonActivity.class));
            }
        });

        RecyclerView rvHoaDonChiTiet = findViewById(R.id.RecyclerView_HoaDonChiTiet);
        hoaDonList = new ArrayList<>();
        hoaDonList = billDetailDAO.getAllBillDetailByID(billID);

        adapter = new HoaDonChiTietAdapter(hoaDonList);
        rvHoaDonChiTiet.setAdapter(adapter);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvHoaDonChiTiet.setLayoutManager(manager);
    }

    @SuppressWarnings("unused")
    private void DialogHoaDonChitiet(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_hdct, null);
        dialog.setView(dialogView);
        final Dialog dialog1 = dialog.show();

        final Spinner spBookID;
        final EditText edtQuality;

        spBookID = dialogView.findViewById(R.id.spBookID);
        edtQuality = dialogView.findViewById(R.id.edtQuality);

        List<Book> books = new SachDAO(databaseHelper).getAllSach();

        Button them = dialogView.findViewById(R.id.btnThem_HoaDonChiTiet);
        Button huy = dialogView.findViewById(R.id.btnHuy_HoaDonChiTiet);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BillDetail billDetail = new BillDetail();
                billDetail.billID = HoaDonChiTietActivity.this.billID;
                billDetail.bookID = ((Book) spBookID.getSelectedItem()).getID();
                billDetail.soluong = Integer.parseInt(edtQuality.getText().toString().trim());

                BillDetailDAO billDetailDAO = new BillDetailDAO(databaseHelper);
                long result = billDetailDAO.insertBillDetail(billDetail);

                if (result > 0) {
                    hoaDonList.add(billDetail);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "Thêm hóa đơn chi tiết thành công!!!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm hóa đơn chi tiết thất bại!!!", Toast.LENGTH_LONG).show();
                }
            }
        });

        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
    }

}
