package com.poly.gia_gioi.assignment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.poly.gia_gioi.assignment.R;
import com.poly.gia_gioi.assignment.holder.UserHolder;
import com.poly.gia_gioi.assignment.model.HoaDon;
import com.poly.gia_gioi.assignment.sqlitedao.HoaDonDAO;

import java.util.ArrayList;
import java.util.Date;

public class HoaDonAdapter extends RecyclerView.Adapter<UserHolder> {

    private final Context context;
    private final ArrayList<HoaDon> arrayList;
    private  HoaDonDAO hoaDonDAO;

    public HoaDonAdapter(Context context, ArrayList<HoaDon> arrayList,HoaDonDAO hoaDonDAO) {
        this.context = context;
        this.arrayList = arrayList;
        this.hoaDonDAO =hoaDonDAO;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_hoadon,parent,false);
        return new UserHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, final int position) {
        final HoaDon nguoiDung=arrayList.get(position);

        holder.tvUser.setText(nguoiDung.getId()+"");
        holder.tvSDT.setText( new Date(nguoiDung.getDate()).toString());

        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long result = hoaDonDAO.deleteHoaDon(nguoiDung.getId());

                if (result < 0){
                    Toast.makeText(context, "Xóa Không thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context,"Xóa Thành Công!!!",Toast.LENGTH_SHORT).show();

                    arrayList.remove(position);
                    notifyDataSetChanged();

                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
