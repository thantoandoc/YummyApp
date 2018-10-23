package com.team.ymmy.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.team.ymmy.adapters.DishAdapterRecycler;
import com.team.ymmy.model.DishModel;
import com.team.ymmy.yummyapp.R;

import java.util.ArrayList;


public class CheesesFragment extends Fragment {

    private RecyclerView mCheesesGrid;
    private ArrayList mDishArray;
    private DishAdapterRecycler mDishAdapter;
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


        mDishAdapter.notifyDataSetChanged();
    }

    private void mapWidgets(View rootView) {
        mCheesesGrid = rootView.findViewById(R.id.recycler_grid_dish);
        mDishArray = new ArrayList<>();
        mDishAdapter = new DishAdapterRecycler(getActivity(), R.layout.item_dish, mDishArray);
        mCheesesGrid.setAdapter(mDishAdapter);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 2);
        mCheesesGrid.setLayoutManager(manager);
    }



}
