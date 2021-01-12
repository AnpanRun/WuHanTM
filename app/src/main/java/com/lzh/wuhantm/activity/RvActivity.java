package com.lzh.wuhantm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lzh.wuhantm.adapter.MyAdapter;
import com.lzh.wuhantm.MyItemClickListener;
import com.lzh.wuhantm.R;

import java.util.ArrayList;
import java.util.HashMap;

public class RvActivity extends AppCompatActivity implements MyItemClickListener {

    String TAG = "MyRecyclerView";
    private ArrayList<HashMap<String, Object>> listItem;
    private MyAdapter myAdapter;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, TAG + " : onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrv);
        initData();
        initView();
    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.my_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));

        myAdapter = new MyAdapter(this, listItem);
        myAdapter.setOnItemClickListener(this);
        rv.setAdapter(myAdapter);
    }

    private void initData() {
        listItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 100; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemTitle", "第" + i + "行");
            map.put("ItemText", "这是第" + i + "行");
            map.put("ItemImage", R.mipmap.ic_launcher);
            listItem.add(map);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, TAG + " : onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, TAG + " : onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, TAG + " : onPause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, TAG + " : onDestroy()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, TAG + " : onStop()");
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.d(TAG, TAG + " : onPostCreate()");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, TAG + " : onNewIntent()");
    }

    @Override
    public void onItemClick(View view, int position) {
        System.out.println("点击了第" + position + "行");
        Toast.makeText(this, (String) listItem.get(position).get("ItemText"), Toast.LENGTH_SHORT).show();
    }
}