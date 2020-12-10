package com.example.tbnaaproject;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class TheAdminMenu extends AppCompatActivity {

    private DrawerLayout mDrawerLayout = null;
    private ActionBarDrawerToggle mActionBarDrawerToggle = null;

    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_admin_menu);

//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        navigationView = (NavigationView) findViewById(R.id.navigation);
//        initDrawer();
    }

    private void initDrawer() {
        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        }; mDrawerLayout.addDrawerListener(drawerToggle);
    }
}