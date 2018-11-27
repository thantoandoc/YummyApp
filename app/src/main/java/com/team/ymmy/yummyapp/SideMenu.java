package com.team.ymmy.yummyapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.team.ymmy.constant.Constant;
import com.team.ymmy.fragments.AppetizersFragment;
import com.team.ymmy.fragments.CheesesFragment;
import com.team.ymmy.fragments.ColdStarterFragment;
import com.team.ymmy.fragments.DessertsFragment;
import com.team.ymmy.fragments.MainCoursesFragment;
import com.team.ymmy.fragments.SoupsFragment;

public class SideMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private int fragmentNow = 0;
    private static final String FRAGMENT_NOW_FILE = "FRAGMENT_NOW_FILE";
    private static final String FRAGMENT_NOW = "FRAGMENT_NOW";

    @Override
    protected void onRestart() {
        super.onRestart();
        SharedPreferences sharedPreferences = getSharedPreferences(FRAGMENT_NOW_FILE, MODE_PRIVATE);
        fragmentNow = sharedPreferences.getInt(FRAGMENT_NOW, R.id.action_cold_starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_menu);

        mapViews();
        setupToolbar();
        setupDrawer();
    }

    private void setupDrawer() {
        mNavigationView.setNavigationItemSelectedListener(this);
    }


    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
    }

    private void mapViews() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToolbar = findViewById(R.id.toolbar);
        mNavigationView = findViewById(R.id.nav_view);

        setDefaultItem();

    }

    private void setDefaultItem() {
        Intent intent = getIntent();
        int ID = intent.getIntExtra(Constant.POSITION_CATEGORY, fragmentNow);
        System.out.println(fragmentNow);
        mNavigationView.setCheckedItem(ID);
        Fragment fragment;
        switch (ID){
            case R.id.action_cold_starter:
                fragment = new ColdStarterFragment();
                changeFragment(fragment, ID);
                break;
            case R.id.action_appetizers:
                fragment = new AppetizersFragment();
                changeFragment(fragment, ID);
                break;
            case R.id.action_soup:
                fragment = new SoupsFragment();
                changeFragment(fragment, ID);
                break;
            case R.id.action_main_courses:
                fragment = new MainCoursesFragment();
                changeFragment(fragment, ID);
                break;
            case R.id.action_cheese_biscuits:
                fragment = new CheesesFragment();
                changeFragment(fragment, ID);
                break;
            case R.id.action_desserts:
                fragment = new DessertsFragment();
                changeFragment(fragment, ID);
                break;
        }
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
                if(mDrawerLayout.isDrawerOpen(Gravity.END)){
                    mDrawerLayout.closeDrawer(Gravity.END);
                }else {
                    mDrawerLayout.openDrawer(Gravity.END);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.action_cold_starter : {
                fragmentNow = item.getItemId();
                System.out.println(fragmentNow);
                fragment = new ColdStarterFragment();
                changeFragment(fragment, item.getItemId());
                mDrawerLayout.closeDrawer(Gravity.END);
                return true;
            }
            case R.id.action_appetizers : {
                fragmentNow = item.getItemId();
                System.out.println(fragmentNow);
                fragment = new AppetizersFragment();
                changeFragment(fragment, item.getItemId());
                mDrawerLayout.closeDrawer(Gravity.END);
                return true;
            }
            case R.id.action_soup : {
                fragmentNow = item.getItemId();
                System.out.println(fragmentNow);
                fragment = new SoupsFragment();
                changeFragment(fragment, item.getItemId());
                mDrawerLayout.closeDrawer(Gravity.END);
                return true;
            }
            case R.id.action_main_courses : {
                fragmentNow = item.getItemId();
                System.out.println(fragmentNow);
                fragment = new MainCoursesFragment();
                changeFragment(fragment, item.getItemId());
                mDrawerLayout.closeDrawer(Gravity.END);
                return true;
            }
            case R.id.action_cheese_biscuits : {
                fragmentNow = item.getItemId();
                System.out.println(fragmentNow);
                fragment = new CheesesFragment();
                changeFragment(fragment, item.getItemId());
                mDrawerLayout.closeDrawer(Gravity.END);
                return true;
            }
            case R.id.action_desserts : {
                fragmentNow = item.getItemId();
                System.out.println(fragmentNow);
                fragment = new DessertsFragment();
                changeFragment(fragment, item.getItemId());
                mDrawerLayout.closeDrawer(Gravity.END);
                return true;
            }
        }
        return false;
    }
    public void changeFragment(Fragment fragment, int fragmentNow){
        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.content_frame, fragment);
        mFragmentTransaction.commit();

        SharedPreferences sharedPreferences = getSharedPreferences(FRAGMENT_NOW_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(FRAGMENT_NOW, fragmentNow);
        editor.commit();
    }



}
