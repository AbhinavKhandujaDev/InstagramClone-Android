package com.example.instagramclone_android.Utils.extensions;

public class GlobalValues {
    public static GlobalValues vals = new GlobalValues();

    private int likeIntValue = 0;
    private int commentIntValue = 1;
    private int followIntValue = 2;
    private int commentMentionIntValue = 3;
    private int postMentionIntValue = 4;

    public int getLikeIntValue() {
        return likeIntValue;
    }

    public int getCommentIntValue() {
        return commentIntValue;
    }

    public int getFollowIntValue() {
        return followIntValue;
    }

    public int getCommentMentionIntValue() {
        return commentMentionIntValue;
    }

    public int getPostMentionIntValue() {
        return postMentionIntValue;
    }
}
