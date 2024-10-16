package com.example.gdscproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.gdscproject.fragments.HomeFragment;

import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout;
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DuoDrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();

    }

    private void init() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Remove the default title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false); // Remove the title
        }

// Set the navigation icon (hamburger icon) if needed
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(v -> {
            // Handle navigation (drawer opening, etc.)
        });

        drawerLayout = (DuoDrawerLayout) findViewById(R.id.drawer);
        DuoDrawerToggle drawerToggle = new DuoDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        View contentView = drawerLayout.getContentView();
        View menuView = drawerLayout.getMenuView();

        LinearLayout ll_Home = menuView.findViewById(R.id.ll_Home);
        LinearLayout ll_Profile = menuView.findViewById(R.id.ll_Profile);
        LinearLayout ll_Setting = menuView.findViewById(R.id.ll_Setting);
        LinearLayout ll_bookmark = menuView.findViewById(R.id.ll_bookmark);
        LinearLayout ll_Logout = menuView.findViewById(R.id.ll_Logout);


        ll_Home.setOnClickListener(this);
        ll_Profile.setOnClickListener(this);
        ll_Setting.setOnClickListener(this);
        ll_bookmark.setOnClickListener(this);
        ll_Logout.setOnClickListener(this);


        replace(new HomeFragment());


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_Home:
                replace(new HomeFragment(),"Home");
                break;

            case R.id.ll_Profile:
                replace(new HomeFragment(),"Profile");
                break;

            case R.id.ll_Setting:
                replace(new HomeFragment(),"Setting");
                break;

            case R.id.ll_bookmark:
                Toast.makeText(this, "Share...", Toast.LENGTH_SHORT).show();
                break;

            case R.id.ll_Logout:
                Toast.makeText(this, "Logout...", Toast.LENGTH_SHORT).show();
                break;

        }
        drawerLayout.closeDrawer();
    }

    private void replace(Fragment fragment, String s) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.addToBackStack(s);
        transaction.commit();
    }

    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();
    }
}