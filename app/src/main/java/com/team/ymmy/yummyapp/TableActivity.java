package com.team.ymmy.yummyapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.team.ymmy.adapters.TableAdapter;
import com.team.ymmy.interfaces.InternetConnection;
import com.team.ymmy.model.Table;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {

    private ArrayList<Table> mTableList;
    private Toolbar mToolbar;
    private ProgressBar progressBar;
    private TableAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private FirebaseDatabase database;
    private DatabaseReference mTableListRef;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        mapViews();
        setupToolbar();
        setControls();

    }
    private void showSnack() {
        Snackbar snackbar = Snackbar.make( findViewById(android.R.id.content) , R.string.nE, Snackbar.LENGTH_INDEFINITE);
        snackbar.getView().setBackgroundResource(R.color.black);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) snackbar.getView().getLayoutParams();
        layoutParams.setMargins(0,0,0,0);
        snackbar.getView().setLayoutParams(layoutParams);
        snackbar.show();

    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("Table List");
    }
    private void mapViews() {
        mToolbar = findViewById(R.id.toolbar_table);
        progressBar = findViewById(R.id.progress_table);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_grid_table);
        mTableList = new ArrayList<>();
        mAdapter = new TableAdapter(TableActivity.this, R.layout.item_table, mTableList);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        database = FirebaseDatabase.getInstance();
        mTableListRef = database.getReference().child("danhsachbanan");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.config_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sign_out:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setControls() {
        mTableListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mTableList.clear();
                progressBar.setVisibility(View.INVISIBLE);
                for( DataSnapshot ds: dataSnapshot.getChildren()) {
                    Table table = ds.getValue(Table.class);
                    mTableList.add(table);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
