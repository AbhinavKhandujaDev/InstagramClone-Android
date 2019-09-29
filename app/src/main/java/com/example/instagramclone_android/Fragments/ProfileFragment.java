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

import com.example.instagramclone_android.Model.User;
import com.example.instagramclone_android.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ProfileFragment extends Fragment {
    private static final String TAG = "ProfileFragment";

    private TextView toolbarTitle;

    private RecyclerView profileRecyclerView;

    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "frag onCreateView");

        View view = inflater.inflate(R.layout.fragment_profile, container,false);

        toolbarTitle = view.findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Profile");

        profileRecyclerView = view.findViewById(R.id.profile_recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        profileRecyclerView.setLayoutManager(linearLayoutManager);

        fetchUserData();
        return view;
    }

    private void fetchUserData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child("P2p6MBEcesYnxEdxBXdxohYHwjv2");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = (String) dataSnapshot.child("name").getValue();
                String profileImageUrl = (String) dataSnapshot.child("profileImageUrl").getValue();
                String username = (String) dataSnapshot.child("username").getValue();
                user = new User(name,username,profileImageUrl,"P2p6MBEcesYnxEdxBXdxohYHwjv2");

                Log.d(TAG, "Value is: " + user.getProfileImageUrl());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
