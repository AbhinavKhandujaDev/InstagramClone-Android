package com.example.instagramclone_android.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclone_android.Utils.FirebaseRefs;
import com.example.instagramclone_android.Utils.extensions.ExtensionFragment;
import com.example.instagramclone_android.adapters.HomeFragmentAdapter;
import com.example.instagramclone_android.models.Interfaces.FragmentExtensionInterfaces;
import com.example.instagramclone_android.models.Interfaces.ViewHolderHomeFeedInterface;
import com.example.instagramclone_android.models.Post;
import com.example.instagramclone_android.R;
import com.example.instagramclone_android.models.User;
import com.example.instagramclone_android.view_holders.HomeFeedViewHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomeFragment extends ExtensionFragment implements ViewHolderHomeFeedInterface {
    private static final String TAG = "HomeFragment";

    private TextView toolbarTitle;

    private RecyclerView feedRecyclerView;
    private HomeFragmentAdapter feedAdapter;

    private List<Post> posts;

    boolean viewSinglePost = false;

    private Post post;

    private String currentKey;
    private int initialPostsCount = 5;
    private int furtherPostsCount = 6;


    public HomeFragment(@Nullable Post post) {
        this.post = post;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container,false);

        feedRecyclerView = view.findViewById(R.id.feed_recycler_view);
        toolbarTitle = view.findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Home");

        setRecyclerView();
        fetchFeed();
        return view;
    }

    private void setRecyclerView() {
        posts = new ArrayList<>();
        feedAdapter = new HomeFragmentAdapter(posts,this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        feedRecyclerView.setLayoutManager(linearLayoutManager);
        feedRecyclerView.setAdapter(feedAdapter);
    }

    private void fetchFeed() {
        if (post != null) {
            posts.add(post);
            feedAdapter.notifyDataSetChanged();
            return;
        }
        FirebaseRefs fbref = FirebaseRefs.refs;
        String uid = fbref.getUid();
        DatabaseReference dbRef = fbref.getUserFeedRef().child(uid);
        fetchPosts(dbRef, currentKey, initialPostsCount, furtherPostsCount, new PostIdInterface() {
            @Override
            public void lastPostId(DataSnapshot dataSnapshot) {
                currentKey = dataSnapshot.getKey();
            }
        }, new PostFetchInterface() {
            @Override
            public void postFetched(Post post) {
                posts.add(post);
                Collections.sort(posts, new Comparator<Post>() {
                    @Override
                    public int compare(Post o1, Post o2) {
                        if (o1.getCreatedAt() > o2.getCreatedAt()) {
                            return 1;
                        }else if (o1.getCreatedAt() < o2.getCreatedAt()) {
                            return -1;
                        }
                        return 0;
                    }
                });
                feedAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void handleUsernameTapped(HomeFeedViewHolder holder) {
        Fragment profileFragment = new ProfileFragment(holder.getPost().getUser());
        navigateToFragment(profileFragment);
    }

    @Override
    public void handleOptionsTapped(HomeFeedViewHolder holder) {

    }

    @Override
    public void handleLikeTapped(final HomeFeedViewHolder holder) {
        final Post post = holder.getPost();
        post.adjustLikes(!post.isDidLike(), new Post.PostLikes() {
            @Override
            public void likesUpdated(long likes) {
                holder.getLikesImage().setImageResource((post.isDidLike()) ? R.drawable.ic_like : R.drawable.ic_unlike);
                holder.getLikesCountTextView().setText(likes + " likes");
            }
        });
    }

    @Override
    public void handleCommentTapped(HomeFeedViewHolder holder) {

    }

    @Override
    public void handleConfigureLikeButton(final HomeFeedViewHolder holder) {
        final Post post = holder.getPost();
        String uid = FirebaseRefs.refs.getUid();
        FirebaseRefs.refs.getUserLikesRef().child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean ssHasChild = dataSnapshot.hasChild(post.getPostId());
                post.setDidLike(ssHasChild);
                holder.getLikesImage().setImageResource(ssHasChild ? R.drawable.ic_like : R.drawable.ic_unlike);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void handleShowLikes(HomeFeedViewHolder holder) {

    }

    @Override
    public void handleShowMessages(HomeFeedViewHolder holder) {

    }
}
