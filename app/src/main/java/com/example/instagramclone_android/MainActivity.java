package com.example.instagramclone_android;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.instagramclone_android.Fragments.HomeFragment;
import com.example.instagramclone_android.Fragments.NotificationsFragment;
import com.example.instagramclone_android.Fragments.ProfileFragment;
import com.example.instagramclone_android.Fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView toolbarTitle;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbarTitle);

        bottomNavigationView = findViewById(R.id.activity_main_bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        toolbarTitle.setText("Home");
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, new HomeFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFrag = null;

            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    toolbarTitle.setText("Home");
                    selectedFrag = new HomeFragment();
                    break;
                case R.id.nav_search:
                    toolbarTitle.setText("Search");
                    selectedFrag = new SearchFragment();
                    break;
                case R.id.nav_notifications:
                    toolbarTitle.setText("Notifications");
                    selectedFrag = new NotificationsFragment();
                    break;
                case R.id.nav_profile:
                    toolbarTitle.setText("Profile");
                    selectedFrag = new ProfileFragment();
                    break;
                default:
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, selectedFrag).commit();
            return true;
        }
    };
}
