package com.team.ymmy.yummyapp;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class SideMenu extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private FrameLayout mFrameLayout;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_menu);

        mapViews();
        setupToolbar();
        setupDrawerToogle();
    }

    private void setupDrawerToogle() {
//        ActionBarDrawerToggle mDrawerToogle = new ActionBarDrawerToggle(SideMenu.this, mDrawerLayout, mToolbar, R.string.closeDrawer, R.string.openDrawer);
//        mDrawerLayout.addDrawerListener(mDrawerToogle);
//        mDrawerToogle.syncState();
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar mActionBar =  getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setIcon(R.drawable.ic_arrow_back_24dp);
    }

    private void mapViews() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToolbar = findViewById(R.id.toolbar);
        mFrameLayout = findViewById(R.id.content_frame);
        mNavigationView = findViewById(R.id.nav_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_categories:{
                if(mDrawerLayout.isDrawerOpen(Gravity.RIGHT)){
                    mDrawerLayout.closeDrawer(Gravity.RIGHT);
                }else {
                    mDrawerLayout.openDrawer(Gravity.RIGHT);
                }
                return true;
            }
            case android.R.id.home : {
                onBackPressed();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
