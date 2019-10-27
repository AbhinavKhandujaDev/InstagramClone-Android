package com.example.instagramclone_android.Utils;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.instagramclone_android.models.Interfaces.FragmentExtensionInterfaces;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class FragmentExtension extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void fetchFeeds(DatabaseReference databaseReference, @Nullable String currentKey, int initialCount, int furtherCount, String lastPostId, final FragmentExtensionInterfaces interfaces) {
        databaseReference.limitToLast(initialCount).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean first = false;
                DataSnapshot fsnapshot = null;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (!first) {
                        fsnapshot = snapshot;
                        first = true;
                    }
                    String postId = snapshot.getKey();
                }

                interfaces.lastPostId(fsnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
