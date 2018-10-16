package com.poly.gia_gioi.assignment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.poly.gia_gioi.assignment.R;
import com.poly.gia_gioi.assignment.holder.UserHolder;
import com.poly.gia_gioi.assignment.model.SachBanChay;

import java.util.ArrayList;

public class SachBanChayAdapter extends RecyclerView.Adapter<UserHolder>{
    private final ArrayList<SachBanChay> arrayList;

    public SachBanChayAdapter(Context context, ArrayList<SachBanChay> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_sachbanchay,parent,false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        SachBanChay nguoiDung=arrayList.get(position);
        holder.tvUser.setText("Mã sách:"+nguoiDung.getMaSach());
        holder.tvSDT.setText("Số lượng"+nguoiDung.getSoLuong()+"");
        holder.imgAnh.setImageResource(nguoiDung.getAnh());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
