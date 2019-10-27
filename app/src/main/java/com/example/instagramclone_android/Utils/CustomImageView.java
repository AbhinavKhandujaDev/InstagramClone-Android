package com.example.instagramclone_android.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;

public class CustomImageView extends AppCompatImageView {

    static HashMap<String, Bitmap> imageCache = new HashMap<>();

    private String lastImgUrl;

    public CustomImageView(Context context) {
        super(context);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setImage(String url) {

        lastImgUrl = url;

        if (imageCache.get(lastImgUrl) != null) {
            Bitmap imb = imageCache.get(lastImgUrl);
            setImageBitmap(imb);
            return;
        }

        FirebaseRefs.refs.getImageStorageRef().getReferenceFromUrl(url).getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                setImageBitmap(bmp);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }
}
