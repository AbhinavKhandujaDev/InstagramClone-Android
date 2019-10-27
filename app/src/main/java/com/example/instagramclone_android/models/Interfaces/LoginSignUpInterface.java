package com.example.instagramclone_android.models.Interfaces;

public interface LoginSignUpInterface {
    void alreadyHaveAccTapped();
    void createAccTapped();
    void logInTapped(String email, String password);
    void signUpTapped(String email, String password,String fullName, String username);
}
