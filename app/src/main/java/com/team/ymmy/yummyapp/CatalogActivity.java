package com.team.ymmy.yummyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.team.ymmy.adapters.CatalogAdapter;
import com.team.ymmy.model.Catalog;

import java.util.ArrayList;

public class CatalogActivity extends AppCompatActivity {

    private ListView lvDanhMucMonAn;
    private ArrayList<Catalog> mDanhMucMonAn;
    private CatalogAdapter catalogAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        mapViews();
        setControls();
    }

    private void setControls() {
        mDanhMucMonAn.add(new Catalog("Cold Starter",R.drawable.background));
        mDanhMucMonAn.add(new Catalog("Appetizers",R.drawable.background));
        mDanhMucMonAn.add(new Catalog("Soup",R.drawable.background));
        mDanhMucMonAn.add(new Catalog("Main Course",R.drawable.background));
        mDanhMucMonAn.add(new Catalog("Cheese & Biscuits",R.drawable.background));
        mDanhMucMonAn.add(new Catalog("Dessert",R.drawable.background));

        catalogAdapter.notifyDataSetChanged();
    }

    private void mapViews() {
        lvDanhMucMonAn = findViewById(R.id.lv_catalog);
        mDanhMucMonAn = new ArrayList<>();
        catalogAdapter = new CatalogAdapter(CatalogActivity.this, R.layout.catalog_item, mDanhMucMonAn);
        lvDanhMucMonAn.setAdapter(catalogAdapter);
    }
}
