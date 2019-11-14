package com.example.instagramclone_android.models.Interfaces;

import com.example.instagramclone_android.models.Post;
import com.example.instagramclone_android.models.User;
import com.google.firebase.database.DataSnapshot;

public interface FragmentExtensionInterfaces {
    void lastPostId(DataSnapshot dataSnapshot);
    void postFetched(Post post);
    void userFetch(User user);
}
