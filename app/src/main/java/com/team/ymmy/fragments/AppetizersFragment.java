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
import android.widget.GridView;

import com.team.ymmy.adapters.DishAdapter;
import com.team.ymmy.model.DishModel;
import com.team.ymmy.yummyapp.R;
import com.team.ymmy.yummyapp.SideMenu;

import java.util.ArrayList;

public class AppetizersFragment extends Fragment {

    private GridView mAppetizersGrid;
    private ArrayList mDishArray;
    private DishAdapter mDishAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_appetizers, container, false);
        mapWidgets(rootView);
        initData();
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


}
