package com.example.instagramclone_android.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.instagramclone_android.Utils.FirebaseRefs;
import com.example.instagramclone_android.Utils.extensions.GlobalValues;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Post {

    public interface PostLikes {
        void likesUpdated(long likes);
    }

    private String caption;
    private long createdAt;
    private String imageUrl;
    private long likes;
    private String ownerUid;
    private String postId;
    private boolean didLike = false;

    private User user;

    public Post(String postId, DataSnapshot object) {
        this.caption = (String) object.child("caption").getValue();
        this.createdAt = (long) object.child("createdAt").getValue();
        this.imageUrl = (String) object.child("imageUrl").getValue();
        this.likes = (long) object.child("likes").getValue();
        this.ownerUid = (String) object.child("uid").getValue();
        this.postId = postId;

        fetchUser(ownerUid);
    }

    private void fetchUser(final String uid) {
        FirebaseRefs.refs.getUsersRef().child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = (String) dataSnapshot.child("name").getValue();
                String profileImageUrl = (String) dataSnapshot.child("profileImageUrl").getValue();
                String username = (String) dataSnapshot.child("username").getValue();
                String key = dataSnapshot.getKey();
                User user1 = new User(key,name,username,profileImageUrl);
                user = user1;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public User getUser() {
        return user;
    }

    public String getCaption() {
        return caption;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public long getLikes() {
        return likes;
    }

    public String getOwnerUid() {
        return ownerUid;
    }

    public String getPostId() {
        return postId;
    }

    public boolean isDidLike() {
        return didLike;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }
    public void setOwnerUid(String ownerUid) {
        this.ownerUid = ownerUid;
    }
    public void setPostId(String postId) {
        this.postId = postId;
    }
    public void setDidLike(boolean didLike) {
        this.didLike = didLike;
    }

    public void adjustLikes(boolean addLike, final PostLikes postLikes) {
        final String currUser = FirebaseRefs.refs.getUid();
        if (addLike) {
            Map<String, Object> value = new HashMap<String, Object>() {{
                put(postId, 1);
            }};
            //update like from user-likes structure
            FirebaseRefs.refs.getUserLikesRef().child(currUser).updateChildren(value, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                    sendLikeNotifToServer(currUser);

                    Map<String, Object> value2 = new HashMap<String, Object>() {{
                        put(currUser, 1);
                    }};
                    //update likes count
                    FirebaseRefs.refs.getPostLikesRef().child(postId).updateChildren(value2, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                            likes += 1;
                            didLike = true;
                            Map<String, Object> value3 = new HashMap<String, Object>() {{
                                put("likes", likes);
                            }};
                            postLikes.likesUpdated(likes);
                            FirebaseRefs.refs.getPostsRef().updateChildren(value3);
                        }
                    });
                }
            });
            return;
        }

        FirebaseRefs.refs.getUserLikesRef().child(postId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String notificationId = dataSnapshot.getKey();
                FirebaseRefs.refs.getNotificationsRef().child(ownerUid).child(notificationId).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                        //remove like from user-like structure
                        FirebaseRefs.refs.getUserLikesRef().child(currUser).child(postId).removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                                //remove like from post-like structure
                                FirebaseRefs.refs.getPostLikesRef().child(postId).child(currUser).removeValue(new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                        if (likes <= 0) {return;}
                                        likes -= 1;
                                        didLike = false;
                                        Map<String, Object> value3 = new HashMap<String, Object>() {{
                                            put("likes", likes);
                                        }};
                                        postLikes.likesUpdated(likes);
                                        FirebaseRefs.refs.getPostsRef().updateChildren(value3);
                                    }
                                });
                            }
                        });
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void sendLikeNotifToServer(final String currUser) {
        final Date date = new Date();
        DatabaseReference notifRef = FirebaseRefs.refs.getNotificationsRef().child(ownerUid).push();
        if (currUser.equals(FirebaseRefs.refs.getUid())) {return;}
        FirebaseRefs.refs.getUserLikesRef().child(currUser).child(postId).setValue(notifRef.getKey());
        Map<String, Object> value3 = new HashMap<String, Object>() {{
            put("checked", 0);
            put("creationDate", date.getTime());
            put("uid", currUser);
            put("type", GlobalValues.vals.getLikeIntValue());
            put("postId", postId);
        }};
    }
}
