package com.lzh.wuhantm.activity;

import android.os.Bundle;

import com.lzh.wuhantm.R;
import com.lzh.wuhantm.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

/**
 * Created by AnpanRun on 2021/1/29.
 */
public class ViewPageActivity extends AppCompatActivity {
    ViewPager2 pager2;
    ArrayList<HashMap<String, Object>> listItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpage);
        initDate();
        pager2 = findViewById(R.id.vp_page);
        MyAdapter myAdapter = new MyAdapter(this, listItem);
        pager2.setAdapter(myAdapter);
    }

    private void initDate() {
        listItem = new ArrayList<HashMap<String, Object>>();
        for(int i = 0 ; i<3 ;i ++){
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("ItemTile","第"+i+"个标题");
            hashMap.put("ItemText","第"+i+"个内容");
            hashMap.put("ItemImage",R.drawable.ic_launcher_background);
            listItem.add(hashMap);
        }
    }
}
