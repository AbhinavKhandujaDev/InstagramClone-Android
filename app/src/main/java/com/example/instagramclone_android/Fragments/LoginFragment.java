package com.example.instagramclone_android.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.instagramclone_android.Model.Interfaces.LoginSignUpInterface;
import com.example.instagramclone_android.R;

public class LoginFragment extends Fragment {

    LinearLayout signUp;

    private LoginSignUpInterface loginSignUpInterface;

    public LoginFragment(LoginSignUpInterface loginSignUpInterface) {
        this.loginSignUpInterface = loginSignUpInterface;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        signUp = view.findViewById(R.id.login_create_account);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginSignUpInterface.signUpTapped();
            }
        });

        return view;
    }


}
