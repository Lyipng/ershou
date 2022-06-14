package com.example.ershou;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt_publish, bt_cansal;
    EditText et_title, et_price, et_phone, et_description;
    Spinner spType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_commodity);
        bt_cansal = findViewById(R.id.btn_back);
        bt_publish = findViewById(R.id.btn_publish);
        bt_cansal.setOnClickListener(this);
        bt_publish.setOnClickListener(this);
        spType = findViewById(R.id.spn_type);
        et_title = findViewById(R.id.et_title);
        et_price = findViewById(R.id.et_price);
        et_phone =findViewById(R.id.et_phone);
        et_description = findViewById(R.id.et_description);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_publish:
                addcommodity();
                finish();
                break;
        }
    }

    private void addcommodity() {

        String title = et_title.getText().toString();
        String category = spType.getSelectedItem().toString();
        String price = et_price.getText().toString();
        String phone = et_phone.getText().toString();
        String description = et_description.getText().toString();
        _User user = BmobUser.getCurrentUser(_User.class);

        Commodity commodity = new Commodity();
        commodity.setUid(commodity.getObjectId());
        commodity.setTitle(title);
        commodity.setCategory(category);
        commodity.setPrice(price);
        commodity.setPhone(phone);
        commodity.setDescription(description);
        commodity.setStudent(user);

        commodity.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    Toast.makeText(AddActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddActivity.this, "发布失败" + e, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}