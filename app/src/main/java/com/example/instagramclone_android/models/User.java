package com.example.instagramclone_android.models;

import androidx.annotation.NonNull;

import com.example.instagramclone_android.Utils.FirebaseRefs;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class User {

    public interface UserFollowInterface {
        void followStatus(boolean isFollowed);
    }

    private String name;
    private String username;
    private String profileImageUrl;
    private String uid;
    private boolean isFollowed = false;

    public User(String uid, String name, String username, String profileImageUrl) {
        this.name = name;
        this.username = username;
        this.profileImageUrl = profileImageUrl;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getUid() {
        return uid;
    }

    public boolean isFollowed() {
        return isFollowed;
    }

    public void checkIfFollowed(final UserFollowInterface follow) {
        FirebaseRefs ref = FirebaseRefs.getRefs();
        String currUser = ref.getUid();
        ref.getUserFollowingRef().child(currUser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                isFollowed = dataSnapshot.hasChild(uid);
                follow.followStatus(isFollowed);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
