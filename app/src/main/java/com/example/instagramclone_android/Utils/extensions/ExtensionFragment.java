package com.example.instagramclone_android.Utils.extensions;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.instagramclone_android.R;
import com.example.instagramclone_android.Utils.FirebaseRefs;
import com.example.instagramclone_android.fragments.LoginFragment;
import com.example.instagramclone_android.fragments.SignUpFragment;
import com.example.instagramclone_android.models.Interfaces.FragmentExtensionInterfaces;
import com.example.instagramclone_android.models.Post;
import com.example.instagramclone_android.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ExtensionFragment extends Fragment {

    public interface PostIdInterface {
        void lastPostId(DataSnapshot dataSnapshot);
    }

    public interface UserFetchInterface {
        void userFetch(User user);
    }

    public interface PostFetchInterface {
        void postFetched(Post post);
    }

    FragmentManager fragmentManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getFragmentManager();
    }

    public void fetchPosts(DatabaseReference databaseReference, @Nullable final String currentKey, int initialCount, int furtherCount, final PostIdInterface postIdInterface, final PostFetchInterface postFetchInterface) {

        if (currentKey != null) {
            databaseReference.orderByKey().endAt(currentKey).limitToLast(furtherCount).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Iterable<DataSnapshot> allObjects = dataSnapshot.getChildren();
                    DataSnapshot first = allObjects.iterator().next();
                    for (DataSnapshot snapshot: allObjects) {
                        String postId = snapshot.getKey();
                        assert postId != null;
                        if (!postId.equals(currentKey)) {
                            fetchPost(postId, new PostFetchInterface() {
                                @Override
                                public void postFetched(Post post) {
                                    postFetchInterface.postFetched(post);
                                }
                            });
                        }
                    }
                    postIdInterface.lastPostId(first);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            return;
        }

        databaseReference.limitToLast(initialCount).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> allObjects = dataSnapshot.getChildren();
                DataSnapshot first = allObjects.iterator().next();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String postId = snapshot.getKey();
                    fetchPost(postId, new PostFetchInterface() {
                        @Override
                        public void postFetched(Post post) {
                            postFetchInterface.postFetched(post);
                        }
                    });
                }
                postIdInterface.lastPostId(first);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void fetchUser(final String uid, final FragmentExtensionInterfaces user) {
        FirebaseRefs.refs.getUsersRef().child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = (String) dataSnapshot.child("name").getValue();
                String profileImageUrl = (String) dataSnapshot.child("profileImageUrl").getValue();
                String username = (String) dataSnapshot.child("username").getValue();
                String key = dataSnapshot.getKey();
                User user1 = new User(key,name,username,profileImageUrl);
                user.userFetch(user1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void fetchPost(final String postId, final PostFetchInterface post) {
        FirebaseRefs.refs.getPostsRef().child(postId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Post post1 = new Post(postId,dataSnapshot);
                post.postFetched(post1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    protected void navigateToFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.activity_main_frame_layout, fragment).commit();
    }

}
