package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Sau khi view đã hiển thị xong, tìm nút login
        Button btn_login = findViewById(R.id.login_btn);
        // Sau khi nút login được click thì mới đọc dữ liệu của 2 ô EditText
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tìm các EditText và lấy nội dung user đã gõ
                EditText et_email = findViewById(R.id.login_et_email);
                EditText et_password = findViewById(R.id.login_et_password);
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();
//                Log.d("email", Boolean.toString(email == ""));
//                Log.d("pass", password);
                // Kiểm tra dữ liệu, nếu 1 trong 2 không có nội dung thì báo là chưa nhập đủ
                // chuỗi.matches("") -> true có nghĩa là chuỗi rỗng
                // chuỗi.trim().length() == 0 -> có nghĩa là chuỗi rỗng
//              // so sánh chuỗi chuỗi.trim().equals("chuỗi được so sánh")
                if (email.matches("") || password.matches("")) {
                    Toast.makeText(
                            LoginActivity.this,
                            "Vui lòng nhập đầy đủ thông tin",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (email.matches("admin@gmail.com") && password.matches("123")) {
                    // Thông báo sau khi đăng nhập thành công
                    Toast.makeText(
                            LoginActivity.this,
                            "Đăng nhập thành công",
                            Toast.LENGTH_SHORT
                    ).show();
                    // Chuyển activity sang LoginSuccessActivity kèm dữ liệu
                    Intent intent_login_success = new Intent(getBaseContext(), LoginSuccessActivity.class);
                    intent_login_success.putExtra("login_email", email);
                    intent_login_success.putExtra("login_password", password);
                    startActivity(intent_login_success);
                } else {
                    Toast.makeText(
                            LoginActivity.this,
                            "Đăng nhập thất bại",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });
    }
}