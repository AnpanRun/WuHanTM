package com.lzh.common.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * 权限类
 */
public class ZHPermission {
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void check(Activity context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.RECORD_AUDIO)) {
                Toast.makeText(context, "您之前拒绝过", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "您没有权限", Toast.LENGTH_SHORT).show();
                context.requestPermissions(new String[]{ Manifest.permission.RECORD_AUDIO}, 10001);
            }
        } else {
            Toast.makeText(context, "您已经拥有了权限", Toast.LENGTH_SHORT).show();
        }
    }
}
