package com.poly.gia_gioi.assignment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.poly.gia_gioi.assignment.adapter.SachBanChayAdapter;
import com.poly.gia_gioi.assignment.model.SachBanChay;

import java.util.ArrayList;

public class SachBanChayActivity extends AppCompatActivity {

    private SachBanChayAdapter sachBanChayAdapter;
    private ArrayList<SachBanChay> arrayList;
    private Toolbar toolbarSBC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sachbanchay);

        toolbarSBC=findViewById(R.id.toolbarSBC);
        toolbarSBC.setNavigationIcon(R.drawable.ic_undo);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarSBC.setTitleTextColor(Color.WHITE);
        toolbarSBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SachBanChayActivity.this,HomeAtivity.class));
            }
        });
        toolbarSBC.setTitle("Top 10 Sách bán chạy");
        setSupportActionBar(toolbarSBC);
        RecyclerView recyclerview_SBC = findViewById(R.id.reclysachbanchay);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        arrayList=new ArrayList<>();
        arrayList.clear();
        arrayList.add(new SachBanChay("S1",100,R.drawable.money_icon));
        arrayList.add(new SachBanChay("S2",100,R.drawable.money_icon));
        arrayList.add(new SachBanChay("S3",100,R.drawable.money_icon));
        arrayList.add(new SachBanChay("S4",100,R.drawable.money_icon));
        arrayList.add(new SachBanChay("S5",100,R.drawable.money_icon));
        arrayList.add(new SachBanChay("S6",100,R.drawable.money_icon));
        arrayList.add(new SachBanChay("S7",100,R.drawable.money_icon));
        sachBanChayAdapter=new SachBanChayAdapter(this,arrayList);

        recyclerview_SBC.setLayoutManager(linearLayoutManager);
        recyclerview_SBC.setHasFixedSize(true);
        recyclerview_SBC.setAdapter(sachBanChayAdapter);
    }
}
