package com.example.instagramclone_android.fragments;

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

import com.example.instagramclone_android.adapters.HomeFragmentAdapter;
import com.example.instagramclone_android.models.Post;
import com.example.instagramclone_android.R;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    private TextView toolbarTitle;

    private RecyclerView feedRecyclerView;
    private HomeFragmentAdapter feedAdapter;

    private Post[] posts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "frag onCreateView");

        View view = inflater.inflate(R.layout.fragment_home, container,false);

        feedRecyclerView = view.findViewById(R.id.feed_recycler_view);
        toolbarTitle = view.findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Home");

        setRecyclerView();

        return view;
    }

    private void setRecyclerView() {
        feedAdapter = new HomeFragmentAdapter(posts);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        feedRecyclerView.setLayoutManager(linearLayoutManager);
        feedRecyclerView.setAdapter(feedAdapter);
    }

    private void fetchFeed() {

    }

}
