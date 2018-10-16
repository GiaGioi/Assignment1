package com.poly.gia_gioi.assignment.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context ;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.poly.gia_gioi.assignment.R;
import com.poly.gia_gioi.assignment.holder.UserHolder;
import com.poly.gia_gioi.assignment.model.User;
import com.poly.gia_gioi.assignment.sqlitedao.UserDAO;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserHolder> {
    private final Context context;
    private List<User> users;
    private UserDAO userDAO;

    public UserAdapter(Context context, List<User> users, UserDAO userDAO) {
        this.context = context;
        this.users = users;
        this.userDAO = userDAO;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_nguoidung,parent,false);
        return new UserHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, final int position) {
        User nguoiDung= users.get(position);
        holder.tvUser.setText(nguoiDung.getTenNguoiDung());
        holder.tvSDT.setText(nguoiDung.getSDT()+"");


        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDAO.deleteUser(users.get(position).getUsername());
                users.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(context);
                dialog.setTitle(users.get(position).getTenNguoiDung());

                dialog.setContentView(R.layout.dialog_edit_user);

                EditText edPassWord;
                EditText edRePassword;
                final EditText edtName;
                final EditText edtPhone;

                edPassWord = dialog.findViewById(R.id.edPassword);
                edRePassword = dialog.findViewById(R.id.ednhaplaimatkhau);
                edtName = dialog.findViewById(R.id.edName);
                edtPhone = dialog.findViewById(R.id.edPhone);

                edtName.setText(users.get(position).getTenNguoiDung());
                edtPhone.setText(users.get(position).getSDT());


                dialog.findViewById(R.id.btSave).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        User user = new User();
                        user.setUsername(users.get(position).getUsername());
                        user.setTenNguoiDung(edtName.getText().toString().trim());
                        user.setSDT(edtPhone.getText().toString().trim());

                        userDAO.updateUser(user);

                        users.get(position).setTenNguoiDung(edtName.getText().toString().trim());
                        users.get(position).setSDT(edtPhone.getText().toString().trim());
                        notifyDataSetChanged();

                        Toast.makeText(context,context.getString(R.string.notify_save_successful),Toast.LENGTH_SHORT).show();
                        dialog.dismiss();


                    }
                });
                dialog.findViewById(R.id.btnHuy).setOnClickListener(new View.OnClickListener() {
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
        return users.size();
    }
}



