package com.team.ymmy.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.team.ymmy.adapters.DishAdapter;
import com.team.ymmy.model.DishModel;
import com.team.ymmy.yummyapp.R;

import java.util.ArrayList;

public class DessertsFragment extends Fragment {

    private GridView mDessertGrid;
    private ArrayList mDishArray;
    private DishAdapter mDishAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_desserts, container, false);
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
        mDessertGrid = rootView.findViewById(R.id.grid_desserts);
        mDishArray = new ArrayList<>();
        mDishAdapter = new DishAdapter(getActivity(), R.layout.item_dish, mDishArray);
        mDessertGrid.setAdapter(mDishAdapter);
    }


}
