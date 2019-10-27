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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclone_android.adapters.ProfileFragmentAdapter;
import com.example.instagramclone_android.models.User;
import com.example.instagramclone_android.R;
import com.example.instagramclone_android.Utils.FirebaseRefs;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {
    private static final String TAG = "ProfileFragment";

    private TextView toolbarTitle;

    private RecyclerView profileRecyclerView;
    private ProfileFragmentAdapter adapter;

    private User user;

    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "frag onCreateView");
        mAuth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.fragment_profile, container,false);

        toolbarTitle = view.findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Profile");

        profileRecyclerView = view.findViewById(R.id.profile_recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(),3);
        profileRecyclerView.setLayoutManager(linearLayoutManager);

        fetchUserData();
        return view;
    }

    private void fetchUserData() {
        DatabaseReference myRef = FirebaseRefs.getRefs().getUsersRef();
        String currentUid = mAuth.getUid();
        if (currentUid == null) {return;}
        myRef.child(currentUid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = (String) dataSnapshot.child("name").getValue();
                String profileImageUrl = (String) dataSnapshot.child("profileImageUrl").getValue();
                String username = (String) dataSnapshot.child("username").getValue();
                String key = dataSnapshot.getKey();
                user = new User(key,name,username,profileImageUrl);

                adapter = new ProfileFragmentAdapter(user);
                profileRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
