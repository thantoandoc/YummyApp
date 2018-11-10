package com.team.ymmy.yummyapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.NavigationView;
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
import com.team.ymmy.model.DishModel;

import java.util.ArrayList;

public class SideMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;

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
        int ID = intent.getIntExtra(Constant.POSITION_CATEGORY, R.id.action_cold_starter);
        mNavigationView.setCheckedItem(ID);
        Fragment fragment;
        switch (ID){
            case R.id.action_cold_starter:
                fragment = new ColdStarterFragment();
                changeFragment(fragment);
                break;
            case R.id.action_appetizers:
                fragment = new AppetizersFragment();
                changeFragment(fragment);
                break;
            case R.id.action_soup:
                fragment = new SoupsFragment();
                changeFragment(fragment);
                break;
            case R.id.action_main_courses:
                fragment = new MainCoursesFragment();
                changeFragment(fragment);
                break;
            case R.id.action_cheese_biscuits:
                fragment = new CheesesFragment();
                changeFragment(fragment);
                break;
            case R.id.action_desserts:
                fragment = new DessertsFragment();
                changeFragment(fragment);
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

    @Override
    public boolean onNavigationItemSelected( MenuItem item) {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.action_cold_starter : {
                fragment = new ColdStarterFragment();
                changeFragment(fragment);
                mDrawerLayout.closeDrawer(Gravity.RIGHT);
                return true;
            }
            case R.id.action_appetizers : {
                fragment = new AppetizersFragment();
                changeFragment(fragment);
                mDrawerLayout.closeDrawer(Gravity.RIGHT);
                return true;
            }
            case R.id.action_soup : {
                fragment = new SoupsFragment();
                changeFragment(fragment);
                mDrawerLayout.closeDrawer(Gravity.RIGHT);
                return true;
            }
            case R.id.action_main_courses : {
                fragment = new MainCoursesFragment();
                changeFragment(fragment);
                mDrawerLayout.closeDrawer(Gravity.RIGHT);
                return true;
            }
            case R.id.action_cheese_biscuits : {
                fragment = new CheesesFragment();
                changeFragment(fragment);
                mDrawerLayout.closeDrawer(Gravity.RIGHT);
                return true;
            }
            case R.id.action_desserts : {
                fragment = new DessertsFragment();
                changeFragment(fragment);
                mDrawerLayout.closeDrawer(Gravity.RIGHT);
                return true;
            }
        }
        return false;
    }
    public void changeFragment(Fragment fragment){
        FragmentTransaction mFragmentTransaction = getFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.content_frame, fragment);
        mFragmentTransaction.commit();
    }



}
