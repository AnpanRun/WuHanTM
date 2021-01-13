package com.lzh.wuhantm.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.lzh.wuhantm.R;
import com.lzh.wuhantm.activity.MainActivity;

public class TMService extends Service {
    String TAG = "TMService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, TAG + " TMService : onCreate()");

        //添加下列代码将后台Service变成前台Service
        //构建"点击通知后打开MainActivity"的Intent对象
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("前台的服务通知标题"); //设置通知的标题
        builder.setContentText("前台的服务通知内容"); //设置通知的内容
        builder.setSmallIcon(R.mipmap.ic_launcher); //设置通知的图标
        builder.setContentIntent(pendingIntent);  //设置点击通知后的操作

        Notification notification = builder.getNotification();//将Builder对象转变成普通的notification
        startForeground(1, notification);//让Service变成前台Service,并在系统的状态栏显示出来
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, TAG + " TMService : onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, TAG + " TMService : onDestroy()");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, TAG + " TMService : onBind()");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, TAG + " TMService : Unbind()");

        return super.onUnbind(intent);
    }

    public class MyBinder extends Binder {

        public void service_connect_Activity() {
            Log.d(TAG, "Service关联了Activity,并在Activity执行了Service的方法");
        }
    }
}
