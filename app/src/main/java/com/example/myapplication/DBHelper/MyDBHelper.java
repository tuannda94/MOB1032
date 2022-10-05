package com.example.myapplication.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Class kế thừa lại đối tượng SQLiteOpenHelper để khởi tạo và quản lý DB
public class MyDBHelper extends SQLiteOpenHelper {
//    Khởi tạo thông tin DB_NAME và DB_VERSION
    public static final String DB_NAME = "mob1032";
    public static final int DB_VERSION = 1;

//    Hàm khởi tạo
    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
//    Ghi đè hàm onCreate để khởi tạo thông tin trong DB mob1032, hàm này đã đc dựng ở SQLiteOpenHelper
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//      Có các câu lệnh tạo bảng
//      "CREATE TABLE ten_bang (ten_truong_thong_tin KIEU_DU_LIEU NOT NULL/DEFAULT 0), PRIMARY KEY (ten_truong AUTOINCREMENT) "
        String sql_create_products_table = "CREATE TABLE products (" +
            "id INTEGER NOT NULL, " +
            "name TEXT NOT NULL," +
            "price NUMERIC DEFAULT 0," +
            "PRIMARY KEY (id AUTOINCREMENT)" +
        ")";

//        Tạo 1 số bản ghi mẫu
        String sql_insert_products = "INSERT INTO products (name, price) " +
                "VALUES ('Iphone 12', 120000), ('SamSung S22', 10000), ('Oppo find X', 14000)";
//        Sau khi đã định nghĩa câu lệnh tạo bảng và tạo bản ghi -> thực thi câu lệnh để tạo lần lượt
        sqLiteDatabase.execSQL(sql_create_products_table);
        sqLiteDatabase.execSQL(sql_insert_products);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        Tạm thời không cần xử lý quá trình thay đổi DB khi upgrade phiên bản
    }
//    MyDBHelper được kết hợp với 1 thằng tên là DAO (Data Access Object)
//    DAO sẽ là 1 tập hợp các hàm liên quan đến việc truy vấn của đối tượng (bảng) nào đó
//    -> Định nghĩa ra 1 object có tên là DoiTuongDAO (VD ProductDAO) có các phương thức: lấy ds, lấy 1 bản ghi, thêm, sửa, xoá
//    khi nào cần dùng chỉ cần gọi đến hàm đó là được mà không cần viết truy vấn ở ngoài
}
