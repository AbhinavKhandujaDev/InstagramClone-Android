package com.example.instagramclone_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.instagramclone_android.fragments.LoginFragment;
import com.example.instagramclone_android.fragments.SignUpFragment;
import com.example.instagramclone_android.models.Interfaces.LoginSignUpInterface;
import com.example.instagramclone_android.Utils.SharedPrefs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginSignUpActivity extends AppCompatActivity implements LoginSignUpInterface {

    Fragment loginFragment;
    Fragment signUpFragment;

    FragmentManager fragmentManager;

    FrameLayout frameLayout;

    private FirebaseAuth mAuth;

    private static final String TAG = "LoginSignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        boolean isLogin = SharedPrefs.sharedPrefs.getLoginStatus(LoginSignUpActivity.this);
        if (isLogin && mAuth.getCurrentUser() != null) {
            intentToHomeActivity();
            return;
        }

        setContentView(R.layout.activity_login_sign_up);
        frameLayout = findViewById(R.id.activity_login_signup_frame_layout);
        setRootFragment();
    }

    private void intentToHomeActivity() {
        Intent intent = new Intent(LoginSignUpActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void setRootFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        loginFragment = new LoginFragment(this);
        fragmentManager.beginTransaction().add(R.id.activity_login_signup_frame_layout, loginFragment, "0").commit();
    }

    @Override
    public void alreadyHaveAccTapped() {
        onBackPressed();
    }

    @Override
    public void createAccTapped() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        signUpFragment = new SignUpFragment(this);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.activity_login_signup_frame_layout, signUpFragment).commit();
    }

    @Override
    public void signUpTapped(String email, String password,String fullName, String username) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(LoginSignUpActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginSignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void logInTapped(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(LoginSignUpActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            SharedPrefs.sharedPrefs.setLoginStatus(true, LoginSignUpActivity.this);
                            intentToHomeActivity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginSignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
