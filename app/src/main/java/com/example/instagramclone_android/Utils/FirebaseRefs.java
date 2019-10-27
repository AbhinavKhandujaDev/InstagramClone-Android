package com.example.instagramclone_android.Utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseRefs {

    public static FirebaseRefs refs = new FirebaseRefs();

    private DatabaseReference dbref;
    private StorageReference storageRef;
    private FirebaseStorage imageStorageRef;

    private DatabaseReference usersRef;

    private DatabaseReference postsRef;
    private DatabaseReference userFeedRef;
    private DatabaseReference userPostsRef;

    private DatabaseReference userLikesRef;
    private DatabaseReference postLikesRef;

    private DatabaseReference userFollowerRef;
    private DatabaseReference userFollowingRef;

    private DatabaseReference commentsRef;

    private DatabaseReference notificationsRef;

    private DatabaseReference messagesRef;
    private DatabaseReference userMessagesRef;

    private DatabaseReference hashtagPostsRef;

    private DatabaseReference profileImageStorageRef;

    private FirebaseRefs() {
        this.dbref = FirebaseDatabase.getInstance().getReference();
        this.storageRef = FirebaseStorage.getInstance().getReference();
        this.imageStorageRef = FirebaseStorage.getInstance();

        this.usersRef = dbref.child("users");
        this.postsRef = dbref.child("posts");
        this.userFeedRef = dbref.child("user-feed");
        this.userPostsRef = dbref.child("user-posts");
        this.userLikesRef = dbref.child("user-likes");
        this.postLikesRef = dbref.child("post-likes");
        this.userFollowerRef = dbref.child("user-follower");
        this.userFollowingRef = dbref.child("user-following");
        this.commentsRef = dbref.child("comments");
        this.notificationsRef = dbref.child("notifications");
        this.messagesRef = dbref.child("messages");
        this.userMessagesRef = dbref.child("user-messages");
        this.hashtagPostsRef = dbref.child("hashtag-post");
        this.profileImageStorageRef = dbref.child("profile_images");
    }

    public static FirebaseRefs getRefs() {
        return refs;
    }

    public DatabaseReference getDbref() {
        return dbref;
    }

    public StorageReference getStorageRef() {
        return storageRef;
    }

    public FirebaseStorage getImageStorageRef() {
        return imageStorageRef;
    }

    public DatabaseReference getUsersRef() {
        return usersRef;
    }

    public DatabaseReference getPostsRef() {
        return postsRef;
    }

    public DatabaseReference getUserFeedRef() {
        return userFeedRef;
    }

    public DatabaseReference getUserPostsRef() {
        return userPostsRef;
    }

    public DatabaseReference getUserLikesRef() {
        return userLikesRef;
    }

    public DatabaseReference getPostLikesRef() {
        return postLikesRef;
    }

    public DatabaseReference getUserFollowerRef() {
        return userFollowerRef;
    }

    public DatabaseReference getUserFollowingRef() {
        return userFollowingRef;
    }

    public DatabaseReference getCommentsRef() {
        return commentsRef;
    }

    public DatabaseReference getNotificationsRef() {
        return notificationsRef;
    }

    public DatabaseReference getMessagesRef() {
        return messagesRef;
    }

    public DatabaseReference getUserMessagesRef() {
        return userMessagesRef;
    }

    public DatabaseReference getHashtagPostsRef() {
        return hashtagPostsRef;
    }

    public DatabaseReference getProfileImageStorageRef() {
        return profileImageStorageRef;
    }
}
