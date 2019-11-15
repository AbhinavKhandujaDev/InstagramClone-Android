package com.example.instagramclone_android.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclone_android.Utils.FirebaseRefs;
import com.example.instagramclone_android.Utils.extensions.ExtensionFragment;
import com.example.instagramclone_android.adapters.HomeFragmentAdapter;
import com.example.instagramclone_android.models.Post;
import com.example.instagramclone_android.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomeFragment extends ExtensionFragment {
    private static final String TAG = "HomeFragment";

    private TextView toolbarTitle;

    private RecyclerView feedRecyclerView;
    private HomeFragmentAdapter feedAdapter;

    private List<Post> posts;

    boolean viewSinglePost = false;

    private Post post;

    private String currentKey;
    private int initialPostsCount = 5;
    private int furtherPostsCount = 6;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container,false);

        feedRecyclerView = view.findViewById(R.id.feed_recycler_view);
        toolbarTitle = view.findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Home");

        setRecyclerView();
        fetchFeed();
        return view;
    }

    private void setRecyclerView() {
        posts = new ArrayList<>();
        feedAdapter = new HomeFragmentAdapter(posts);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        feedRecyclerView.setLayoutManager(linearLayoutManager);
        feedRecyclerView.setAdapter(feedAdapter);
    }

    private void fetchFeed() {
        if (viewSinglePost) {
            posts.add(post);
            return;
        }
        FirebaseRefs fbref = FirebaseRefs.refs;
        String uid = fbref.getUid();
        DatabaseReference dbRef = fbref.getUserFeedRef().child(uid);
        fetchPosts(dbRef, currentKey, initialPostsCount, furtherPostsCount, new PostIdInterface() {
            @Override
            public void lastPostId(DataSnapshot dataSnapshot) {
                currentKey = dataSnapshot.getKey();
            }
        }, new PostFetchInterface() {
            @Override
            public void postFetched(Post post) {
                posts.add(post);
                Collections.sort(posts, new Comparator<Post>() {
                    @Override
                    public int compare(Post o1, Post o2) {
                        if (o1.getCreatedAt() > o2.getCreatedAt()) {
                            return 1;
                        }else if (o1.getCreatedAt() < o2.getCreatedAt()) {
                            return -1;
                        }
                        return 0;
                    }
                });
                feedAdapter.notifyDataSetChanged();
            }
        });
    }

}
