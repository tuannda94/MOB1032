package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.myapplication.DAO.ProductDAO;

import java.util.ArrayList;

public class CustomListView extends AppCompatActivity {
//    Tìm ra list view hiển thị dữ liệu
//    Định nghĩa dữ liệu kiểu Product
//    Định nghĩa Adaper tương ứng kết nối 2 thành phần đó

    ListView clv_products;
    ArrayList<Product> list_product;
    ProductAdapter adapter;
//  Định nghĩa DAO
    ProductDAO productDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);

        // Ánh xạ view list view
        clv_products = findViewById(R.id.clv_list);
//        // Khởi tạo dữ liệu
//        list_product = new ArrayList<Product>();
//        list_product.add(new Product(1, "Iphone 14", 12000));
//        list_product.add(new Product(2, "Iphone 13", 10000));
//        list_product.add(new Product(3, "Iphone 12", 11000));
//        list_product.add(new Product(4, "Iphone 11", 9000));

//        Thay vì khởi tạo dữ liệu mẫu thì khởi tạo ProductDAO để gọi hàm getAll lấy dữ liệu trong DB
        productDAO = new ProductDAO(this);
        Log.d("ABC", productDAO.getAll().size() + "");
        list_product = productDAO.getAll();

        // Khởi tạo adapter và ghép nối dữ liệu với ListView
        adapter = new ProductAdapter(list_product);
        // Gắn adapter vào ListView
        clv_products.setAdapter(adapter);
    }
}