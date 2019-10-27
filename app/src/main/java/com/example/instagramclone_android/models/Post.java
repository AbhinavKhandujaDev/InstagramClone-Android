package com.example.instagramclone_android.models;

public class Post {
    String caption;
    long createdAt;
    String imageUrl;
    int likes;
    String ownerUid;
    String postId;
    boolean didLike = false;

    public Post(String caption, long createdAt, String imageUrl, int likes, String ownerUid, String postId, boolean didLike) {
        this.caption = caption;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
        this.likes = likes;
        this.ownerUid = ownerUid;
        this.postId = postId;
        this.didLike = didLike;
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

    public int getLikes() {
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
