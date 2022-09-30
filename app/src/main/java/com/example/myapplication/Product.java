// New -> Java Class -> Nhập tên + chọn loại Class
// Định nghĩa đối tượng sẽ xuất hiện trên từng phần tử của ListView
package com.example.myapplication;

public class Product {
    int id; // mã sản phẩm
    String name; // tên sản phẩm
    double price; // giá sản phẩm

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
