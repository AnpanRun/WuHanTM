package com.lzh.wuhantm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.lzh.wuhantm.R;

/**
 *  复习Fragment
 *  onAttach() - onCreate() - onCreateView() - onActivityCreated() - onStart() - onResume() - onPause() - onStop() - onDestoryView() - onDestory() -onDetach()
 *
 *
 * 由于onSaveInstanceState()在onPause()之后，onStop()之前调用。onRestoreInstanceState()在onStart()之后，onResume()之前，因此避免出现该异常的方案有：
 *
 * 不要把Fragment事务放在异步线程的回调中
 * 逼不得已时使用commitAllowingStateLoss()
 */
public class TMFmActivity extends AppCompatActivity {
    String TAG = "TMFmActivity";

    //将Fragment添加到Activity中一般有两种方法:
    //1.在 Activity 的 layout.xml 布局文件中静态添加
    //2.在 Activity 的 .java文件中动态添加
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentdemo);
        replaceFragment(new RightFragment());
    }

    /**
     * 动态加载Fragment中，FragmentTransaction类提供了方法完成增删等操作，完成后调用FragmentTransaction.commit()方法提交修改
     *
     * transaction.add()：往Activity里面添加一个片段
     * transaction.remove()：从Activity中移除一个Fragment，如果被移除的Fragment没有添加到回退栈，这个Fragment实例将会被销毁
     * transaction.replace()：使用另一个Fragment替换当前的，实际上是remove()然后add()的合体
     * transaction.hide()：隐藏当前Fragment，仅不可见，不会销毁
     * transaction.show()：显示之前隐藏的Fragment
     * detach()：会将view从UI中移除,和remove()不同,此时fragment的状态依然由FragmentManager维护
     * attach()：重建view视图，附加到UI上并显示。
     * commit方法一定要在Activity.onSaveInstance()之前调用
     *
     * commit()操作是异步的，内部通过mManager.enqueueAction()加入处理队列。对应的同步方法为commitNow()，commit()内部会有checkStateLoss()操作
     * 如果开发人员使用不当（比如commit()操作在onSaveInstanceState()之后），可能会抛出异常
     * 而commitAllowingStateLoss()方法则是不会抛出异常版本的commit()方法，但是尽量使用commit()，而不要使用commitAllowingStateLoss()。
     * @param fragment
     */
    private void replaceFragment(Fragment  fragment){
        //1. 取得FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        //2. 开启一个事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //--  向Fragment传递数据
        Bundle bundle = new Bundle();
        bundle.putString("message" ,  "I LOVE LZH");
        fragment.setArguments(bundle);

        //使用另一个Fragment替换当前的，实际上是remove()然后add()的合体
        transaction.replace(R.id.fg_left, fragment);

        //待补充
        transaction.addToBackStack(null);
        /**
         * commit()操作是异步的，内部通过mManager.enqueueAction()加入处理队列。对应的同步方法为commitNow()
         * commit()内部会有checkStateLoss()操作，如果开发人员使用不当（比如commit()操作在onSaveInstanceState()之后）
         * 可能会抛出异常，而commitAllowingStateLoss()方法则是不会抛出异常版本的commit()方法
         * 但是尽量使用commit()，而不要使用commitAllowingStateLoss()。
         */
        transaction.commit();
        //通过id拿到 fragment对象
        //fragmentManager.findFragmentById()
    }

    /**
     * onSaveInstanceState()在onPause()之后，onStop()之前调用
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, TAG + " TMFmActivity : onSaveInstanceState()");
    }

    /**
     * onRestoreInstanceState()在onStart()之后，onResume()之前
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, TAG + " TMFmActivity : onRestoreInstanceState()");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, TAG + " TMFmActivity : onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, TAG + " TMFmActivity : onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, TAG + " TMFmActivity : onPause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, TAG + " TMFmActivity : onDestroy()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, TAG + " TMFmActivity : onStop()");
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.d(TAG, TAG + " TMFmActivity : onPostCreate()");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, TAG + " TMFmActivity : onNewIntent()");
    }
    
    
}
