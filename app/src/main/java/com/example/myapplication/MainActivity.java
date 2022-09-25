package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Phần xử lý của res/layout/activity_main.xml

    // Nghiệp vụ mong muốn: bấm vào tên -> hiển thị thông tin lớp và địa chỉ lên Toast (thông báo nháy)
    // 1. Định nghĩa biến thể hiện TextView
    TextView tv_ho_ten, tv_lop, tv_dia_chi;
    // 2. Tìm ra nó ~ tuy nhiên đoạn code này chưa chạy vào onCreate -> chưa có view mà tìm

    Button btn_chuyen_huong;

    // Phương thức chạy khi bắt đầu ứng dụng, mở phần hiển thị main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. Sau khi view đã được tạo, đã có các TextView + id của nó thì mới tìm được
        tv_ho_ten = findViewById(R.id.ho_ten); // Tìm id trong Resource có tên là ho_ten
        tv_lop = findViewById(R.id.lop);
        tv_dia_chi = findViewById(R.id.dia_chi);
        // Javascript
        // a = document.getElementById('a'); a.addEventListener('click') = function () {}
        // aContent = a.value;
        // 3. Sau khi tìm được thì cần định nghĩa sự kiện click cho TextView vừa tìm được
        tv_dia_chi.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(MainActivity.this, "Thông báo nhanh khi bấm vào lớp", Toast.LENGTH_SHORT).show();
        }
        });
        tv_ho_ten.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
        //               Log.d("test click", "Da click vao ho ten");
                // Toast.makeText định nghĩa việc hiển thị thông báo nháy lên màn hình
        //              // Toast.makeText(ngữ cảnh hiển thị, nội dung, thời gian)
               String lop = tv_lop.getText().toString();
               String dia_chi = tv_dia_chi.getText().toString();
               String toast_text = lop + " " + dia_chi;
               Toast.makeText(MainActivity.this, toast_text, Toast.LENGTH_LONG).show();
           }
        });
        tv_lop.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String lop = tv_lop.getText().toString();
               Toast.makeText(
            MainActivity.this,
               lop + ' ' + lop,
                   Toast.LENGTH_SHORT
           ).show();
           }
        });

        // Sự kiện click cho nút chuyển hướng
        btn_chuyen_huong = findViewById(R.id.btn_chuyen_huong);
        btn_chuyen_huong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Đây sẽ là nơi xử lý bằng Intent: sau khi click sẽ chuyển hướng màn hình
                // Khởi tạo 1 intent mới kèm Activity sẽ được chuyển hướng sang
                Intent intent_chuyen_huong = new Intent(getBaseContext(), InfoActivity.class);
                // Mang thêm thông tin tên, địa chỉ từ MainActivity sang InfoActivity
                String main_ho_ten = tv_ho_ten.getText().toString();
                String main_dia_chi = tv_dia_chi.getText().toString();
                intent_chuyen_huong.putExtra("main_ten", main_ho_ten); // main_ten là tên biến sẽ nhận được ở bên kia
                intent_chuyen_huong.putExtra("main_dia_chi", main_dia_chi);
//              // Chuyển hướng cùng dữ liệu
                startActivity(intent_chuyen_huong);
            }
        });

    }
}