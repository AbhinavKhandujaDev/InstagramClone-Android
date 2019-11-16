package com.example.instagramclone_android.models.Interfaces;

import com.example.instagramclone_android.view_holders.HomeFeedViewHolder;

public interface ViewHolderHomeFeedInterface {
    void handleUsernameTapped(HomeFeedViewHolder holder);
    void handleOptionsTapped(HomeFeedViewHolder holder);
    void handleLikeTapped(HomeFeedViewHolder holder);
    void handleCommentTapped(HomeFeedViewHolder holder);
    void handleConfigureLikeButton(HomeFeedViewHolder holder);
    void handleShowLikes(HomeFeedViewHolder holder);
    void handleShowMessages(HomeFeedViewHolder holder);
}
