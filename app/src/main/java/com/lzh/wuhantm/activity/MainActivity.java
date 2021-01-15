package com.lzh.wuhantm.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
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
    private Button mBtOpenFg;

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
        mBtOpenFg = (Button) findViewById(R.id.bt_open_fragment);

        mBtGotoRv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RvActivity.class);
                // 标记位属性	含义
                // FLAG_ACTIVITY_SINGLE_TOP	指定启动模式为栈顶复用模式（SingleTop）
                // FLAG_ACTIVITY_NEW_TASK	指定启动模式为栈内复用模式（SingleTask）
                // FLAG_ACTIVITY_CLEAR_TOP	所有位于其上层的Activity都要移除，SingleTask模式默认具有此标记效果
                // FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS	具有该标记的Activity不会出现在历史Activity的列表中，即无法通过历史列表回到该Activity上
                //  AndroidMainfest.xml 二者设置的区别
                //  优先级不同
                //  Intent设置方式的优先级 > Manifest设置方式，即 以前者为准
                //  限定范围不同
                //  Manifest设置方式无法设定 FLAG_ACTIVITY_CLEAR_TOP；Intent设置方式 无法设置单例模式（SingleInstance）

                /**
                 * 通过 Flag 设置启动模式，有谬误的地方：
                 * FLAG_ACTIVITY_NEW_TASK != SingleTask
                 * 应该是：
                 * FLAG_ACTIVITY_NEW_TASK + FLAG_ACTIVITY_CLEAR_TOP == SingleTask
                 *
                 * 而且如果单独用 FLAG_ACTIVITY_CLEAR_TOP，如果 Manifest 是 standard，则目标 Activity 也会出栈，并重新走 onCreate() 方法，不走 onNewIntent() 方法
                 */
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
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

        mBtOpenFg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TMFmActivity.class));
            }
        });
    }

    private void initData() {

    }


    /**
     * onSaveInstanceState()在onPause()之后，onStop()之前调用
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, TAG + " MainActivity : onSaveInstanceState()");
    }

    /**
     * onRestoreInstanceState()在onStart()之后，onResume()之前
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, TAG + " MainActivity : onRestoreInstanceState()");

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