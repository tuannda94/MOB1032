package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.crud.Adapter.UserAdapter;
import com.example.crud.DAO.UserDAO;

public class MainActivity extends AppCompatActivity {
//    Công việc: CRUD users có các trường id, name
//    1. Giao diện listview danh sách user: có thêm 1 nút thêm mới
//    2. Giao diện item của danh sách user: id, name, nút sửa, nút xoá
//    3. Giao diện form tạo mới user và update user
//    4. Khởi tạo DB, Khởi tạo UserDAO, khởi tạo UserDTO, Khởi tạo UserAdapter

    Button lv_btn_add;
    ListView lv_user;
    UserDAO userDAO;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_user);
        // ánh xạ list view ra biến
        lv_user = findViewById(R.id.lv_list);
        // khởi tạo DAO và chạy DB
        userDAO = new UserDAO(this);
        userDAO.open();
        // khởi tạo adapter kèm dữ liệu
        userAdapter = new UserAdapter(userDAO.getAll(), userDAO);
        // setAdapter để hiển thị dữ liệu danh sách
        lv_user.setAdapter(userAdapter);

//        Định nghĩa hành vi click cho nút thêm mới
        lv_btn_add = findViewById(R.id.lv_btn_add);
        lv_btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // khi click sẽ gọi hàm hiển thị dialog thêm mới với ngữ cảnh là activity hiện tại
                userAdapter.showDialogAdd(MainActivity.this);
            }
        });
    }
}