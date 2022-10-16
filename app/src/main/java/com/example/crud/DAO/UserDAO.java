package com.example.crud.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.crud.DTO.User;
import com.example.crud.Database.DBHelper;

import java.util.ArrayList;

public class UserDAO {
    SQLiteDatabase db;
    DBHelper dbHelper;

    public UserDAO(Context context) {
        dbHelper = new DBHelper(context);
    }
    public void open() {db = dbHelper.getWritableDatabase();}
    public void close() {dbHelper.close();}

    public ArrayList<User> getAll() {
//        1. Khởi tạo danh sách
        ArrayList<User> listUser = new ArrayList<User>();
//        2. Truy vấn và gán kq vào con trỏ
//        Cursor cursor = db.rawQuery("SELECT * FROM users");
        Cursor cursor = db.query(
                User.TB_NAME,
                new String[] {"*"},
                null,
                null,
                null,
                null,
                null);
//        3. Duyệt tất cả để lấy ra danh sách gán vào ds ở bước 1
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                User userObj = new User(); // khởi tạo đối tượng và gán giá trị cho thuộc tính
                userObj.setId(cursor.getInt(0));
                userObj.setName(cursor.getString(1));

                listUser.add(userObj); // thêm phần tử vào danh sách ở bước 1

                cursor.moveToNext(); // thực hiện lặp tiếp, nếu không có thì sẽ bị treo app
            }
        }

        return listUser;
    }

    public int delete(User userObj) {
//        điều kiện where có thể nhận nhiều giá trị ở ?, nên tham số thứ 3 là 1 mảng giá trị lần lượt của ?
        int response = db.delete(
                User.TB_NAME,
                "id = ?",
                new String[] {userObj.getId() + ""});
        return response;
    }

    public long insert(User userObj) {
//        Sử dụng kiểu dữ liệu ContentValues để làm việc với SQLite, mỗi bản ghi là 1 đối tượng ContentValues
        ContentValues cv = new ContentValues();
        cv.put(User.COL_NAME, userObj.getName());

        long response = db.insert(User.TB_NAME, null, cv);
        return response;
    }

    public int update(User userObj) {
        ContentValues cv = new ContentValues();
        cv.put(User.COL_NAME, userObj.getName());

        int response = db.update(
                User.TB_NAME,
                cv,
                "id = ?",
                new String[] {userObj.getId() + ""}
        );

        return response;
    }
}
