package com.example.instagramclone_android.view_holders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclone_android.R;
import com.example.instagramclone_android.Utils.CustomImageView;
import com.example.instagramclone_android.models.Post;

public class PostsViewHolder extends RecyclerView.ViewHolder {

    CustomImageView postImageView;

    public PostsViewHolder(@NonNull View itemView) {
        super(itemView);
        this.postImageView = itemView.findViewById(R.id.profile_item_image);
    }

    public void setPostImageView(Post post) {
        postImageView.setImage(post.getImageUrl());
    }

}
