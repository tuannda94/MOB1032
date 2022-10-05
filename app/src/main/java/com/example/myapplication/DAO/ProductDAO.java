package com.example.myapplication.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.DBHelper.MyDBHelper;
import com.example.myapplication.Product;

import java.util.ArrayList;

// DAO này sẽ chứa các phương thức để truy vấn cho bảng products
// các hàm lấy ds, lấy 1, thêm, sửa, xoá
public class ProductDAO {
    SQLiteDatabase db;
    MyDBHelper dbHelper;

    public ProductDAO(Context context) {
        dbHelper = new MyDBHelper(context); // khởi tạo 1 đối tượng helper mới
        db = dbHelper.getWritableDatabase(); // nếu chưa có csdl thì sẽ tự tạo
    }

//    Định nghĩa các hàm lấy dữ liệu qua truy vấn bảng products
//    Hàm lấy ra tất cả các bản ghi
    public ArrayList<Product> getAll() {
        // 1. Định nghĩa biến danh sách sẽ trả về
        ArrayList<Product> product_list = new ArrayList<Product>();
        // 2. Viết truy vấn
        String sql = "SELECT * FROM products";
        // 3. Thực thi truy vấn
        Cursor cursor = db.rawQuery(sql, null);
        // 4. Trả về dữ liệu (kiểm tra xem có dl hay không)
        if (cursor.moveToFirst()) { // có dữ liệu nếu trả về true
            while (!cursor.isAfterLast()) { // khi nào qua phần tử cuối thì dừng
                // Lấy dữ liệu từ con trỏ sau đó gán vào đối tượng Product
                int cursor_product_id = cursor.getInt(0);
                String cursor_product_name = cursor.getString(1);
                double cursor_product_price = cursor.getDouble(2);
                // gán vào đối tượng product
                Product product = new Product(
                    cursor_product_id,
                    cursor_product_name,
                    cursor_product_price
                );
                // add vào trong product_list sau mỗi vòng lặp
                product_list.add(product);
//              // THẦY QUÊN ĐIỀU KIỆN LẶP
                cursor.moveToNext();
            }
        }

        return product_list;
    }

}
