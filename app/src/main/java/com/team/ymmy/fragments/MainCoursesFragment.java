package com.team.ymmy.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.team.ymmy.adapters.DishAdapterRecycler;
import com.team.ymmy.model.DishModel;
import com.team.ymmy.yummyapp.R;

import java.util.ArrayList;


public class MainCoursesFragment extends Fragment {

    private RecyclerView mMainCourseGrid;
    private ArrayList mDishArray;
    private DishAdapterRecycler mDishAdapter;
    private FirebaseDatabase database;
    private DatabaseReference mMainRef;
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
        mMainRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for( DataSnapshot ds: dataSnapshot.getChildren()) {
                    DishModel dish = ds.getValue(DishModel.class);
                    mDishArray.add(dish);
                    Log.d("AAAAAAAAAAAAA", "onDataChange: " + dish);
                }
                mDishAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void mapWidgets(View rootView) {
        mMainCourseGrid = rootView.findViewById(R.id.recycler_grid_dish);
        mDishArray = new ArrayList<>();
        mDishAdapter = new DishAdapterRecycler(getActivity(), R.layout.item_dish, mDishArray);
        mMainCourseGrid.setAdapter(mDishAdapter);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 2);
        mMainCourseGrid.setLayoutManager(manager);

        database = FirebaseDatabase.getInstance();
        mMainRef = database.getReference().child("DanhSachMonAn").child("Main_Courses");
    }


}
