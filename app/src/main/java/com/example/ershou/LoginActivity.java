package com.example.ershou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 登录界面Activity类
 */
public class LoginActivity extends AppCompatActivity {

    EditText EtStuNumber,EtStuPwd;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Bmob.initialize(this, "23edcedb0fe3ae5e083cc69d28d5acac");

        EtStuNumber = findViewById(R.id.et_username);
        EtStuPwd = findViewById(R.id.et_pwd);
        TextView tv_signup =findViewById(R.id.tv_sign_up);
        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        Button btLogin = findViewById(R.id.bt_login);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckInput()) {
                    final _User user = new _User();

                    user.setUsername(EtStuNumber.getText().toString());
                    user.setPassword(EtStuPwd.getText().toString());
                    user.login(new SaveListener<_User>() {
                        @Override
                        public void done(_User user, BmobException e) {
                            if(e==null){//账号密码正确
                                Toast.makeText(LoginActivity.this,"登录成功！",Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                            else {//账号密码错误
                                Toast.makeText(LoginActivity.this,"账号或密码输入错误!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
    public boolean CheckInput() {
        String StuNumber = EtStuNumber.getText().toString();
        String StuPwd = EtStuPwd.getText().toString();
        if(StuNumber.trim().equals("")) {
            Toast.makeText(LoginActivity.this,"账号不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(StuPwd.trim().equals("")) {
            Toast.makeText(LoginActivity.this,"密码不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
