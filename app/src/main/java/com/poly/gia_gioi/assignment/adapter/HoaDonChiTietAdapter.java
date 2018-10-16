package com.poly.gia_gioi.assignment.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.poly.gia_gioi.assignment.R;
import com.poly.gia_gioi.assignment.model.BillDetail;

import java.util.List;

@SuppressWarnings("unchecked")
public class HoaDonChiTietAdapter extends RecyclerView.Adapter<HoaDonChiTietAdapter.ViewHolder>  {
    private final List<BillDetail> billDetails;


    public HoaDonChiTietAdapter(List<BillDetail> billDetails) {
        this.billDetails = billDetails;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_hoa_don_chi_tiet, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonChiTietAdapter.ViewHolder holder, int position) {
        final BillDetail billDetail = billDetails.get(position);

        holder.tvName.setText(billDetail.bookID);
        holder.tvQuality.setText(billDetail.soluong + "");
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return billDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgDelete;
        TextView tvName;
        TextView tvQuality;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvQuality = itemView.findViewById(R.id.tvSoLuong);
            imgDelete = itemView.findViewById(R.id.imgDeleteHoaDonChiTiet_item);
        }

    }
}
