package com.team.ymmy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.ymmy.model.DishModel;
import com.team.ymmy.yummyapp.R;

import java.util.ArrayList;

/**
 * Created by Admin on 9/26/2018.
 */

public class DishAdapter extends BaseAdapter {
    private Context mContext;
    private int mResource;
    private ArrayList<DishModel> mArray;

    public DishAdapter(Context mContext, int mResource, ArrayList<DishModel> mArray) {
        this.mContext = mContext;
        this.mResource = mResource;
        this.mArray = mArray;
    }

    @Override
    public int getCount() {
        return mArray.size();
    }

    @Override
    public Object getItem(int position) {
        return mArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        private ImageView mImage;
        private TextView mPrice;
        private TextView mName;
        public ViewHolder() {
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(this.mResource, null);
            holder.mImage = convertView.findViewById(R.id.img_dish);
            holder.mName = convertView.findViewById(R.id.txt_dish_name);
            holder.mPrice = convertView.findViewById(R.id.txt_dish_price);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DishModel dish = (DishModel) getItem(position);
        holder.mImage.setImageResource(dish.getImage());
        holder.mPrice.setText(dish.getPrice());
        holder.mName.setText(dish.getName());
        return convertView;
    }
}
