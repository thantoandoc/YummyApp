package com.team.ymmy.yummyapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.team.ymmy.adapters.DishChooseAdapter;
import com.team.ymmy.constant.Constant;
import com.team.ymmy.model.DishChooseModel;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity implements ValueEventListener, View.OnClickListener {

    private Toolbar mToolbar;
    private int table_order;
    private Button  btnCheckout;
    private RecyclerView mRecyclerDish;
    private DishChooseAdapter adapter;
    private ArrayList<DishChooseModel> arrayList;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        mapWidgets();
        setupToolbar();
        handleEvents();
    }

    private void handleEvents() {
        btnCheckout.setOnClickListener(this);
    }

    private void mapWidgets() {
        Intent intent = getIntent();
        table_order = intent.getIntExtra(Constant.TABLE_NAME, 0);
        mToolbar = findViewById(R.id.toolbar_checkout);
        btnCheckout = findViewById(R.id.btn_checkout);
        arrayList = new ArrayList<>();
        mRecyclerDish = findViewById(R.id.recycler_dish_for_checkout);
        adapter = new DishChooseAdapter(this, R.layout.item_dish_choosen, arrayList);
        mRecyclerDish.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerDish.setLayoutManager(mLayoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        getDatas();
    }

    private void getDatas() {
        databaseReference.child("DishChoose").child("ban_" +(table_order  + 1)).addValueEventListener(this);
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
    public void onDataChange(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()){
            DishChooseModel dish = ds.getValue(DishChooseModel.class);
            arrayList.add(dish);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_checkout:

                break;
        }
    }
}
