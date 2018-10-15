package com.example.android.alexandriatourguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the toolbar view
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Create an ActionBar using the toolbar view
        setSupportActionBar(toolbar);

        // Find the drawer view
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Create an ActionBarDrawerToggle object
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        // Set the ActionBarDrawerToggle on the drawer view
        drawer.setDrawerListener(toggle);
        // Offset the ActionBarDrawerToggle properly based on whether the drawer is opened or closed
        toggle.syncState();

        // Find the NavigationView view
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        // Set a listener that will be notified when a menu item is selected.
        navigationView.setNavigationItemSelectedListener(this);

        //Display landmarks when the activity is loaded
        displaySelectedScreen(R.id.nav_landmarks);
    }

    // Handle the ActionBarDrawerToggle press
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //calling the method displaySelectedScreen and passing the id of selected menu
        displaySelectedScreen(id);
        return true;
    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_landmarks:
                fragment = new LandmarksFragment();
                break;
            case R.id.nav_events:
                fragment = new EventsFragment();
                break;
            case R.id.nav_local_dishes:
                fragment = new LocalDishesFragment();
                break;
            case R.id.nav_restaurants:
                fragment = new RestaurantsFragment();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
