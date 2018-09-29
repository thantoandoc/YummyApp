package com.team.ymmy.yummyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.team.ymmy.adapters.TableAdapter;
import com.team.ymmy.model.Table;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {

    private ArrayList<Table> mArrayList;
    private TableAdapter mAdapter;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        setControls();
        mapViews();
    }



    private void mapViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_grid_table);

        mAdapter = new TableAdapter(TableActivity.this, R.layout.item_table, mArrayList);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }


    private void setControls() {
        mArrayList = new ArrayList<>();
        mArrayList.add(new Table(1,R.drawable.background));
        mArrayList.add(new Table(2,R.drawable.background));
        mArrayList.add(new Table(3,R.drawable.background));
        mArrayList.add(new Table(4,R.drawable.background));
        mArrayList.add(new Table(5,R.drawable.background));
        mArrayList.add(new Table(6,R.drawable.background));
        mArrayList.add(new Table(7,R.drawable.background));
    }

}
