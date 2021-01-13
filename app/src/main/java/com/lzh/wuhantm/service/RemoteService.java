package com.lzh.wuhantm.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

public class RemoteService extends Service {
    String TAG = "RemoteService";

    AIDL_Service.Stub mBinder = new AIDL_Service.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void myAIDL_Service() throws RemoteException {
            Log.d(TAG,"客户单通过AIDL与远程后台成功通信");
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, TAG + " RemoteService : onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, TAG + " RemoteService : onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, TAG + " RemoteService : onDestroy()");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, TAG + " RemoteService : onBind()");
        //在onBind()返回继承自Binder的Stub类型的Binder，非常重要
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, TAG + " RemoteService : Unbind()");

        return super.onUnbind(intent);
    }

    public class MyBinder extends Binder {

        public void service_connect_Activity() {
            Log.d(TAG, "Service关联了Activity,并在Activity执行了Service的方法");
        }
    }
}
