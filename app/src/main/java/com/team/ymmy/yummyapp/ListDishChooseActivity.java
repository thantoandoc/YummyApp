package com.team.ymmy.yummyapp;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    private TextView totalPriceOrder;
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

    private int getTotalPrice() {
        int total = 0;
        for (int i = 0; i < arrayList.size(); i++){
            if(arrayList.get(i).getDiscount() > 0){
                total += ((int) (arrayList.get(i).getPrice() -  arrayList.get(i).getPrice() *  arrayList.get(i).getDiscount() / 100)) * arrayList.get(i).getCounter();
            }else{
                total += arrayList.get(i).getPrice() * arrayList.get(i).getCounter();
            }
        }
        return total;
    }

    private void mapWidgets() {
        Intent intent = getIntent();
        table_order = intent.getIntExtra(Constant.TABLE_NAME, 0);
        mToolbar = findViewById(R.id.toolbar_list_choose);
        mRecyclerDish = findViewById(R.id.recycler_dish_choose);
        arrayList = new ArrayList<>();
        btn_submit = findViewById(R.id.btn_submit);
        totalPriceOrder = findViewById(R.id.totalPriceOrder);
        adapter = new DishChooseAdapter(this, R.layout.item_dish_choosen, arrayList);
        mRecyclerDish.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerDish.setLayoutManager(mLayoutManager);
        initSwipe();

        database = FirebaseDatabase.getInstance().getReference();
        getDatas();

}

    private void initSwipe() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT ) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                final DishChooseModel deleteItem= adapter.getItem(position);

                if (direction == ItemTouchHelper.LEFT){
                    adapter.removeItem(position);
                    Snackbar snackbar = Snackbar.make( findViewById(android.R.id.content) , "This dish was removed",Snackbar.LENGTH_LONG);
                    snackbar.setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            adapter.restoreItem(deleteItem, position);
                        }
                    });
                    snackbar.setActionTextColor(Color.GREEN);
                    snackbar.show();
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                final View foregroundView = ((DishChooseAdapter.ViewHolder) viewHolder).viewForeground;
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
                    foregroundView.setTranslationX(dX);
                }else {
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }


            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerDish);
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
                if(CatalogActivity.mDSMonAn.size() > 0){
                    pushDataToFirebase();
                    finish();
                }
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
                System.out.println(ds.getKey());
                arrayList.add(dish);
            }

            adapter.notifyDataSetChanged();
        }
        totalPriceOrder.setText(String.valueOf(getTotalPrice()));
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

}
