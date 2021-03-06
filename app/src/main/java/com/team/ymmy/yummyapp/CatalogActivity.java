package com.team.ymmy.yummyapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.team.ymmy.adapters.CatalogAdapter;
import com.team.ymmy.constant.Constant;
import com.team.ymmy.model.Catalog;
import com.team.ymmy.model.DishChoose;
import com.team.ymmy.model.DishChooseModel;
import com.team.ymmy.model.DishModel;

import java.util.ArrayList;

public class CatalogActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    public static ArrayList<DishChooseModel> mDSMonAn;
    private ListView lvDanhMucMonAn;
    private ArrayList<Catalog> mDanhMucMonAn;
    private CatalogAdapter catalogAdapter;
    private Toolbar mToolbar;
    private FloatingActionButton fab, fab_list, fab_payment;
    private boolean isCheck = false;
    private int iTABLE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        mapViews();
        setupToolbar();
        setControls();
        handleEvents();
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

    private void handleEvents() {
        lvDanhMucMonAn.setOnItemClickListener(this);
        fab.setOnClickListener(this);
        fab_list.setOnClickListener(this);
        fab_payment.setOnClickListener(this);
    }

    private void setControls() {
        mDanhMucMonAn.add(new Catalog(getResources().getString(R.string.action_cold_starter),R.drawable.bg_start));
        mDanhMucMonAn.add(new Catalog(getResources().getString(R.string.action_appetizers),R.drawable.bg_appetizer));
        mDanhMucMonAn.add(new Catalog(getResources().getString(R.string.action_soup),R.drawable.bg_soup));
        mDanhMucMonAn.add(new Catalog(getResources().getString(R.string.action_main_courses),R.drawable.bg_main_course));
        mDanhMucMonAn.add(new Catalog(getResources().getString(R.string.action_cheese_biscuits),R.drawable.bg_cheese));
        mDanhMucMonAn.add(new Catalog(getResources().getString(R.string.action_desserts),R.drawable.bg_dessert));

        catalogAdapter.notifyDataSetChanged();
    }

    private void mapViews() {
        Intent intent = getIntent();
        iTABLE = intent.getIntExtra(Constant.TABLE_NAME, 0);
        mToolbar = findViewById(R.id.toolbar_category);
        lvDanhMucMonAn = findViewById(R.id.lv_catalog);
        fab = findViewById(R.id.fab);
        fab_list = findViewById(R.id.fab_list);
        fab_payment = findViewById(R.id.fab_payment);
        mDanhMucMonAn = new ArrayList<>();
        catalogAdapter = new CatalogAdapter(CatalogActivity.this, R.layout.item_category, mDanhMucMonAn);
        lvDanhMucMonAn.setAdapter(catalogAdapter);
        if( mDSMonAn == null){
            mDSMonAn = new ArrayList<>();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        invisibleFloatingButton();
        Intent intent = new Intent(CatalogActivity.this, SideMenu.class);
        intent.putExtra(Constant.POSITION_CATEGORY, Constant.CATEGORY_ID[position]);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                if( mDSMonAn.size() != 0 ){
                    new AlertDialog.Builder(this)
                            .setMessage("Are you sure you want to cancel order ?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mDSMonAn = null;
                                    onBackPressed();
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();

                }else{
                    onBackPressed();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab :{
                isCheck = !isCheck;
                if (isCheck){
                    visibleFloatingButton();
                }else {
                    invisibleFloatingButton();
                }
                break;
            }
            case R.id.fab_list :{
                invisibleFloatingButton();
                Intent intent = new Intent(CatalogActivity.this, ListDishChooseActivity.class);
                intent.putExtra(Constant.TABLE_NAME, iTABLE);
                startActivity(intent);
                break;
            }
            case R.id.fab_payment :{
                invisibleFloatingButton();
                Intent intent = new Intent(CatalogActivity.this, CheckoutActivity.class);
                intent.putExtra(Constant.TABLE_NAME, iTABLE);
                startActivity(intent);
                break;
            }
        }
    }

    private void invisibleFloatingButton() {
        fab_list.hide();
        fab_payment.hide();
    }

    private void visibleFloatingButton() {
        fab_list.show();
        fab_payment.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isCheck = false;
    }
}
