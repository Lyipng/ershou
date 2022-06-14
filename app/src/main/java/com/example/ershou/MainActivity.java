package com.example.ershou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton bt_home, bt_add, bt_personal;
    ListView listView;
    CommodityAdapter adapter;
    private List<Commodity> commodity;
    Context context = this;
    String commodity_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定
        bt_home = findViewById(R.id.ib_home_page);
        bt_add = findViewById(R.id.ib_add_product);
        bt_personal = findViewById(R.id.ib_personal);
        bt_add.setOnClickListener(this);
        bt_home.setOnClickListener(this);
        bt_personal.setOnClickListener(this);
        listView = findViewById(R.id.lv_all_commodity);
        initData();
        getData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_add_product:
                Intent intent1 = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent1);
                break;
            case R.id.ib_home_page:
                initData();
                getData();
                break;
            case R.id.ib_personal:
                Intent intent2 = new Intent(MainActivity.this, Personal_CenterActivity.class);
                startActivity(intent2);
                break;
        }
    }

    //初始化对象并保存数据
    private void initData() {
        //获取首页数据列表
        commodity = new ArrayList<>();
        adapter = new CommodityAdapter(MainActivity.this, R.layout.layout_citem, commodity);

        //  CommodityAdapter CommodityAdapter = new CommodityAdapter(this, R.layout.layout_all_commodity, commodity);
        listView.setAdapter(adapter);
    }

    //从Bmob后台数据库，获取首页卡片数据
    private void getData() {
        BmobQuery<Commodity> query = new BmobQuery<>();
        _User user = new _User();
        user.setObjectId(commodity_id);
        //按照时间降序
        query.order("-createdAt");
        query.findObjects(new FindListener<Commodity>() {
            @Override
            public void done(List<Commodity> Data, BmobException e) {
                if (e == null) {
                    for (Commodity datum : Data) {
                        adapter.add(datum);
                    }
                    adapter.notifyDataSetChanged();
                    //Toast.makeText(context, "刷新成功" + Data.size(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("BMOB", e.toString());
                }
            }
        });
    }
}