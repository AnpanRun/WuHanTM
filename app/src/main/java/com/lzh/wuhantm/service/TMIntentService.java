package com.lzh.wuhantm.service;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

/**
 * 不建议通过bindService()启动IntentService
 * 在IntentService中,onBind() 默认返回null
 */
public class TMIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public TMIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
