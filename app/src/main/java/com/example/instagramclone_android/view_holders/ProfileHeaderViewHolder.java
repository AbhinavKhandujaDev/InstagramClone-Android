package com.example.instagramclone_android.view_holders;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclone_android.R;
import com.example.instagramclone_android.Utils.CustomImageView;
import com.example.instagramclone_android.Utils.FirebaseRefs;
import com.example.instagramclone_android.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class ProfileHeaderViewHolder extends RecyclerView.ViewHolder {

    private CustomImageView profileImageView;

    private TextView followersTextView;
    private TextView followingTextView;
    private TextView postsTextView;
    private TextView usernameTextView;

    private Button editPtofileButton;

    public ProfileHeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        profileImageView = itemView.findViewById(R.id.profile_header_view_image);

        followersTextView = itemView.findViewById(R.id.profile_header_followers);
        followingTextView = itemView.findViewById(R.id.profile_header_following);
        postsTextView = itemView.findViewById(R.id.profile_header_posts);

        usernameTextView = itemView.findViewById(R.id.profile_header_username);

        editPtofileButton = itemView.findViewById(R.id.profile_header_editProfile_button);
    }

    public void setUserDets(User user, Context context) {
        this.usernameTextView.setText(user.getUsername());
        profileImageView.setImage(user.getProfileImageUrl());

        String sourceString = "<b>" + 0 + "</b> " + "<br>Posts";
        postsTextView.setText(Html.fromHtml(sourceString));
        setFollowersLabel(user);
        setFollowingsLabel(user);
        configureEditProfBtn(user,context);
    }

    private void setFollowersLabel(User user) {
        String sourceString = "<b>" + 0 +"</b>" + "<br>Followers";
        followersTextView.setText(Html.fromHtml(sourceString));

        FirebaseRefs.refs.getUserFollowerRef().child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() != 0) {
                    String sourceString = "<b>" + dataSnapshot.getChildrenCount() +"</b>" + "<br>Followers";
                    followersTextView.setText(Html.fromHtml(sourceString));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setFollowingsLabel(User user) {

        String sourceString = "<b>" + 0 +"</b>" + "<br>Following";
        followingTextView.setText(Html.fromHtml(sourceString));

        FirebaseRefs.refs.getUserFollowingRef().child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() != 0) {
                    String sourceString = "<b>" + dataSnapshot.getChildrenCount() +"</b>" + "<br>Following";
                    followingTextView.setText(Html.fromHtml(sourceString));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void configureEditProfBtn(User user, Context context) {
        if (!FirebaseRefs.refs.getmAuth().getUid().equals(user.getUid())) {
            editPtofileButton.setBackground(ContextCompat.getDrawable(context,R.drawable.active_login_signup_btn));

        }
    }
}
