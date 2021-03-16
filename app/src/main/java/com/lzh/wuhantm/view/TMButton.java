package com.lzh.wuhantm.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import android.widget.TextView;

/**
 * Created by LiuBo on 2021/3/15.
 */
public class TMButton extends TextView {
    public TMButton(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("TM","TMButton onTouchEvent " + event.getAction());
        return true;
    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i("TM","TMButton dispatchTouchEvent " + event.getAction());

        return super.dispatchTouchEvent(event);
    }

    public TMButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TMButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TMButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
