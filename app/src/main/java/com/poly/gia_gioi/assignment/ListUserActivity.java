package com.poly.gia_gioi.assignment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.poly.gia_gioi.assignment.adapter.UserAdapter;
import com.poly.gia_gioi.assignment.database.DatabaseHelper;
import com.poly.gia_gioi.assignment.model.User;
import com.poly.gia_gioi.assignment.sqlitedao.UserDAO;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("UnusedAssignment")
public class ListUserActivity extends AppCompatActivity  {

    private List<User> users;
    private RecyclerView reclynguoidung;
    private UserAdapter userapdapter;
    private LinearLayoutManager linearLayoutManager;


    private UserDAO userDAO;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);

        databaseHelper = new DatabaseHelper(this);
        userDAO = new UserDAO(databaseHelper);

        Toolbar toolbarUser = findViewById(R.id.toolbarNguoidung);
        toolbarUser.setTitle(getString(R.string.title_list_user_act));
        toolbarUser.setNavigationIcon(R.drawable.ic_undo);
        setSupportActionBar(toolbarUser);
        for (int i=0;i<5;i++){
            User user = new User(
                    "giagioi",
                    "gioikin123",
                    "Gia Gioi",R.drawable.emthree,
                    "01667096535");
            userDAO.insertUser(user);
        }
        toolbarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListUserActivity.this,HomeAtivity.class));
            }
        });



        themlist();
        khoitao();

    }
    private void khoitao() {

        reclynguoidung = findViewById(R.id.reclyNguoiDung);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        users = new ArrayList<>();
        users.clear();
    }

    private void themlist() {


        users = userDAO.getAllUsers();
        userapdapter = new UserAdapter(this, users, userDAO);

        reclynguoidung.setLayoutManager(linearLayoutManager);
        reclynguoidung.setHasFixedSize(true);
        reclynguoidung.setAdapter(userapdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.ttk){
            final AlertDialog.Builder dialog1 = new AlertDialog.Builder(this);
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View dialogView = inflater.inflate(R.layout.dialog_add_user, null);
            dialog1.setView(dialogView);
            final Dialog dialog = dialog1.show();
            dialog.setTitle("Create New Bill");

            final EditText edpassWord;
            final EditText edRePassword;
            final EditText edName;
            final EditText edphone;
            final EditText edUserName;

            edUserName = dialog.findViewById(R.id.edUserName);
            edpassWord = dialog.findViewById(R.id.edPassword);
            edRePassword = dialog.findViewById(R.id.ednhaplaimatkhau);
            edName = dialog.findViewById(R.id.edName);
            edphone = dialog.findViewById(R.id.edPhone);

            dialog.findViewById(R.id.btSave).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    User user = new User();
                    user.setUsername(edUserName.getText().toString().trim());
                    user.setTenNguoiDung(edName.getText().toString().trim());
                    user.setSDT(edphone.getText().toString().trim());
                    user.setPassword(edpassWord.getText().toString().trim());
                    userDAO.insertUser(user);


                    users.add(0, user);
                    userapdapter.notifyDataSetChanged();


                    Toast.makeText(ListUserActivity.this,
                            getString(R.string.notify_add_successful), Toast.LENGTH_SHORT).show();
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
        if (item.getItemId() == R.id.dx){
            startActivity(new Intent(ListUserActivity.this,LoginActivity.class));
            Toast.makeText(this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==R.id.dmk){
            startActivity(new Intent(ListUserActivity.this,ChangePasswordActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

}


