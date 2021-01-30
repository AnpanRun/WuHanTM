package com.lzh.wuhantm.bean;

import com.lzh.wuhantm.BR;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * Created by AnpanRun on 2021/1/29.
 */
public class DBGoods extends BaseObservable{
    //如果是 public 修饰符，则可以直接在成员变量上方加上 @Bindable 注解
    @Bindable
    public String name;

    //如果是 private 修饰符，则在成员变量的 get 方法上添加 @Bindable 注解
    private String details;

    private float price;

    public DBGoods(String name, String details, float price) {
        this.name = name;
        this.details = details;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
        //更新所有字段
        notifyChange();
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
