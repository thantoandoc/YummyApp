package com.team.ymmy.yummyapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.internal.to;
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
    private long backUpTotal = 0L, backUpSize = 0L;
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
        totalPriceOrder = findViewById(R.id.totalPriceOrder);
        adapter = new DishChooseAdapter(this, R.layout.item_dish_choosen, arrayList, totalPriceOrder);
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
                    Snackbar snackbar = Snackbar.make( findViewById(android.R.id.content) , getResources().getString(R.string.delete_string),Snackbar.LENGTH_SHORT);
                    snackbar.setAction(getResources().getString(R.string.action_undo), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            adapter.restoreItem(deleteItem, position);
                            mRecyclerDish.scrollToPosition(position);
                        }
                    });
                    snackbar.setActionTextColor(Color.GREEN);
                    snackbar.show();
                }
                if (direction == ItemTouchHelper.RIGHT){

                }


            }

            private float convertDpToPixel(float dp, Context context){
                Resources resources = context.getResources();
                DisplayMetrics metrics = resources.getDisplayMetrics();
                float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
                return px;
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                View itemView = viewHolder.itemView;
                Paint paint = new Paint();
                Bitmap icon;

                if(dX > 0) {
                    paint.setColor(Color.parseColor("#3F51B5"));
                    icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_edit_24);
                    // Draw Rect with varying right side, equal to displacement dX

                    RectF background = new RectF((float) itemView.getLeft() - dX,
                            (float) itemView.getTop() +  convertDpToPixel(4, getApplicationContext()),
                            (float) itemView.getRight(),
                            (float) itemView.getBottom() - convertDpToPixel(4, getApplicationContext()));

                    c.drawRect(background, paint);

                    // Set the image icon for right swipe
                    c.drawBitmap(icon, (float) itemView.getLeft() + convertDpToPixel(16, getApplicationContext()), (float) itemView.getTop() +
                            ((float) itemView.getBottom() - (float) itemView.getTop() - icon.getHeight()) / 2, paint);

                } else {
                    paint.setColor(Color.parseColor("#FF0000"));
                    icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_delete_24);
                    // Draw Rect with varying right side, equal to displacement dX

                    RectF background = new RectF((float) itemView.getRight() + dX,
                            (float) itemView.getTop() +  convertDpToPixel(4, getApplicationContext()),
                            (float) itemView.getRight(),
                            (float) itemView.getBottom() - convertDpToPixel(4, getApplicationContext()));

                    c.drawRect(background, paint);

                    // Set the image icon for right swipe
                    c.drawBitmap(icon, (float) itemView.getRight() - convertDpToPixel(40, getApplicationContext()), (float) itemView.getTop() +
                            ((float) itemView.getBottom() - (float) itemView.getTop() - icon.getHeight()) / 2, paint);

                }


                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
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
                if(CatalogActivity.mDSMonAn.size() > 0 || isEquals(arrayList) ){
                    pushDataToFirebase();
                    finish();
                    return;
                }

            }
        }
    }

    private boolean isEquals(ArrayList<DishChooseModel> arrayList) {
        return (backUpSize != arrayList.size() || backUpTotal != getTotalPrice(arrayList));
    }
    private int getTotalPrice(ArrayList<DishChooseModel> mArrayDish) {
        int total = 0;
        for (int i = 0; i < mArrayDish.size(); i++){
            if(mArrayDish.get(i).getDiscount() > 0){
                total += ((int) (mArrayDish.get(i).getPrice() -  mArrayDish.get(i).getPrice() *  mArrayDish.get(i).getDiscount() / 100)) * mArrayDish.get(i).getCounter();
            }else{
                total += mArrayDish.get(i).getPrice() * mArrayDish.get(i).getCounter();
            }
        }
        return total;
    }

    private void pushDataToFirebase() {
        database.child("danhsachbanan").child("ban_" + (table_order + 1)).child("status").setValue(true);

        database.child(Constant.DISHCHOOSE).child( "ban_" + (table_order + 1) ).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError == null){
                    for (int i = 0; i < arrayList.size(); i++){
                        System.out.println("AAA");
                        database.child(Constant.DISHCHOOSE).child("ban_" + (table_order + 1) ).push().setValue(arrayList.get(i));
                    }
                }
            }
        });


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
            backUpTotal = getTotalPrice(arrayList);
            backUpSize = arrayList.size();
            adapter.notifyDataSetChanged();
        }
        totalPriceOrder.setText(String.valueOf(adapter.getTotalPrice()));

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

}
