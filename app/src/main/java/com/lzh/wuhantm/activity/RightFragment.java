package com.lzh.wuhantm.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lzh.wuhantm.R;

public class RightFragment extends Fragment {
    String TAG = "RightFragment";
    String message;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_right, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, TAG + " RightFragment : onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, TAG + " RightFragment : onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, TAG + " RightFragment : onPause()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, TAG + " RightFragment : onDestroy()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, TAG + " RightFragment : onStop()");
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, TAG + " RightFragment : onAttach()");
        //Activity向Fragment 传递数据
        message = getArguments().getString("message");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, TAG + " RightFragment : onDetach()");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, TAG + " RightFragment : onActivityCreated()");
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, TAG + " RightFragment : onDestroyView()");
    }
}
