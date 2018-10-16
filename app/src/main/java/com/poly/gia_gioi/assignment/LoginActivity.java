package com.poly.gia_gioi.assignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.poly.gia_gioi.assignment.adapter.UserAdapter;
import com.poly.gia_gioi.assignment.database.DatabaseHelper;
import com.poly.gia_gioi.assignment.model.User;
import com.poly.gia_gioi.assignment.sqlitedao.UserDAO;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText edUsername;
    private EditText edPassword;
    private List<User> users;
    private UserAdapter userAdapter;

    private UserDAO userDAO;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        databaseHelper = new DatabaseHelper(this);
        userDAO = new UserDAO(databaseHelper);

//        User user =new User();
        User user = new User();
        user.setPassword("admin12345");
        user.setUsername("admin123");
        user.setSDT("0367096535");
        user.setTenNguoiDung("Gia Giỏi");
        user.setAnhNguoiDung(R.drawable.emthree);
        userDAO.insertUser(user);

        Toolbar toolbarLogin = findViewById(R.id.toolbarLogin);
        toolbarLogin.setTitle("Login");
        setSupportActionBar(toolbarLogin);
        edUsername=findViewById(R.id.edUserNam);
        edPassword=findViewById(R.id.edPassword);

    }
     public void kiemtra() {

        String checkname = edUsername.getText().toString().trim();
        String pass = edPassword.getText().toString().trim();

        if (checkname.equals("")) {
            edUsername.setError(getString(R.string.error_UserName));

            return;
        }

         String[] b = {"!", "~", "@", "#", "$", "%", "^", "&", "*", "*", "(", ")",
                 "_", "-", "=", "+", "[", "]", ";", ":", "\\", "|", "?", "/", "<", ">", ".", ",", "'"};

         for (String aB : b) {
             if (checkname.contains(aB)) {
                 edUsername.setError(getString(R.string.error_Ki_Tu_Dac_Bite));
                 return;
             }

         }

         if (pass.equals("")) {
             edPassword.setError(getString(R.string.error_PassWord));
             return;
         }

         for (int i = 0; i < b.length; i++) {
             if (pass.indexOf(b[i]) > -1) {

                 edPassword.setError(getString(R.string.error_Ki_Tu_Dac_Bite));
                 return;
             }

         }
         if (pass.length() < 4) {
             edPassword.setError(getString(R.string.error_PassWord_It_Hon_6Ki_Tu));
             return;
         }
            UserDAO userDAO = new UserDAO(databaseHelper);
            User user = userDAO.getUserByUserName(checkname);
            userDAO.insertUser(user);
        if (user == null) {
            Toast.makeText(LoginActivity.this,
                    getString(R.string.notify_wrong_username_or_password),
                    Toast.LENGTH_SHORT).show();
        } else {
            String passwordInDB = user.getPassword();

            if (passwordInDB.equals(pass)) {
                startActivity(new Intent(LoginActivity.this, HomeAtivity.class));
                finish();
            } else {
                Toast.makeText(LoginActivity.this,
                        getString(R.string.notify_wrong_username_or_password),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void checkLogin(View view) {
        kiemtra();
        CheckBox chkRememberPass;
        String strUser, strPass;
        chkRememberPass = (CheckBox) findViewById(R.id.chkRememberPass);

        strUser = edUsername.getText().toString();
        strPass = edPassword.getText().toString();
        if (strUser.isEmpty()||strPass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Tên đăng nhập và mật khẩu không được bỏ trống",
                    Toast.LENGTH_SHORT).show();
        }else {
            if (strUser.equalsIgnoreCase("giagioi")&&strPass.equalsIgnoreCase("gioikin123- ")){
                rememberUser(strUser,strPass,chkRememberPass.isChecked());
                finish();
            }else {
                Toast.makeText(getApplicationContext(),"Tên đăng nhập và mật khẩu không đúng",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void rememberUser(String u, String p, boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status){
            //xoa tinh trang luu tru truoc do
            edit.clear();
        }else {
            //luu du lieu
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }
        //luu lai toan bo
        edit.commit();
    }

    public void checkHuy(View view) {
        edUsername.setText("");
        edPassword.setText("");
    }
}
