package com.team.ymmy.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.team.ymmy.model.DishChoose;

import java.util.ArrayList;

public class DishChooseAdapter extends RecyclerView.Adapter<DishChooseAdapter.ViewHolder> {

    private Context mContext;
    private int mResource;
    private ArrayList<DishChoose> mArrayDish;
    public DishChooseAdapter(Context mContext, int resource, ArrayList<DishChoose> arrayList) {
        this.mContext = mContext;
        this.mResource = resource;
        this.mArrayDish = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mArrayDish.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
