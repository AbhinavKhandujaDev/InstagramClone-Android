package com.example.instagramclone_android;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.instagramclone_android.Fragments.HomeFragment;
import com.example.instagramclone_android.Fragments.NotificationsFragment;
import com.example.instagramclone_android.Fragments.ProfileFragment;
import com.example.instagramclone_android.Fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView toolbarTitle;

    BottomNavigationView bottomNavigationView;

    private Fragment homeFrag;
    private Fragment searchFrag;
    private Fragment notifFrag;
    private Fragment profileFrag;

    private Fragment currentFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbarTitle);

        bottomNavigationView = findViewById(R.id.activity_main_bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        setRootFragment();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            if (menuItem.getItemId() == bottomNavigationView.getSelectedItemId()) { return false; }

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.hide(currentFrag);

            //At first only one fragment is loaded to reduce the load of networking calls
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    toolbarTitle.setText("Home");
                    currentFrag = homeFrag;
                    transaction.show(homeFrag).commit();
                    break;
                case R.id.nav_search:
                    toolbarTitle.setText("Search");

                    //if fragment is null or not loaded then added to the transaction stack otherwise the previous one hides and the tapped on is shown
                    if (searchFrag == null) {
                        searchFrag = new SearchFragment();
                        currentFrag = searchFrag;
                        transaction.add(R.id.activity_main_frame_layout, searchFrag, "1").commit();
                        return true;
                    }
                    currentFrag = searchFrag;
                    transaction.show(searchFrag).commit();
                    break;
                case R.id.nav_notifications:
                    toolbarTitle.setText("Notifications");
                    if (notifFrag == null) {
                        notifFrag = new NotificationsFragment();
                        currentFrag = notifFrag;
                        transaction.add(R.id.activity_main_frame_layout, notifFrag, "2").commit();
                        return true;
                    }
                    currentFrag = notifFrag;
                    transaction.show(notifFrag).commit();
                    break;
                case R.id.nav_profile:
                    toolbarTitle.setText("Profile");
                    if (profileFrag == null) {
                        profileFrag= new ProfileFragment();
                        currentFrag = profileFrag;
                        transaction.add(R.id.activity_main_frame_layout, profileFrag, "3").commit();
                        return true;
                    }
                    currentFrag = profileFrag;
                    transaction.show(profileFrag).commit();
                    break;
                default:
                    break;
            }
            return true;
        }
    };

    private void setRootFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        toolbarTitle.setText("Home");
        homeFrag = new HomeFragment();
        currentFrag = homeFrag;
        fragmentManager.beginTransaction().add(R.id.activity_main_frame_layout,homeFrag, "0").commit();
    }

}
