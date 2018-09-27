package com.team.ymmy.yummyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.team.ymmy.adapters.TableAdapter;
import com.team.ymmy.model.Table;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayList<Table> arrayListTable;
    private TableAdapter tableAdapter;
    private GridView gridTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        mapViews();
        setControls();
        handleEvents();
    }



    private void mapViews() {
        gridTable = (GridView) findViewById(R.id.grid_table);
        arrayListTable = new ArrayList<>();
        tableAdapter = new TableAdapter(TableActivity.this, R.layout.item_table, arrayListTable);
        gridTable.setAdapter(tableAdapter);
    }


    private void setControls() {
       arrayListTable.add(new Table(1,R.drawable.background));
       arrayListTable.add(new Table(2,R.drawable.background));
       arrayListTable.add(new Table(3,R.drawable.background));
       arrayListTable.add(new Table(4,R.drawable.background));
       arrayListTable.add(new Table(5,R.drawable.background));
       arrayListTable.add(new Table(6,R.drawable.background));
       arrayListTable.add(new Table(7,R.drawable.background));

       tableAdapter.notifyDataSetChanged();
    }

    private void handleEvents() {
        gridTable.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Table table = (Table) tableAdapter.getItem(position);
        Intent intent = new Intent(TableActivity.this, CatalogActivity.class);
        startActivity(intent);
    }
}
