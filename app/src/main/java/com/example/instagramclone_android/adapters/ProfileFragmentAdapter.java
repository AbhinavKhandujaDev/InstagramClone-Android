package com.example.instagramclone_android.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclone_android.R;
import com.example.instagramclone_android.models.User;
import com.example.instagramclone_android.view_holders.ProfileHeaderViewHolder;

public class ProfileFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private User user;
//    private Post[] post;

    public ProfileFragmentAdapter(User user) {
        this.user = user;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_header_view, parent, false);
        viewHolder = new ProfileHeaderViewHolder(view);

//        if(viewType==0) {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_header_view,parent,false);
//            viewHolder = new ProfileHeaderViewHolder(view);
//        }else {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.two,parent,false);
//            viewHolder= new PostsViewHolder(view);
//
//            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
//            layoutParams.height = (parent.getHeight() / 3) - layoutParams.leftMargin - layoutParams.rightMargin;
//            view.setLayoutParams(layoutParams);
//        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProfileHeaderViewHolder header = (ProfileHeaderViewHolder) holder;
        header.setView(this.user);
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
