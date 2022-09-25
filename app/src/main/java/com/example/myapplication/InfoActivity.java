package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {
/*
* Chuột phải vào cây thư mục -> chọn New -> Activty -> Empty Activty
*
* Intent: là cầu nối giữa các Activty
*   + Chuyển hướng từ Activity này sang Activity khác
*   + Mang theo dữ liệu cần thiết sang đó
* */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // Nhận dữ liệu từ quá trình gửi qua intent của MainActivity
        // Khai báo 1 biến kiểu Intent để nhận dữ liệu
        Intent intent = getIntent();
        // Nhận dữ liệu bằng các key đã định nghĩa key+value bên MainActivity
        String ten = intent.getExtras().getString("main_ten", "");
        String dia_chi = intent.getExtras().getString("main_dia_chi", "");
        // Hiển thị ra view dựa theo id của các view trong activity_info
        // Tìm kiếm những view cần hiển thị nội dung
        TextView tv_ten = findViewById(R.id.info_ten);
        TextView tv_dia_chi = findViewById(R.id.info_dia_chi);
        // Cập nhật nội dung hiển thị mới cho các TextView vừa rồi
        tv_ten.setText(ten);
        tv_dia_chi.setText(dia_chi);
    }
}