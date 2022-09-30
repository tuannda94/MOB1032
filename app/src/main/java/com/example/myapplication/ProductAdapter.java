package com.example.myapplication;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    // Định nghĩa dữ liệu đầu vào
    ArrayList<Product> list_product;

    public ProductAdapter(ArrayList<Product> list_product) {
        this.list_product = list_product;
    }

    // Chuột phải -> Generate -> Override -> select toàn bộ trong
    // android.widget.Adapter
    // Dưới đây là 4 phương thức bắt buộc phải có của Adapter
    @Override
    public int getCount() {
        // Trả về số lượng phần tử trong 1 ds
        return list_product.size();
    }

    @Override
    public Object getItem(int i) {
        // Trả về phần tử ở vị trí i
        Product product = list_product.get(i);
        return product;
    }

    @Override
    public long getItemId(int i) {
        // Trả về id của phần tử ở vị trí i
        Product product = list_product.get(i);
        return product.id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //  Định nghĩa lại việc hiển thị phần tử theo row_product_list_view.xml
        // trả về 1 view là dòng trong danh sách Product đã có
        // 3 tham số (i là vị trí hiên tại đang duyệt, view thể hiện phần tử, ngữ cảnh)

        // 1. Trả về 1 view là phần tử trong danh sách đang duyệt
        View row; // biến thể hiện view của phần tử
        if (view == null) { // nếu không truyền view vào thì lấy view = view đã vẽ trước
            row = View.inflate(
                viewGroup.getContext(),
                R.layout.row_product_list_view,
            null
            );
        } else { // nếu có thì gán bằng view đó
            row = view;
        }

        // 2. Lấy dữ liệu của phần tử hiện tại
        Product product = list_product.get(i);
        // 3. Gán cho phần hiển thị đó
        // phần hiển thị hiện tại chính là biến row, tìm cái phần hiển thị theo row đó
        TextView clv_item_name = row.findViewById(R.id.clv_item_name);
        TextView clv_item_price = row.findViewById(R.id.clv_item_price);
        clv_item_name.setText(product.name);
        clv_item_price.setText(product.price + "");
        // 4. Trả về kết quả là row đã đóng dữ liệu vào
        return row;
    }
}
