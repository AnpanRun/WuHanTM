package com.lzh.wuhantm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lzh.wuhantm.R;


public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";
    private Button mBtGotoRv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, TAG + " : onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initView() {
        mBtGotoRv = (Button)findViewById(R.id.bt_goto_rv);

        mBtGotoRv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RvActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {

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
}