package com.example.instagramclone_android.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.instagramclone_android.R;

public class NotificationsFragment extends Fragment {
    private static final String TAG = "NotificationsFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "frag onCreateView");
        return inflater.inflate(R.layout.fragment_notifications, container,false);
    }
}
