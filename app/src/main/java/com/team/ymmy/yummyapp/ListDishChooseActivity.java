package com.team.ymmy.yummyapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.team.ymmy.adapters.DishChooseAdapter;

public class ListDishChooseActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerDish;
    private Button btn_submit;
    private DishChooseAdapter adapter;

    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dish_choose);
        mapWidgets();
        setupToolbar();
        handleEvents();
    }

    private void handleEvents() {
        btn_submit.setOnClickListener(this);
    }

    private void mapWidgets() {
        mToolbar = findViewById(R.id.toolbar_list_choose);
        mRecyclerDish = findViewById(R.id.recycler_dish_choose);
        btn_submit = findViewById(R.id.btn_submit);
        adapter = new DishChooseAdapter(this, R.layout.item_dish_choosen, CatalogActivity.mDSMonAn);
        mRecyclerDish.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerDish.setLayoutManager(mLayoutManager);

        database = FirebaseDatabase.getInstance().getReference();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit:{

                return;
            }
        }
    }
}
