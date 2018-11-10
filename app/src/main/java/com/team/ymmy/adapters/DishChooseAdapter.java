package com.team.ymmy.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.team.ymmy.model.DishChooseModel;
import com.team.ymmy.yummyapp.R;

import java.util.ArrayList;

public class DishChooseAdapter extends RecyclerView.Adapter<DishChooseAdapter.ViewHolder> {

    private Context mContext;
    private int mResource;
    private ArrayList<DishChooseModel> mArrayDish;
    public DishChooseAdapter(Context mContext, int resource, ArrayList<DishChooseModel> arrayList) {
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
        Picasso.with(mContext).load(mArrayDish.get(position).getImage()).into(holder.mImage);
        holder.counter.setText(String.valueOf(mArrayDish.get(position).getCounter()));
    }

    @Override
    public int getItemCount() {
        return mArrayDish.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImage;
        private TextView mPrice;
        private TextView mName, counter;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mImage = itemView.findViewById(R.id.img_dish_choose);
            this.mPrice = itemView.findViewById(R.id.cost_dish_choose);
            this.mName = itemView.findViewById(R.id.name_dish_choose);
            this.counter = itemView.findViewById(R.id.amounts_dish_choose);
        }
    }
}
