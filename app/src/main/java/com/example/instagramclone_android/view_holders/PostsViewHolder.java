package com.example.instagramclone_android.view_holders;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclone_android.R;
import com.example.instagramclone_android.Utils.CustomImageView;

public class PostsViewHolder extends RecyclerView.ViewHolder {

    CustomImageView postImageView;

    public PostsViewHolder(@NonNull View itemView) {
        super(itemView);
        this.postImageView = itemView.findViewById(R.id.profile_user_posts_imageView);
    }

}
