package com.lzh.wuhantm.br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 自定义广播接收者BroadcastReceiver
 * 继承BroadcastReceivre基类
 * 必须复写抽象方法onReceive()方法
 * 广播接收器接收到相应广播后，会自动回调 onReceive() 方法
 * 一般情况下，onReceive方法会涉及 与 其他组件之间的交互，如发送Notification、启动Service等
 * 默认情况下，广播接收器运行在 UI 线程，因此，onReceive()方法不能执行耗时操作，否则将导致ANR
 */

// 继承BroadcastReceivre基类
public class TMBroadcastReceiver extends BroadcastReceiver {
    String TAG = "TMBroadcastReceiver";

    // 复写onReceive()方法
    // 接收到广播后，则自动调用该方法
    @Override
    public void onReceive(Context context, Intent intent) {
        //写入接收广播后的操作
        Log.d(TAG, "TMBroadcastReceiver onReceive");
    }

    /**
     * 广播的类型主要分为5类：
     *
     * 普通广播（Normal Broadcast）
     * 系统广播（System Broadcast）
     * 有序广播（Ordered Broadcast）
     * 粘性广播（Sticky Broadcast）
     * App应用内广播（Local Broadcast）

     */
}
