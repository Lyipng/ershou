package com.example.ershou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Personal_CenterActivity extends AppCompatActivity implements View.OnClickListener {
    Button bt_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);
        bt_back = findViewById(R.id.btn_back);
        bt_back.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_back:
                Intent intent = new Intent(Personal_CenterActivity.this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}