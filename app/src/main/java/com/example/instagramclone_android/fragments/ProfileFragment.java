package com.example.instagramclone_android.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclone_android.Utils.extensions.ExtensionFragment;
import com.example.instagramclone_android.adapters.ProfileFragmentAdapter;
import com.example.instagramclone_android.models.Interfaces.FragmentExtensionInterfaces;
import com.example.instagramclone_android.models.Post;
import com.example.instagramclone_android.models.User;
import com.example.instagramclone_android.R;
import com.example.instagramclone_android.Utils.FirebaseRefs;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProfileFragment extends ExtensionFragment {
    private static final String TAG = "ProfileFragment";

    private TextView toolbarTitle;

    private RecyclerView profileRecyclerView;
    private ProfileFragmentAdapter adapter;
    private FirebaseAuth mAuth;

    private String currentKey;
    private int initialCount = 10;
    private int furtherPostsCount = 6;

    private List<Post> posts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.fragment_profile, container,false);

        toolbarTitle = view.findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Profile");

        profileRecyclerView = view.findViewById(R.id.profile_recycler_view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(),3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position == 0) ? 3 : 1;
            }
        });
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        profileRecyclerView.setLayoutManager(gridLayoutManager);

        fetchUserData();
        getPosts(null);
        return view;
    }

    private void fetchUserData() {
        String currentUid = mAuth.getUid();
        if (currentUid == null) {return;}

        fetchUser(currentUid, new FragmentExtensionInterfaces() {
            @Override
            public void lastPostId(DataSnapshot dataSnapshot) {}

            @Override
            public void postFetched(Post post) {}

            @Override
            public void userFetch(User user) {
                adapter = new ProfileFragmentAdapter(user, getContext(), posts);
                profileRecyclerView.setAdapter(adapter);
            }
        });
    }

    private void getPosts(@Nullable String uid) {
        String currUid = mAuth.getUid();
        String id = (uid != null) ? uid : currUid;
        DatabaseReference ref = FirebaseRefs.refs.getUserPostsRef().child(id);

        posts = new ArrayList<>();

        fetchPosts(ref, currentKey, initialCount, furtherPostsCount, new PostIdInterface() {
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
                adapter.notifyDataSetChanged();
            }
        });
    }
}
