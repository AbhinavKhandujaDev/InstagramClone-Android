package com.example.instagramclone_android.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.instagramclone_android.Model.Interfaces.LoginSignUpInterface;
import com.example.instagramclone_android.R;

public class SignUpFragment extends Fragment {

    private LoginSignUpInterface loginSignUpInterface;

    LinearLayout signIn;

    public SignUpFragment(LoginSignUpInterface loginSignUpInterface) {
        this.loginSignUpInterface = loginSignUpInterface;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        signIn = view.findViewById(R.id.frag_signup_already_have_acc);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginSignUpInterface.signInTapped();
            }
        });

        return view;
    }

}
