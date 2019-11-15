package com.example.instagramclone_android.view_holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclone_android.R;
import com.example.instagramclone_android.Utils.CustomImageView;
import com.example.instagramclone_android.Utils.FirebaseRefs;
import com.example.instagramclone_android.models.Post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class HomeFeedViewHolder extends RecyclerView.ViewHolder {

    private CustomImageView profileImageView;
    private TextView username;
    private ImageView optionsImage;
    private CustomImageView postImage;
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

    public void setData(Post post) {
        postImage.setImage(post.getImageUrl());
        likesCountTextView.setText(post.getLikes() + " likes");
        captionTextView.setText(post.getCaption());

        fetchUserInfo(post);
    }

    private void fetchUserInfo(Post post) {
        FirebaseRefs ref = FirebaseRefs.refs;
        String currentUid = ref.getUid();
        String ownerUid = post.getOwnerUid();
        optionsImage.setVisibility(ownerUid.equals(currentUid) ? View.VISIBLE : View.INVISIBLE);
        ref.getUsersRef().child(ownerUid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = (String) dataSnapshot.child("username").getValue();
                String imgUrl = (String) dataSnapshot.child("profileImageUrl").getValue();

                username.setText(name);
                profileImageView.setImage(imgUrl);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
