package com.team.ymmy.yummyapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.team.ymmy.model.DishModel;

import java.util.ArrayList;
import java.util.Date;

public class CheckoutActivity extends AppCompatActivity implements ValueEventListener, View.OnClickListener {

    private Toolbar mToolbar;
    private int table_order;
    private Button  btnCheckout;
    private TextView totalPrice;
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
        totalPrice = findViewById(R.id.totalPrice);
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

    private int indexOf(ArrayList<DishChooseModel> array, DishChooseModel dish){
        for(int i = 0; i < array.size(); i++){
            if(equal(array.get(i), dish)) return i;
        }
        return -1;
    }
    private boolean equal(DishChooseModel a, DishChooseModel b){
        if(a.getId() == b.getId() && a.getName().equals(b.getName())) return true;
        return false;
    }
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()){
            DishChooseModel dish = ds.getValue(DishChooseModel.class);
            int idx = indexOf(arrayList, dish);
            if (idx >= 0){
                int count = arrayList.get(idx).getCounter() + dish.getCounter();
                arrayList.get(idx).setCounter(count);
            }else{
                arrayList.add(dish);
            }
        }
        adapter.notifyDataSetChanged();
        totalPrice.setText(String.valueOf(getTotalPrice()));
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_checkout:
                databaseReference.child("danhsachbanan").child("ban_" + (table_order + 1)).child("status").setValue(false);
                for (int i = 0; i < arrayList.size(); i++){
                    databaseReference.child(Constant.GET_BILL).child("ban_" + (table_order + 1) +"_"+ System.currentTimeMillis()).push().setValue(arrayList.get(i));
                }
                databaseReference.child(Constant.DISHCHOOSE).child("ban_" + (table_order + 1) ).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if(databaseError == null){
                            Intent intent = new Intent(getApplicationContext(), TableActivity.class);
                            startActivity(intent);
                        }
                    }
                });
                break;
//                if(arrayList.size() > 0){
//                    int mTotalPrice = getTotalPrice();
//                    AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogTransparentStyle);
//                    View rootView = LayoutInflater.from(this).inflate(R.layout.dialog_checkout, null);
//                    RecyclerView mCheckoutRecycle = rootView.findViewById(R.id.recycler_dish_checkout);
//                    Button btnCheckoutDialog = rootView.findViewById(R.id.btn_dialog_checkout);
//                    TextView totalPrice = rootView.findViewById(R.id.totalPrice);
//
//                    mCheckoutRecycle.setAdapter(checkoutAdataper);
//                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
//                    mCheckoutRecycle.setLayoutManager(mLayoutManager);
//
//                    totalPrice.setText(String.valueOf(mTotalPrice));
//                    builder.setView(rootView);
//                    final AlertDialog alertDialog = builder.create();
//                    //handleEvents of Button;
//                    btnCheckoutDialog.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            // deleteDataOnFirebase;
//                            databaseReference.child("danhsachbanan").child("ban_" + (table_order + 1)).child("status").setValue(false);
//                            for (int i = 0; i < arrayList.size(); i++){
//                                databaseReference.child(Constant.GET_BILL).child("ban_" + (table_order + 1) +"_"+ System.currentTimeMillis()).push().setValue(arrayList.get(i));
//                            }
//                            databaseReference.child(Constant.DISHCHOOSE).child("ban_" + (table_order + 1) ).removeValue(new DatabaseReference.CompletionListener() {
//                                @Override
//                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//                                    if(databaseError == null){
//                                        Intent intent = new Intent(getApplicationContext(), TableActivity.class);
//                                        startActivity(intent);
//                                    }
//                                }
//                            });
//                        }
//                    });
//                    alertDialog.show();
//                }

        }

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
}
