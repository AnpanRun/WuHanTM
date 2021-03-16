package com.lzh.wuhantm.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.lzh.wuhantm.R
import java.lang.reflect.Method

class HookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hook)
        var button: Button = findViewById(R.id.bt_hook_button)
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
            }
        })

        //对于单个方法的java接口，可以简写成以接口类型作为前缀的Lambda表达式，所以我们再改下：
        button.setOnClickListener(View.OnClickListener {
            Toast.makeText(this,"测试",Toast.LENGTH_SHORT).show()
        })

    }

    fun hookClick(view: View) {

    }
}

