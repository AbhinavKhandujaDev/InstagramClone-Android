package com.example.instagramclone_android.Adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclone_android.Model.Post;
import com.example.instagramclone_android.R;

public class HomeFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Post[] post;

    public HomeFragmentAdapter(Post[] post) {
        this.post = post;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class HomeFeedViewHolder extends RecyclerView.ViewHolder {

        private ImageView profileImageView;
        private TextView  username;
        private ImageView optionsImage;
        private ImageView postImage;
        private ImageView likesImage;
        private ImageView commentImage;
        private ImageView chatImage;
        private TextView  likesCountTextView;
        private TextView  captionTextView;

        public HomeFeedViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImageView = itemView.findViewById(R.id.post_item_profile_image);
            username = itemView.findViewById(R.id.post_item_username);
            optionsImage = itemView.findViewById(R.id.post_item_options);
            postImage = itemView.findViewById(R.id.post_item_post_image);
            likesImage = itemView.findViewById(R.id.post_item_likes);
            commentImage = itemView.findViewById(R.id.post_item_comment);
            chatImage = itemView.findViewById(R.id.post_item_chat);
            likesCountTextView = itemView.findViewById(R.id.post_item_likes_count);
            captionTextView = itemView.findViewById(R.id.post_item_caption);
        }

//        public void setData(Post post) {
//            this.username.setText(post.get);
//        }
    }
}
