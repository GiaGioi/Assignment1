package com.poly.gia_gioi.assignment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.poly.gia_gioi.assignment.adapter.HoaDonAdapter;
import com.poly.gia_gioi.assignment.database.Constant;
import com.poly.gia_gioi.assignment.database.DatabaseHelper;
import com.poly.gia_gioi.assignment.model.HoaDon;
import com.poly.gia_gioi.assignment.sqlitedao.HoaDonDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class HoaDonActivity extends AppCompatActivity implements Constant {

    private HoaDonAdapter HoaDonadapter;
    private ArrayList<HoaDon> arrayList;
    private Toolbar toolbarHoaDon;
    private long datePicker = -1;

    private HoaDonDAO hoaDonDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        hoaDonDAO=new HoaDonDAO(new DatabaseHelper(this));

        RecyclerView reclyhoadon = findViewById(R.id.reclyhoadon);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        arrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HoaDon hoaDon = new HoaDon(new Random().nextInt(2000) + "", System.currentTimeMillis());
            arrayList.add(hoaDon);
        }
        HoaDonadapter = new HoaDonAdapter(this, arrayList,hoaDonDAO);



        toolbarHoaDon = findViewById(R.id.toolbarHoaDon);
        toolbarHoaDon.setNavigationIcon(R.drawable.ic_undo);
        toolbarHoaDon.setTitle("Hóa Đơn");
        setSupportActionBar(toolbarHoaDon);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarHoaDon.setTitleTextColor(Color.WHITE);
        toolbarHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HoaDonActivity.this,HomeAtivity.class));
            }
        });


        HoaDonadapter = new HoaDonAdapter(this, arrayList, hoaDonDAO);

        reclyhoadon.setLayoutManager(linearLayoutManager);
        reclyhoadon.setHasFixedSize(true);
        reclyhoadon.setAdapter(HoaDonadapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itemTTK) {
            final Dialog dialog = new Dialog(HoaDonActivity.this);
            dialog.setTitle("Create New Bill");

            dialog.setContentView(R.layout.dialog_create_hoa_don);

            final EditText edtID;
            final Button btnDatePicker;
            Button btnCreate;

            edtID = dialog.findViewById(R.id.edMaHoaDon_HoaDon);
            btnDatePicker = dialog.findViewById(R.id.btnPicker);
            btnCreate = dialog.findViewById(R.id.btnThem_ThemHoaDon);

            btnDatePicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDatePickerDialog(btnDatePicker);
                }
            });

            btnCreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String billID = edtID.getText().toString().trim();

                    if (billID.length() > 7) {
                        edtID.setError(getString(R.string.notify_max_bill_id_length));
                        return;
                    }

                    HoaDon bill = new HoaDon(billID, datePicker);
                    long result = hoaDonDAO.insertHoaDon(bill);
                    if (result > 0) {
                        arrayList.add(bill);
                        HoaDonadapter.notifyDataSetChanged();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(HoaDonActivity.this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(getApplicationContext(), "Mời Chọn Ngày!!!", Toast.LENGTH_SHORT).show();
                }

            });

            dialog.show();

        }
        return super.onOptionsItemSelected(item);
    }

    private void showDatePickerDialog(final Button btnPicker) {

        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                calendar.set(year, month, day);
                HoaDonActivity.this.datePicker = calendar.getTimeInMillis();
                btnPicker.setText(new Date(calendar.getTimeInMillis()).toString());

            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

}
