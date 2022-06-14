package com.example.ershou;

import cn.bmob.v3.BmobObject;

/**
 * 商品实体类
 */
public class Commodity extends BmobObject {

    private String uid;
    //标题
    private String title;
    //类别
    private String category;
    //价格
    private String price;
    //联系方式
    private String phone;
    //商品描述
    private String description;

    private _User student;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public _User getStudent() {
        return student;
    }

    public void setStudent(_User student) {
        this.student = student;
    }

}
