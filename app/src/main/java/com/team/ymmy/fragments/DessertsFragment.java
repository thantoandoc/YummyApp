package com.team.ymmy.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.team.ymmy.adapters.DishAdapterRecycler;
import com.team.ymmy.constant.Constant;
import com.team.ymmy.model.DishModel;
import com.team.ymmy.yummyapp.R;

import java.util.ArrayList;

public class DessertsFragment extends Fragment {

    private RecyclerView mDessertRecycleView;
    private ArrayList mDishArray;
    private DishAdapterRecycler mDishAdapter;
    private FirebaseDatabase database;
    private DatabaseReference mDessertRef;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dish, container, false);
        mapWidgets(rootView);
        initData();
        return rootView;
    }
    private void initData() {
        mDessertRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for( DataSnapshot ds: dataSnapshot.getChildren()) {
                    DishModel dish = ds.getValue(DishModel.class);
                    mDishArray.add(dish);
                    Log.d("AAAAAAAAAAAAA", "onDataChange: " + dish);
                }
                progressBar.setVisibility(View.INVISIBLE);
                mDishAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void mapWidgets(View rootView) {
        progressBar = rootView.findViewById(R.id.progress_dish);
        progressBar.setVisibility(View.VISIBLE);
        mDessertRecycleView = rootView.findViewById(R.id.recycler_grid_dish);
        mDishArray = new ArrayList<>();
        mDishAdapter = new DishAdapterRecycler(getActivity(), R.layout.item_dish, mDishArray, 3);
        mDessertRecycleView.setAdapter(mDishAdapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mDessertRecycleView.setLayoutManager(mLayoutManager);

        database = FirebaseDatabase.getInstance();
        mDessertRef = database.getReference().child("DanhSachMonAn").child(Constant.TYPE[3]);
    }


}
