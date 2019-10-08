package com.example.instagramclone_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.instagramclone_android.Fragments.HomeFragment;
import com.example.instagramclone_android.Fragments.LoginFragment;
import com.example.instagramclone_android.Fragments.SignUpFragment;
import com.example.instagramclone_android.Model.Interfaces.LoginSignUpInterface;

public class LoginSignUpActivity extends AppCompatActivity implements LoginSignUpInterface {

    Fragment loginFragment;
    Fragment signUpFragment;

    FragmentManager fragmentManager;

    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up);

        frameLayout = findViewById(R.id.activity_login_signup_frame_layout);
        setRootFragment();
    }

    private void setRootFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        loginFragment = new LoginFragment(this);
        fragmentManager.beginTransaction().add(R.id.activity_login_signup_frame_layout,loginFragment, "0").commit();
    }

    @Override
    public void signInTapped() {
        onBackPressed();
    }

    @Override
    public void signUpTapped() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_right);
        signUpFragment = new SignUpFragment(this);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.activity_login_signup_frame_layout,signUpFragment).commit();
    }
}
