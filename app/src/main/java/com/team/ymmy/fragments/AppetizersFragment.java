package com.team.ymmy.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.team.ymmy.adapters.DishAdapter;
import com.team.ymmy.model.DishModel;
import com.team.ymmy.yummyapp.R;

import java.util.ArrayList;

public class AppetizersFragment extends Fragment implements AdapterView.OnItemClickListener {

    private GridView mAppetizersGrid;
    private ArrayList mDishArray;
    private DishAdapter mDishAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_appetizers, container, false);
        mapWidgets(rootView);
        initData();
        handleEvents();
        return rootView;
    }


    private void initData() {
        mDishArray.add(new DishModel("A", R.drawable.background, "1"));
        mDishArray.add(new DishModel("A", R.drawable.background, "1"));
        mDishArray.add(new DishModel("A", R.drawable.background, "1"));
        mDishArray.add(new DishModel("A", R.drawable.background, "1"));
        mDishArray.add(new DishModel("A", R.drawable.background, "1"));
        mDishArray.add(new DishModel("A", R.drawable.background, "1"));
        mDishArray.add(new DishModel("A", R.drawable.background, "1"));

        mDishAdapter.notifyDataSetChanged();
    }

    private void mapWidgets(View rootView) {
        mAppetizersGrid = rootView.findViewById(R.id.grid_appetizers);
        mDishArray = new ArrayList<>();
        mDishAdapter = new DishAdapter(getActivity(), R.layout.item_dish, mDishArray);
        mAppetizersGrid.setAdapter(mDishAdapter);
    }
    private void handleEvents() {
        mAppetizersGrid.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
