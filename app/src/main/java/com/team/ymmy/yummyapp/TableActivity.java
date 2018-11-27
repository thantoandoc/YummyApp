package com.team.ymmy.yummyapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
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
import android.widget.Toast;

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
import java.util.Locale;

public class TableActivity extends AppCompatActivity {

    private ArrayList<Table> mTableList;
    private Toolbar mToolbar;
    private ProgressBar progressBar;
    private TableAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private FirebaseDatabase database;
    private DatabaseReference mTableListRef;
    private static String language = "en";
    private final static String MY_LANGUAGE = "MY_LANGUAGE";
    private final static String MY_LANGUAGE_FILE = "MY_LANGUAGE_FILE";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferences = getSharedPreferences(MY_LANGUAGE_FILE, MODE_PRIVATE);
        language = sharedPreferences.getString(MY_LANGUAGE, "en");
        changeLanguage(language);
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
        mToolbar.setTitle(getResources().getString(R.string.title_table_list));
    }
    private void mapViews() {
        mToolbar = findViewById(R.id.toolbar_table);
        progressBar = findViewById(R.id.progress_table);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_grid_table);
        mTableList = new ArrayList<>();
        mAdapter = new TableAdapter(TableActivity.this, R.layout.item_table, mTableList);
        mRecyclerView.setAdapter(mAdapter);
        int numberSpanCount = getApplicationContext().getResources().getInteger(R.integer.number_of_grid_items);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, numberSpanCount);
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
            case R.id.action_language:
                selectAndChangeLanguage();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void selectAndChangeLanguage() {
        new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.title_language_dialog)).setItems(getResources().getStringArray(R.array.languages), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0: language = "vi";
                    break;
                    case 1: language = "en";
                    break;
                }
                changeLanguage(language);
            }
        }).create().show();
    }


    private void changeLanguage(String language) {
        sharedPreferences = getSharedPreferences(MY_LANGUAGE_FILE, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(MY_LANGUAGE, language);
        editor.commit();
        changeLanguageOnSystem(language);
        mapViews();
        setupToolbar();
        setControls();
    }

    private void changeLanguageOnSystem(String language) {
        Locale locale = new Locale(language);
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, getResources().getDisplayMetrics());
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
