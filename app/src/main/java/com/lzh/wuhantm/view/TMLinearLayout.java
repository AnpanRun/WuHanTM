package com.lzh.wuhantm.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * Created by LiuBo on 2021/3/15.
 */
public class TMLinearLayout extends LinearLayout {
    public TMLinearLayout(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("TM","LL onInterceptTouchEvent " + ev.getAction());
        if(ev.getAction() == MotionEvent.ACTION_MOVE){
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("TM","LL onTouchEvent " + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i("TM","LL dispatchTouchEvent " + event.getAction());

        return super.dispatchTouchEvent(event);
    }
    public TMLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TMLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TMLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
