package com.example.instagramclone_android.Utils;

import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMethods {
    public static boolean isValidEmail(String string) {
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher emailMatcher = emailPattern.matcher(string);
        return emailMatcher.find();
    }

    public static boolean isValidPassword(String string) {
        Pattern passwordPattern = Pattern.compile("\\w{6,}", Pattern.CASE_INSENSITIVE);
        Matcher passswordMatcher = passwordPattern.matcher(string);
        return passswordMatcher.find();
    }
}
