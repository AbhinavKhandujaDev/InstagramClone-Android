package com.example.instagramclone_android.Model;

public class User {
    private String name;
    private String username;
    private String profileImageUrl;
    private String uid;
    private boolean isFollowed = false;

    public User(String name, String username, String profileImageUrl, String uid) {
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
}
