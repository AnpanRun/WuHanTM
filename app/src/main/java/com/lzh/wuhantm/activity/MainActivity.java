package com.lzh.wuhantm.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lzh.wuhantm.R;
import com.lzh.wuhantm.service.TMService;


public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";
    private Button mBtGotoRv;
    private Button mBtStartService;
    private Button mBtStopService;
    private Button mBtBindService;
    private Button mBtUnbindService;

    private TMService.MyBinder myBinder;

    //创建ServiceConnection的匿名类
    private ServiceConnection connection = new ServiceConnection() {
        //重写onServiceConnected()方法和onServiceDisconnected()方法
        //在Activity与Service监理关联和解除关联的时候调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, TAG + " : onServiceConnected");
            //实例化Service的内部类myBinder
            //通过向下转型得到了MyBinder的实例
            myBinder = (TMService.MyBinder) service;
            //在Activity调用Service类的方法
            myBinder.service_connect_Activity();
        }

        //在Activity与Service解除关联的时候调用
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, TAG + " : onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, TAG + " : onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initView() {
        mBtGotoRv = (Button) findViewById(R.id.bt_goto_rv);
        mBtStartService = (Button) findViewById(R.id.bt_start_service);
        mBtStopService = (Button) findViewById(R.id.bt_close_service);
        mBtBindService = (Button) findViewById(R.id.bt_bind_service);
        mBtUnbindService = (Button) findViewById(R.id.bt_unbind_service);

        mBtGotoRv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RvActivity.class);
                startActivity(intent);
            }
        });

        mBtStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TMService.class);
                startService(intent);
            }
        });

        mBtStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TMService.class);
                stopService(intent);
            }
        });

        mBtBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TMService.class);
                //参数说明
                //第一个参数:Intent对象
                //第二个参数:上面创建的Serviceconnection实例
                //第三个参数:标志位
                //这里传入BIND_AUTO_CREATE表示在Activity和Service建立关联后自动创建Service
                //这会使得MyService中的onCreate()方法得到执行，但onStartCommand()方法不会执行
                bindService(intent, connection, BIND_AUTO_CREATE);
            }
        });

        mBtUnbindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用unbindService()解绑服务
                //参数是上面创建的Serviceconnection实例
                unbindService(connection);
            }
        });
    }

    private void initData() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, TAG + " MainActivity : onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, TAG + "MainActivity : onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, TAG + "MainActivity : onPause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, TAG + "MainActivity : onDestroy()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, TAG + "MainActivity : onStop()");
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.d(TAG, TAG + "MainActivity : onPostCreate()");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, TAG + "MainActivity : onNewIntent()");
    }
}