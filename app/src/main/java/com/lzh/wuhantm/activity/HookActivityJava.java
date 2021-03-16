package com.lzh.wuhantm.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lzh.wuhantm.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import androidx.annotation.Nullable;

/**
 * Java Hook
 * Created by LiuBo on 2021/3/16.
 */
public class HookActivityJava extends Activity {
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook);
        button = findViewById(R.id.bt_hook_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Log.i("LZH","onClick");
            }
        });
    }

    private void hookOnClickListener(View view){
        try {
            Method getListenerInfo = View.class.getDeclaredMethod("getListenerInfo");
            getListenerInfo.setAccessible(true);
            Object listenerInfo = getListenerInfo.invoke(view);
            Class listenerInfoClz = Class.forName("android.view.View$ListenerInfo");
            Field mOnClickListener = listenerInfoClz.getDeclaredField("mOnClickListener");
            mOnClickListener.setAccessible(true);
            View.OnClickListener originOnClickListener = (View.OnClickListener) mOnClickListener.get(listenerInfo);
            // 用自定义的 OnClickListener 替换原始的 OnClickListener
            View.OnClickListener hookedOnClickListener = new HookedOnClickListener(originOnClickListener);
            mOnClickListener.set(listenerInfo, hookedOnClickListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class HookedOnClickListener implements View.OnClickListener {
        private View.OnClickListener origin;

        public HookedOnClickListener(View.OnClickListener originOnClickListener) {
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(HookActivityJava.this, "hook click", Toast.LENGTH_SHORT).show();
            Log.i("LZH","Before click, do what you want to to.");
            if (origin != null) {
                origin.onClick(v);
            }
            Log.i("LZH","After click, do what you want to to.");
        }
    }
}
