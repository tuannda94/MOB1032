package com.example.crud.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crud.DAO.UserDAO;
import com.example.crud.DTO.User;
import com.example.crud.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
//    Dữ liệu của Adapter cần là danh sách đối tượng đang tương tác & DAO của nó
    ArrayList<User> listUser;
    UserDAO userDAO;

    public UserAdapter(ArrayList<User> listUser, UserDAO userDAO) {
        this.listUser = listUser;
        this.userDAO = userDAO;
    }

//    Override lại các phương thức cần có của 1 Adapter: getCount, getItem, getItemId, getView
//    Ctr N -> Override methods -> chọn 4 phương thức bên trên -> OK
    @Override
    public int getCount() {
        return listUser.size();
    }

    @Override
    public Object getItem(int i) {
        User userObj = listUser.get(i);
        return userObj;
    }

    @Override
    public long getItemId(int i) {
        User userObj = listUser.get(i);
        return userObj.getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView; // kiểm tra nếu có truyền vào view thì dùng còn không thì mặc định sẽ là item_list_view_user.xml

        if (view == null) {
            itemView = View.inflate(viewGroup.getContext(), R.layout.item_list_view_user, null);
        } else {
            itemView = view;
        }

//        Lấy thông tin bản ghi ở vị trí thứ i
        final User userObj = listUser.get(i);
        final int _index = i;

//        Ánh xạ các view vào biến và set giá trị hiển thị
        TextView tv_id = itemView.findViewById(R.id.item_tv_id);
        TextView tv_name = itemView.findViewById(R.id.item_tv_name);
        TextView tv_edit = itemView.findViewById(R.id.item_tv_edit);
        TextView tv_del = itemView.findViewById(R.id.item_tv_del);

        tv_id.setText(userObj.getId() + ""); // phải ép kiểu về chuỗi thì mới hiển thị được
        tv_name.setText(userObj.getName());

//        xử lý sự kiện click nút sửa
        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Khởi tạo dialog
                final Dialog dialog = new Dialog(viewGroup.getContext());
                dialog.setTitle("Chỉnh sửa thành viên");
                dialog.setContentView(R.layout.dialog_edit_user);

                EditText dl_et_name = dialog.findViewById(R.id.dl_edit_et_name);
                dl_et_name.setText(userObj.getName());
                Button dl_btn_update = dialog.findViewById(R.id.dl_edit_btn_update);
                dl_btn_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Lấy nội dung mới mà người dùng sửa ở EditText gán vào cho obj để dùng obj đó cập nhật
                        userObj.setName(dl_et_name.getText().toString());
                        int response = userDAO.update(userObj); // cập nhật bản ghi trong DB
                        if (response > 0) {
                            listUser.set(_index, userObj); // set dữ liệu mới cho danh sách hiển thị
                            notifyDataSetChanged(); // cập nhật lại việc hiển thị danh sách ra màn hình
                        } else {
                            Toast.makeText(viewGroup.getContext(), "Lỗi!", Toast.LENGTH_SHORT).show();
                        }

                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

//        xử lý sự kiện click nút xoá
        tv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Định nghĩa dialog builder, tiêu đề, nội dung, 2 nút sự kiện đồng ý hoặc k
                AlertDialog.Builder builder = new AlertDialog.Builder(viewGroup.getContext());
                builder.setTitle("Xoá thành viên?");
                builder.setMessage("Bạn có chắc chắn muốn xoá không?");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int response = userDAO.delete(userObj);
                        if (response > 0) {
                            listUser.remove(_index);
                            notifyDataSetChanged(); // cập nhật lại dữ liệu hiển thị ở danh sách
                        } else {
                            Toast.makeText(viewGroup.getContext(), "Lỗi!", Toast.LENGTH_SHORT).show();
                        }
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create(); // khởi tạo 1 dialog từ builder
                dialog.show();
            }
        });

        return itemView;
    }

    public void showDialogAdd(Context context) {
//        Khởi tạo dialog: màn hình nhỏ nổi lên trên activity đang chạy
        final Dialog dialog = new Dialog(context);
//        set nội dung hiển thị là layout đã tạo
        dialog.setTitle("Thêm mới thành viên");
        dialog.setContentView(R.layout.dialog_add_user);
//        lấy nội dung người dùng gõ vào, xử lý sự kiện click để lưu dữ liệu sau đó show
        EditText dl_add_et_name = dialog.findViewById(R.id.dl_add_et_name);
        Button dl_add_btn_create = dialog.findViewById(R.id.dl_add_btn_create);

        dl_add_btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = dl_add_et_name.getText().toString();
                User userObj = new User();
                userObj.setName(name);

                long response = userDAO.insert(userObj);
                if (response > 0) {
//                    notifyDataSetChanged(); // cập nhật lại dữ liệu hiển thị ở danh sách
                    listUser.clear(); // xoá ds cũ
                    listUser.addAll(userDAO.getAll()); // gán lại ds mới lấy từ DB ra
                    //Nếu thành công thì đóng dialog luôn
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Không thêm được!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }
}
