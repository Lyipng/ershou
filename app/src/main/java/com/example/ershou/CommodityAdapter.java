package com.example.ershou;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class CommodityAdapter extends ArrayAdapter<Commodity> {

    private List<Commodity> mCommoditydata;
    private Context context;
    private int resource;

    public List<Commodity> getmCommoditydata(){
        return mCommoditydata;
    }

    public void setCdata(List<Commodity> mdata){
        this.mCommoditydata = mdata;
    }

    public CommodityAdapter(@NonNull Context context, int resource, List<Commodity> Data) {
        super(context, resource, Data);
        this.context = context;
        this.mCommoditydata = Data;
        this.resource = resource;
    }

    public Context getmContext() {
        return context;
    }

    public void setmContext(Context mContext) {
        this.context = mContext;
    }

    public int getResourceId() {
        return resource;
    }

    public void setResourceId(int resourceId) {
        this.resource = resourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Commodity commodity = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null)
        {
            view = LayoutInflater.from(getContext()).inflate(resource, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else
        {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv_name.setText(commodity.getTitle());
        viewHolder.tv_type.setText(commodity.getCategory());
        viewHolder.tv_description.setText(commodity.getDescription());
        viewHolder.tv_price.setText(commodity.getPrice());

        //查找发布商品的账号
        BmobQuery<_User> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("objectId",commodity.getStudent().getObjectId());
        bmobQuery.findObjects(new FindListener<_User>() {
            @Override
            public void done(List<_User> object, BmobException e) {
                if (e == null) {
                    viewHolder.tv_username.setText(object.get(0).getObjectId());
                    Toast.makeText(context, "刷新成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "查询失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    class ViewHolder {
        TextView tv_name,tv_type,tv_description,tv_price,tv_username;
        public ViewHolder(View view) {
             tv_name = view.findViewById(R.id.tv_name);
             tv_type = view.findViewById(R.id.tv_type);
             tv_description = view.findViewById(R.id.tv_description);
             tv_price = view.findViewById(R.id.tv_price);
             tv_username = view.findViewById(R.id.tv_username);
        }
    }
}
