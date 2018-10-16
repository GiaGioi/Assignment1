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
import com.poly.gia_gioi.assignment.model.TypeBook;
import com.poly.gia_gioi.assignment.sqlitedao.TypeBookDAO;

import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<UserHolder>{
    private final Context context;
    private final ArrayList<TypeBook> arrayList;
    private final TypeBookDAO typeBookDAO;

    public LoaiSachAdapter(Context context, ArrayList<TypeBook> arrayList, TypeBookDAO typeBookDAO) {
        this.context = context;
        this.arrayList = arrayList;
        this.typeBookDAO = typeBookDAO;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.item_theloai,parent,false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, final int position) {
        final TypeBook typeBook = arrayList.get(position);
        holder.tvUser.setText(typeBook.getID());
        holder.tvUser.setText(typeBook.getName());
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long result = typeBookDAO.deleteTypeBook(typeBook.getID());

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
                dialog.setContentView(R.layout.dialog_add_typebook);

                final EditText edtID;
                final EditText edtName;
                final EditText edtDes;
                final EditText edtPos;
                Button btnAddTypeBook;
                Button btnCancel;

                edtID = dialog.findViewById(R.id.edid);
                edtID.setVisibility(View.GONE);
                edtName = dialog.findViewById(R.id.edname);
                edtDes = dialog.findViewById(R.id.eddes);
                edtPos = dialog.findViewById(R.id.edposi);
                btnAddTypeBook = dialog.findViewById(R.id.btSave);
                btnCancel = dialog.findViewById(R.id.btnHuy);
                btnAddTypeBook.setText("UPDATE");


                edtName.setText(typeBook.getName());
                edtDes.setText(typeBook.getDes());
                edtPos.setText(typeBook.getPos());

                btnAddTypeBook.setOnClickListener(new View.OnClickListener() {
                    @SuppressWarnings("unused")
                    @Override
                    public void onClick(View view) {

                        String id = typeBook.getID();
                        String name = edtName.getText().toString().trim();
                        String des = edtDes.getText().toString().trim();
                        String pos = edtPos.getText().toString().trim();
                        TypeBook typeBook = new TypeBook();

                        long result = typeBookDAO.updateTypeBook(typeBook);
                        if (result < 0) {
                            Toast.makeText(context, "Cập nhật không thành công. Có lỗi xảy ra !!!", Toast.LENGTH_SHORT).show();
                        } else {

                            arrayList.get(position).setName(edtName.getText().toString().trim());

                            arrayList.get(position).setPos(edtPos.getText().toString().trim());

                            arrayList.get(position).setDes(edtDes.getText().toString().trim());



                            notifyDataSetChanged();

                            Toast.makeText(context, "Cập nhật loại sách thành công!!!", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
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
