package com.example.instagramclone_android.Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclone_android.R;
import com.example.instagramclone_android.view_holders.ProfileHeaderViewHolder;

public class ProfileHeaderDecoration extends RecyclerView.ItemDecoration {

    private View headerView;
    private Context context;

    public ProfileHeaderDecoration(Context context, View headerView) {
        this.headerView = headerView;
        this.context = context;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        for (int i = 0; i < parent.getChildCount(); i++) {
            View view = parent.getChildAt(i);
            if (i == 0) {
                Toast.makeText(context, "aaaaaaaaa", Toast.LENGTH_SHORT).show();
                view.setLeft(0);
                view.setTop(0);
                view.setRight(0);
                view.setBottom(0);
                c.translate(0,0);
            }
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }
}
