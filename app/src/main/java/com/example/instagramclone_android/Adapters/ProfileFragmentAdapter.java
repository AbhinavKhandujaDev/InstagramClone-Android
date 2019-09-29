package com.example.instagramclone_android.Adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclone_android.Model.User;

public class ProfileFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private User user;

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

    class ProfileviewHolder extends RecyclerView.ViewHolder {

        public ProfileviewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
