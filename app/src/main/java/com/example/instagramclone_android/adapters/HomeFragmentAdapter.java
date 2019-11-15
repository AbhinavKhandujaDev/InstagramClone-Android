package com.example.instagramclone_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclone_android.R;
import com.example.instagramclone_android.models.Post;
import com.example.instagramclone_android.view_holders.HomeFeedViewHolder;

import java.util.List;

public class HomeFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Post> posts;

    public HomeFragmentAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new HomeFeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomeFeedViewHolder homeFeedViewHolder = (HomeFeedViewHolder) holder;
        homeFeedViewHolder.setData(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
