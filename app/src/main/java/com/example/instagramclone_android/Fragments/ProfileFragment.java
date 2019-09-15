package com.example.instagramclone_android.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.instagramclone_android.R;

public class ProfileFragment extends Fragment {
    private static final String TAG = "ProfileFragment";

    private TextView toolbarTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "frag onCreateView");

        View view = inflater.inflate(R.layout.fragment_profile, container,false);

        toolbarTitle = view.findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Profile");

        return view;
    }
}
