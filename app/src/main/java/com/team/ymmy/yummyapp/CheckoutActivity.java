package com.team.ymmy.yummyapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.team.ymmy.adapters.CheckoutAdataper;
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
    private CheckoutAdataper checkoutAdataper;
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
        checkoutAdataper = new CheckoutAdataper(this, R.layout.item_dish_checkout, arrayList);

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
                if(arrayList.size() > 0){
                    int mTotalPrice = getTotalPrice();
                    AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogTransparentStyle);
                    View rootView = LayoutInflater.from(this).inflate(R.layout.dialog_checkout, null);
                    RecyclerView mCheckoutRecycle = rootView.findViewById(R.id.recycler_dish_checkout);
                    Button btnCheckoutDialog = rootView.findViewById(R.id.btn_dialog_checkout);
                    TextView totalPrice = rootView.findViewById(R.id.totalPrice);

                    mCheckoutRecycle.setAdapter(checkoutAdataper);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
                    mCheckoutRecycle.setLayoutManager(mLayoutManager);

                    totalPrice.setText(String.valueOf(mTotalPrice));
                    builder.setView(rootView);
                    final AlertDialog alertDialog = builder.create();
                    //handleEvents of Button;
                    btnCheckoutDialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // deleteDataOnFirebase;
                            databaseReference.child("danhsachbanan").child("ban_" + (table_order + 1)).child("status").setValue(false);
                            databaseReference.child(Constant.DISHCHOOSE).child("ban_" + (table_order + 1) ).removeValue(new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                    if(databaseError == null){
                                        Intent intent = new Intent(getApplicationContext(), TableActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                        }
                    });
                    alertDialog.show();
                }
                break;
        }

    }

    private int getTotalPrice() {
        int total = 0;
        for (int i = 0; i < arrayList.size(); i++){
            total += arrayList.get(i).getPrice();
        }
        return total;
    }
}
