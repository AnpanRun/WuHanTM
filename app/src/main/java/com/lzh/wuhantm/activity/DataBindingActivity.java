package com.lzh.wuhantm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableList;
import androidx.databinding.ObservableMap;

import android.os.Bundle;
import android.util.Log;

import com.lzh.wuhantm.BR;
import com.lzh.wuhantm.R;
import com.lzh.wuhantm.bean.DBGoods;
import com.lzh.wuhantm.bean.User;
import com.lzh.wuhantm.databinding.ActivityDataBindingBinding;

import java.util.Random;

/**
 * Android DataBinding 从入门到进阶
 * https://www.jianshu.com/p/bd9016418af2
 */
public class DataBindingActivity extends AppCompatActivity {

    private DBGoods dbGoods;
    private User user;
    private ObservableMap<String, String> map;
    private ObservableList<String> list;

    String TAG = "DataBindingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_data_binding);
        ActivityDataBindingBinding activityDataBindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);

        dbGoods = new DBGoods("Anpan", "面包超人", 11);
        activityDataBindingBinding.setGoods(dbGoods);
        activityDataBindingBinding.setGoodshandler(new GoodsHandler());

        user = new User();
        user.setPassword("888");
        user.setUsername("Anpan");
        activityDataBindingBinding.setUserInfo(user);


        /**
         * 实现了 Observable 接口的类允许注册一个监听器，当可观察对象的属性更改时就会通知这个监听器，此时就需要用到 OnPropertyChangedCallback
         * 当中 propertyId 就用于标识特定的字段
         */
        dbGoods.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (propertyId == BR.name) {
                    Log.d(TAG, "BR.name");
                } else if (propertyId == BR.details) {
                    Log.d(TAG, "BR.details");
                } else if (propertyId == BR._all) {
                    Log.d(TAG, "BR._all");
                } else {
                    Log.d(TAG, "未知");
                }
            }
        });


        list = new ObservableArrayList<String>();
        map = new ObservableArrayMap<String, String>();

        list.add("aaa");
        list.add("bbb");

        map.put("name","Anpan");
        map.put("age","18");

        activityDataBindingBinding.setList(list);
        activityDataBindingBinding.setMap(map);
    }

    public class GoodsHandler {
        public void changeGoodName() {
            dbGoods.setName("code" + new Random().nextInt(100));
            dbGoods.setPrice(new Random().nextInt(100));
        }

        public void changeGoodDetails() {
            dbGoods.setDetails("hi" + new Random().nextInt(100));
            dbGoods.setPrice(new Random().nextInt(100));
        }
    }
}