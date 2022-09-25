package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LoginSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        // Nhận dữ liệu thông tin đăng nhập từ LoginActivity
        Intent intent = getIntent();
        String ls_email = intent.getExtras().getString("login_email");
        String ls_password = intent.getExtras().getString("login_password");
        // Hiển thị lên các TextView đang có
        TextView tv_ls_email = findViewById(R.id.ls_email);
        TextView tv_ls_password = findViewById(R.id.ls_password);
        tv_ls_email.setText(ls_email);
        tv_ls_password.setText(ls_password);
    }
}