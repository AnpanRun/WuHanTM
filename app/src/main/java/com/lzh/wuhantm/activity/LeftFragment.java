package com.lzh.wuhantm.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lzh.wuhantm.R;

public class LeftFragment extends Fragment {
    String TAG = "LeftFragment";
    private String mParam;
    private Activity activity;

    public LeftFragment newInstance(String str) {
        //如果在创建Fragment时要传入参数，必须要通过setArguments(Bundle bundle)方式添加
        //而不建议通过为Fragment添加带参数的构造函数，因为通过setArguments()方式添加
        //在由于内存紧张导致Fragment被系统杀掉并恢复（re-instantiate）时能保留这些数据
        LeftFragment leftFragment = new LeftFragment();
        Bundle bundle = new Bundle();
        bundle.putString("date", str);
        leftFragment.setArguments(bundle);
        return leftFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, TAG + " LeftFragment : onCreateView()");
        return inflater.inflate(R.layout.fragment_left, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, TAG + " LeftFragment : onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, TAG + " LeftFragment : onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, TAG + " LeftFragment : onPause()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, TAG + " LeftFragment : onDestroy()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, TAG + " LeftFragment : onStop()");
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, TAG + " LeftFragment : onAttach()");
        //如果要获取Activity对象，不建议调用getActivity()，而是在onAttach()中将Context对象强转为Activity对象
        activity = (Activity) context;
        //可以在Fragment的onAttach()中通过getArguments()获得传进来的参数。
        mParam = getArguments().getString("date");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, TAG + " LeftFragment : onDetach()");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, TAG + " LeftFragment : onActivityCreated()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, TAG + " LeftFragment : onDestroyView()");
    }
}
