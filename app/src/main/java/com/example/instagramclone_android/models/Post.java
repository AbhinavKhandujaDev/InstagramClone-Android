package com.example.instagramclone_android.models;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;

public class Post {
    String caption;
    long createdAt;
    String imageUrl;
    long likes;
    String ownerUid;
    String postId;
    boolean didLike = false;

    public Post(String postId, DataSnapshot object) {
        this.caption = (String) object.child("caption").getValue();
        this.createdAt = (long) object.child("createdAt").getValue();
        this.imageUrl = (String) object.child("imageUrl").getValue();
        this.likes = (long) object.child("likes").getValue();
        this.ownerUid = (String) object.child("uid").getValue();
        this.postId = postId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getOwnerUid() {
        return ownerUid;
    }

    public void setOwnerUid(String ownerUid) {
        this.ownerUid = ownerUid;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public boolean isDidLike() {
        return didLike;
    }

    public void setDidLike(boolean didLike) {
        this.didLike = didLike;
    }
}
