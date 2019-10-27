package com.example.instagramclone_android.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.instagramclone_android.models.Interfaces.LoginSignUpInterface;
import com.example.instagramclone_android.R;
import com.example.instagramclone_android.Utils.StringMethods;

import static android.app.Activity.RESULT_OK;

public class SignUpFragment extends Fragment {

    public static final int PICK_IMAGE = 100;
    private LoginSignUpInterface loginSignUpInterface;

    LinearLayout signIn;

    ImageView profilePic;

    Uri imageUri;

    EditText emailField;
    EditText passwordField;
    EditText fullNameField;
    EditText userNameField;
    Button signUpBtn;

    public SignUpFragment(LoginSignUpInterface loginSignUpInterface) {
        this.loginSignUpInterface = loginSignUpInterface;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        signIn = view.findViewById(R.id.frag_signup_already_have_acc);

        profilePic = view.findViewById(R.id.signup_add_profile_pic);
        emailField = view.findViewById(R.id.signup_email);
        passwordField = view.findViewById(R.id.signup_password);
        fullNameField = view.findViewById(R.id.signup_full_name);
        userNameField = view.findViewById(R.id.signup_username);
        signUpBtn = view.findViewById(R.id.signup_button);
        signUpBtn.setEnabled(false);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginSignUpInterface.alreadyHaveAccTapped();
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();
                String fullName = fullNameField.getText().toString();
                String username = userNameField.getText().toString();
                loginSignUpInterface.signUpTapped(email,password,fullName,username);
            }
        });

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        addTextChangeListeners();

        return view;
    }

    private void addTextChangeListeners() {
        emailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkField();
            }
        });

        passwordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkField();
            }
        });

        fullNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkField();
            }
        });

        userNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkField();
            }
        });
    }

    private void checkField() {

        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        String fullName = fullNameField.getText().toString();
        String username = userNameField.getText().toString();

        if (StringMethods.isValidEmail(email) && StringMethods.isValidPassword(password) && (!fullName.equals("")) && (!username.equals("")) && profilePic.getDrawable() != null) {
            signUpBtn.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.active_login_signup_btn));
            signUpBtn.setEnabled(true);
        }else {
            signUpBtn.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.inactive_login_signup_btn));
            signUpBtn.setEnabled(false);
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            profilePic.setImageURI(imageUri);
        }
    }
}
