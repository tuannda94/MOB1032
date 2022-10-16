package com.example.crud.DTO;

public class User {
//    Định nghĩa thông tin bảng và các trường tương ứng trong DB
    public static final String TB_NAME = "users";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
//    Định nghĩa thuộc tính của đối tượng
    String name;
    int id;
//    Định nghĩa các phương thức getter và setter
//    Ctrl N chọn Getter and Setter và chọn cả 2 thuộc tính -> OK
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
