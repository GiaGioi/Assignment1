package com.poly.gia_gioi.assignment.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.poly.gia_gioi.assignment.R;
import com.poly.gia_gioi.assignment.holder.UserHolder;
import com.poly.gia_gioi.assignment.model.Book;
import com.poly.gia_gioi.assignment.sqlitedao.SachDAO;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class SachAdapter extends RecyclerView.Adapter<UserHolder> {
    private final Context context;
    private final List<Book> arrayList;
    private SachDAO sachDAO;

    public SachAdapter(Context context, List<Book> arrayList, SachDAO sachDAO) {
        this.context = context;
        this.arrayList = arrayList;
        this.sachDAO=sachDAO;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_sach,parent,false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserHolder holder, final int position) {
        final Book nguoiDung=arrayList.get(position);
        holder.tvUser.setText(nguoiDung.getID());
        holder.tvSDT.setText(nguoiDung.getSoLuong()+"");
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long result = sachDAO.deleteSach(nguoiDung.getID());

                if (result < 0) {

                    Toast.makeText(context,"Xóa không thành công!!!",Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context,"Xóa Thành Công!!!",Toast.LENGTH_SHORT).show();

                    arrayList.remove(position);

                    notifyDataSetChanged();

                }



            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_themsach);

                final EditText edMaSach, edTenSach, edNXB, edTacGia, edGiaBia, edSoLuong;
                Button btnAddTypeBook;
                Button btnCancel;

                edMaSach = dialog.findViewById(R.id.edMaSach);
                edTenSach =dialog.findViewById(R.id.edTenSach);
                edNXB = dialog.findViewById(R.id.edNXB);
                edTacGia = dialog.findViewById(R.id.edTacGia);
                edGiaBia = dialog.findViewById(R.id.edGiaBia);
                edSoLuong = dialog.findViewById(R.id.edSoLuong);
                btnAddTypeBook = dialog.findViewById(R.id.btnAddBook);
                btnCancel = dialog.findViewById(R.id.btnCancelBook);
                btnAddTypeBook.setText("Update");

                edMaSach.setText(nguoiDung.getID());
                edTenSach.setText(nguoiDung.getMaTheLoai());
                edNXB.setText(nguoiDung.getNXB());
                edTacGia.setText(nguoiDung.getTenTacGia());
                sachDAO.insertSach(nguoiDung);

                btnAddTypeBook.setOnClickListener(new View.OnClickListener() {
                    @SuppressWarnings("unused")
                    @Override
                    public void onClick(View view) {

                        String id = nguoiDung.getID();
                        String tacgia = edTacGia.getText().toString().trim();
                        String nxb = edNXB.getText().toString().trim();
                        String tensach = edTenSach.getText().toString().trim();

                        Book book = new Book();

                        long result = sachDAO.updateSach(book);
                        if (result < 0) {
                            Toast.makeText(context, "Cập nhật không thành công. Có lỗi xảy ra !!!", Toast.LENGTH_SHORT).show();
                        } else {

                            arrayList.get(position).setID(edMaSach.getText().toString().trim());

                            arrayList.get(position).setTenTacGia(edTacGia.getText().toString().trim());

                            arrayList.get(position).setMaTheLoai(edTenSach.getText().toString().trim());
                            arrayList.get(position).setNXB(edNXB.getText().toString().trim());

                            notifyDataSetChanged();

                            Toast.makeText(context, "Cập nhật loại sách thành công!!!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
