package com.example.ershou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {

    EditText tvStuNumber,tvStuPwd,tvStuConfirmPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnCancel = findViewById(R.id.bt_cansel);
        //返回到登录界面
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvStuNumber = findViewById(R.id.re_username);
        tvStuPwd = findViewById(R.id.re_pwd);
        tvStuConfirmPwd = findViewById(R.id.re_confirm_pwd);

        //注册点击事件
        Button btnRegister = findViewById(R.id.bt_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //首先确保不为空
                if(CheckInput()) {

                    final _User user = new _User();
                    user.setUsername(tvStuNumber.getText().toString().trim());
                    user.setPassword(tvStuPwd.getText().toString().trim());
                    user.signUp(new SaveListener<_User>() {
                        @Override
                        public void done(_User user, BmobException e) {
                            if(e==null){
                                Toast.makeText(RegisterActivity.this,"恭喜你注册成功!",Toast.LENGTH_SHORT).show();

                                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else
                            {
                                Toast.makeText(RegisterActivity.this,"信息输入错误，请检查"+e,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    public boolean CheckInput() {
        String username = tvStuNumber.getText().toString();
        String password = tvStuPwd.getText().toString();
        String confirm_password = tvStuConfirmPwd.getText().toString();

        if(username.trim().equals("")) {
            Toast.makeText(RegisterActivity.this,"用户名不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.trim().equals("")) {
            Toast.makeText(RegisterActivity.this,"密码不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(confirm_password.trim().equals("")) {
            Toast.makeText(RegisterActivity.this,"确认密码不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!password.trim().equals(confirm_password.trim())) {
            Toast.makeText(RegisterActivity.this,"两次密码输入不一致!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}