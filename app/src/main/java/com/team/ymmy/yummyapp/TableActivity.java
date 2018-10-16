package com.team.ymmy.yummyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.team.ymmy.adapters.TableAdapter;
import com.team.ymmy.model.Table;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {

    private ArrayList<Table> mTableList;
    private TableAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private FirebaseDatabase database;
    private DatabaseReference mTableListRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        mapViews();
        setControls();
    }



    private void mapViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_grid_table);
        mTableList = new ArrayList<>();
        mAdapter = new TableAdapter(TableActivity.this, R.layout.item_table, mTableList);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        database = FirebaseDatabase.getInstance();
        mTableListRef = database.getReference().child("danhsachbanan");
    }


    private void setControls() {
        mTableListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for( DataSnapshot ds: dataSnapshot.getChildren()) {
                    Table table = ds.getValue(Table.class);
                    mTableList.add(table);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
