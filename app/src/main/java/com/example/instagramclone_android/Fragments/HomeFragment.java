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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclone_android.Model.Post;
import com.example.instagramclone_android.R;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    private TextView toolbarTitle;

    private RecyclerView feedRecyclerView;

    private Post[] post = {new Post("Caption",1234567890,"imageUrl",3,"ownerUid", "postId", false)};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "frag onCreateView");

        View view = inflater.inflate(R.layout.fragment_home, container,false);

        toolbarTitle = view.findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Home");

        feedRecyclerView = view.findViewById(R.id.feed_recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        feedRecyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }
}
