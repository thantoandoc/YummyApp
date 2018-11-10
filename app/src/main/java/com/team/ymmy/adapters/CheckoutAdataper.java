package com.team.ymmy.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.team.ymmy.model.DishChooseModel;
import com.team.ymmy.yummyapp.R;

import java.util.ArrayList;

/**
 * Created by Admin on 11/10/2018.
 */

public class CheckoutAdataper extends RecyclerView.Adapter<CheckoutAdataper.ViewHolder> {
    private Context mContext;
    private int mResource;
    private ArrayList<DishChooseModel> mArrayDish;

    public CheckoutAdataper(Context mContext, int resource, ArrayList<DishChooseModel> arrayList) {
        this.mContext = mContext;
        this.mResource = resource;
        this.mArrayDish = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        ViewHolder viewHolder = new ViewHolder(rootView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mPrice.setText(String.valueOf(mArrayDish.get(position).getPrice()));
        holder.mName.setText(mArrayDish.get(position).getName());
        holder.mAmounts.setText(String.valueOf(mArrayDish.get(position).getCounter()));
    }

    @Override
    public int getItemCount() {
        return mArrayDish.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mPrice, mName, mAmounts;
        public ViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.name_dish_choose);
            mPrice = itemView.findViewById(R.id.cost_dish_choose);
            mAmounts = itemView.findViewById(R.id.amounts_dish_choose);
        }
    }
}
