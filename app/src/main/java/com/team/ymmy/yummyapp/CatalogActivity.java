package com.team.ymmy.yummyapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.team.ymmy.adapters.CatalogAdapter;
import com.team.ymmy.constant.Constant;
import com.team.ymmy.model.Catalog;

import java.util.ArrayList;

public class CatalogActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvDanhMucMonAn;
    private ArrayList<Catalog> mDanhMucMonAn;
    private CatalogAdapter catalogAdapter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        mapViews();
        setupToolbar();
        setControls();
        handleEvents();
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

    private void handleEvents() {
        lvDanhMucMonAn.setOnItemClickListener(this);
    }

    private void setControls() {
        mDanhMucMonAn.add(new Catalog("Cold Starter",R.drawable.bg_starter));
        mDanhMucMonAn.add(new Catalog("Appetizers",R.drawable.bg_appetizer));
        mDanhMucMonAn.add(new Catalog("Soup",R.drawable.bg_soup));
        mDanhMucMonAn.add(new Catalog("Main Course",R.drawable.bg_main_course));
        mDanhMucMonAn.add(new Catalog("Cheese & Biscuits",R.drawable.bg_cheese));
        mDanhMucMonAn.add(new Catalog("Dessert",R.drawable.bg_desserts));

        catalogAdapter.notifyDataSetChanged();
    }

    private void mapViews() {
        mToolbar = findViewById(R.id.toolbar_category);
        lvDanhMucMonAn = findViewById(R.id.lv_catalog);
        mDanhMucMonAn = new ArrayList<>();
        catalogAdapter = new CatalogAdapter(CatalogActivity.this, R.layout.item_category, mDanhMucMonAn);
        lvDanhMucMonAn.setAdapter(catalogAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(CatalogActivity.this, SideMenu.class);
        intent.putExtra(Constant.POSITION_CATEGORY, Constant.CATEGORY_ID[position]);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                onBackPressed();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
