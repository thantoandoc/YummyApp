package com.team.ymmy.yummyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.team.ymmy.adapters.TableAdapter;
import com.team.ymmy.model.Table;
import com.team.ymmy.model.Table_Demo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.HashMap;

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
        mTableListRef = database.getReference("danhsachbanan");
    }


    private void setControls() {
        mTableListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                HashMap<Integer, Boolean> mHashMap = (HashMap<Integer, Boolean>) dataSnapshot.getValue();

                Log.d("DATACHANGE", "onDataChange: " + mHashMap.size());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
