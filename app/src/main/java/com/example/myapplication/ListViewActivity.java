package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewActivity extends AppCompatActivity {
    // Khởi tạo đối tượng kiểu ListView
    ListView lv_products;
    // Định nghĩa mảng dữ liệu đầu vào của ds
    String [] products = {"Iphone", "Samsung", "Oppo", "LG", "Iphone", "Samsung", "Oppo", "LG", "Iphone", "Samsung", "Oppo", "LG", "Iphone", "Samsung", "Oppo", "LG"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        // Ánh xạ view ra biến / Tìm kiếm thẻ
        lv_products = findViewById(R.id.lv_list);

        // Adapter là thành phần trung gian dùng để ghép nối dữ liệu với view
        // Dữ liệu -> Adapter -> View
        // Khởi tạo adapter
        ArrayAdapter<String> productListAdapter = new ArrayAdapter<String>(
            ListViewActivity.this, // ngữ cảnh đang làm việc là activity nào
                android.R.layout.simple_list_item_1, // loại layout muốn hiển thị cho từng phần tử con bên trong
                products // dữ liệu đầu vào dạng mảng String
        );
        // Đưa adapter vào listview đang cần hiển thị
        lv_products.setAdapter(productListAdapter);
    }
}