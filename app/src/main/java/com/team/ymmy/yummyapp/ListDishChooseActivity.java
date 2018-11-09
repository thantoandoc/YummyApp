package com.team.ymmy.yummyapp;

import android.content.Intent;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.team.ymmy.adapters.DishChooseAdapter;
import com.team.ymmy.constant.Constant;
import com.team.ymmy.model.DishChooseModel;

import java.util.ArrayList;

public class ListDishChooseActivity extends AppCompatActivity implements View.OnClickListener, ValueEventListener {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerDish;
    private Button btn_submit;
    private DishChooseAdapter adapter;
    private ArrayList<DishChooseModel> arrayList;
    private int table_order;

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
        Intent intent = getIntent();
        table_order = intent.getIntExtra(Constant.TABLE_NAME, 0);
        mToolbar = findViewById(R.id.toolbar_list_choose);
        mRecyclerDish = findViewById(R.id.recycler_dish_choose);
        arrayList = new ArrayList<>();
        btn_submit = findViewById(R.id.btn_submit);
        adapter = new DishChooseAdapter(this, R.layout.item_dish_choosen, arrayList);
        mRecyclerDish.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerDish.setLayoutManager(mLayoutManager);

        database = FirebaseDatabase.getInstance().getReference();

        getDatas();
}

    private void getDatas() {
        database.child("DishChoose").child("ban_" +(table_order  + 1)).addValueEventListener(this);

    }


    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        database = FirebaseDatabase.getInstance().getReference();
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
                pushDataToFirebase();
                finish();
                return;
            }
        }
    }

    private void pushDataToFirebase() {
        database.child("danhsachbanan").child("ban_" + (table_order + 1)).child("status").setValue(true);
        for (int i = 0; i < CatalogActivity.mDSMonAn.size(); i++){
            database.child(Constant.DISHCHOOSE).child("ban_" + (table_order + 1) ).push().setValue(CatalogActivity.mDSMonAn.get(i));
        }
        CatalogActivity.mDSMonAn.clear();
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        if(arrayList.size() == 0){
            if (CatalogActivity.mDSMonAn.size() > 0){
                for (DishChooseModel dish : CatalogActivity.mDSMonAn){
                    arrayList.add(dish);
                }
            }
            for(DataSnapshot ds : dataSnapshot.getChildren()){
                DishChooseModel dish = ds.getValue(DishChooseModel.class);
                arrayList.add(dish);
            }

            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

}
