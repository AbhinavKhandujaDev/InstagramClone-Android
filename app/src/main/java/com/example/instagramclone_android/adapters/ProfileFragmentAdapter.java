package com.example.instagramclone_android.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclone_android.R;
import com.example.instagramclone_android.models.User;
import com.example.instagramclone_android.view_holders.PostsViewHolder;
import com.example.instagramclone_android.view_holders.ProfileHeaderViewHolder;

public class ProfileFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private User user;
    private Context context;

    public ProfileFragmentAdapter(User user, Context context) {
        this.context = context;
        this.user = user;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder;

        int layout = viewType == 0 ? R.layout.profile_header_view : R.layout.profile_item;
//        int layout = R.layout.profile_item;

        view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);

        viewHolder = viewType == 0 ? new ProfileHeaderViewHolder(view) : new PostsViewHolder(view);
//        viewHolder = new PostsViewHolder(view);

        if (viewType > 0) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(parent.getWidth()/3 - 2, parent.getWidth()/3);
            params.setMargins(0,2,0,2);
            view.setLayoutParams(params);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            ProfileHeaderViewHolder header = (ProfileHeaderViewHolder) holder;
            header.setUserDets(user,context);
            return;
        }

        PostsViewHolder postsView = (PostsViewHolder) holder;

    }

    @Override
    public int getItemCount() {
        return 50;
    }
}
