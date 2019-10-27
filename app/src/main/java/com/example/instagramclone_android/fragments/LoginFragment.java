package com.example.instagramclone_android.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.instagramclone_android.models.Interfaces.LoginSignUpInterface;
import com.example.instagramclone_android.R;
import com.example.instagramclone_android.Utils.StringMethods;

public class LoginFragment extends Fragment {

    LinearLayout signUp;

    EditText emailField;
    EditText passwordField;
    Button loginButton;

    private LoginSignUpInterface loginSignUpInterface;

    public LoginFragment(LoginSignUpInterface loginSignUpInterface) {
        this.loginSignUpInterface = loginSignUpInterface;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        signUp = view.findViewById(R.id.login_create_account);
        emailField = view.findViewById(R.id.login_email);
        passwordField = view.findViewById(R.id.login_password);
        loginButton = view.findViewById(R.id.login_button);
        loginButton.setEnabled(false);

        setButtonTapListeners();

        addTextChangeListeners();

        return view;
    }

    private void setButtonTapListeners() {
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginSignUpInterface.createAccTapped();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginSignUpInterface.logInTapped(emailField.getText().toString(),passwordField.getText().toString());
            }
        });
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
    }

    private void checkField() {
        if (StringMethods.isValidEmail(emailField.getText().toString()) && StringMethods.isValidPassword(passwordField.getText().toString())) {
            loginButton.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.active_login_signup_btn));
            loginButton.setEnabled(true);
        }else {
            loginButton.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.inactive_login_signup_btn));
            loginButton.setEnabled(false);
        }
    }
}
