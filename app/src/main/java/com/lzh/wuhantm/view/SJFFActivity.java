package com.lzh.wuhantm.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.lzh.wuhantm.R;

import androidx.annotation.Nullable;

/**
 * Created by LiuBo on 2021/3/15.
 */
public class SJFFActivity extends Activity {
    TMButton button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sjff);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("TM","SJFFActivity dispatchTouchEvent " + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }
}
