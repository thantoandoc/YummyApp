package com.team.ymmy.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.team.ymmy.adapters.DishAdapter;
import com.team.ymmy.model.DishModel;
import com.team.ymmy.yummyapp.R;

import java.util.ArrayList;

public class ColdStarterFragment extends Fragment implements AdapterView.OnItemClickListener {

    private GridView mColdStartGrid;
    private ArrayList mDishArray;
    private DishAdapter mDishAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cold_starter, container, false);
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
        mColdStartGrid = rootView.findViewById(R.id.grid_cold_start);
        mDishArray = new ArrayList<>();
        mDishAdapter = new DishAdapter(getActivity(), R.layout.item_dish, mDishArray);
        mColdStartGrid.setAdapter(mDishAdapter);
    }
    private void handleEvents() {
        mColdStartGrid.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        View mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_choose, null);
        Button mCancel = mRootView.findViewById(R.id.btn_cancel);
        Button mOK = mRootView.findViewById(R.id.btn_ok);
        mBuilder.setView(mRootView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }
}
