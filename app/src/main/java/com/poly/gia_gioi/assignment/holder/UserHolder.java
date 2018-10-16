package com.poly.gia_gioi.assignment.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.poly.gia_gioi.assignment.R;

public class UserHolder extends RecyclerView.ViewHolder{
    public  final TextView tvUser,tvSDT;
    public  final ImageView imgAnh;
    public  final ImageView btDelete;
    public final ImageView btnEdit;


    public UserHolder(View itemView) {
        super(itemView);
        btnEdit = itemView.findViewById(R.id.btnEdit);
        tvUser=itemView.findViewById(R.id.tvUser);
        tvSDT=itemView.findViewById(R.id.tvSDT);
        imgAnh=itemView.findViewById(R.id.imgAnh);
        btDelete = itemView.findViewById(R.id.btDeleteUser);
    }

}
