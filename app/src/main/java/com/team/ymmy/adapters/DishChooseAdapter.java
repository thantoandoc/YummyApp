package com.team.ymmy.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.team.ymmy.model.DishChooseModel;
import com.team.ymmy.yummyapp.CatalogActivity;
import com.team.ymmy.yummyapp.ListDishChooseActivity;
import com.team.ymmy.yummyapp.R;

import java.util.ArrayList;
import java.util.Date;

public class DishChooseAdapter extends RecyclerView.Adapter<DishChooseAdapter.ViewHolder> {

    private Context mContext;
    private int mResource;
    private ArrayList<DishChooseModel> mArrayDish;
    private TextView total;
    public DishChooseAdapter(Context mContext, int resource, ArrayList<DishChooseModel> arrayList, TextView total) {
        this.mContext = mContext;
        this.mResource = resource;
        this.mArrayDish = arrayList;
        this.total = total;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        ViewHolder viewHolder = new ViewHolder(rootView);
        return viewHolder;
    }

    private boolean isNew(DishChooseModel dish){
        final long newTime = 14*24*3600*1000;
        long timeStamp = new Date().getTime();
        if(timeStamp - dish.getStartAt() < newTime) return true;
        return false;
    }

    private boolean isSale(DishChooseModel dish){
        return (dish.getDiscount() > 0);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(mArrayDish.get(position).getDiscount() > 0) {
            holder.mOrginPrice.setVisibility(View.VISIBLE);
            holder.mOrginPrice.setText(String.valueOf(mArrayDish.get(position).getPrice()));
            holder.mPrice.setText(String.valueOf((int) (mArrayDish.get(position).getPrice() - mArrayDish.get(position).getPrice() * 1.0 * (mArrayDish.get(position).getDiscount()) / 100)  ));
        }else{
            holder.mOrginPrice.setVisibility(View.INVISIBLE);
            holder.mPrice.setText(String.valueOf(mArrayDish.get(position).getPrice()));
        }
        holder.mName.setText(mArrayDish.get(position).getName());
        Picasso.with(mContext).load(mArrayDish.get(position).getImage()).into(holder.mImage);
        if(isNew(mArrayDish.get(position))){
            holder.mImageNew.setVisibility(View.VISIBLE);
        }else{
            holder.mImageNew.setVisibility(View.INVISIBLE);
        }
        if(isSale(mArrayDish.get(position))){
            holder.mImageSale.setVisibility(View.VISIBLE);
            holder.mDiscount.setVisibility(View.VISIBLE);
            StringBuffer str = new StringBuffer("-").append(mArrayDish.get(position).getDiscount()).append("%");
            holder.mDiscount.setText(str.toString());
        }else{
            holder.mImageSale.setVisibility(View.INVISIBLE);
            holder.mDiscount.setVisibility(View.INVISIBLE);
        }
        holder.counter.setText(String.valueOf(mArrayDish.get(position).getCounter()));
        holder.counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(position);
            }
        });
    }
    private void showDialog(final int position){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext, R.style.DialogTransparentStyle);
        View mRootView = LayoutInflater.from(mContext).inflate(R.layout.dialog_choose, null);
        ImageView imgDish = mRootView.findViewById(R.id.img_dish_dialog);
        ImageView imgNewDialog = mRootView.findViewById(R.id.img_new_dialog);
        ImageView imgSaleDiale = mRootView.findViewById(R.id.img_sale_off_dialog);
        TextView txtDiscountDialog = mRootView.findViewById(R.id.txt_discount_dialog);
        TextView txtPriceOriginDialog = mRootView.findViewById(R.id.txt_dish_price_origin_dialog);
        TextView txt_Name = mRootView.findViewById(R.id.txt_dish_name_dialog);
        TextView txt_Price = mRootView.findViewById(R.id.txt_dish_price_dialog);
        final TextView mAmounts = mRootView.findViewById(R.id.amounts);
        Button mCancel = mRootView.findViewById(R.id.btn_cancel);
        Button mOK = mRootView.findViewById(R.id.btn_ok);
        Button btn_Add = mRootView.findViewById(R.id.btn_add);
        Button btn_Sub = mRootView.findViewById(R.id.btn_sub);

        Picasso.with(mContext).load(mArrayDish.get(position).getImage()).into(imgDish);
        if(isNew(mArrayDish.get(position))){
            imgNewDialog.setVisibility(View.VISIBLE);
        }
        if(isSale(mArrayDish.get(position))){
            imgSaleDiale.setVisibility(View.VISIBLE);
            StringBuffer strD = new StringBuffer("-").append(mArrayDish.get(position).getDiscount()).append("%");
            txtDiscountDialog.setText(strD.toString());
        }
        txt_Name.setText(mArrayDish.get(position).getName());
        if(mArrayDish.get(position).getDiscount() > 0){
            txtPriceOriginDialog.setVisibility(View.VISIBLE);
            txtPriceOriginDialog.setText(String.valueOf(mArrayDish.get(position).getPrice()));
            txt_Price.setText(String.valueOf((int)(mArrayDish.get(position).getPrice() * 1.0 * (100 - mArrayDish.get(position).getDiscount())) / 100));
        }else{
            txt_Price.setText(String.valueOf(mArrayDish.get(position).getPrice()));
        }
        mAmounts.setText(String.valueOf(mArrayDish.get(position).getCounter()));

        mBuilder.setView(mRootView);

        final AlertDialog dialog = mBuilder.create();
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        mOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soLuong = Integer.parseInt(mAmounts.getText().toString());
                mArrayDish.get(position).setCounter(soLuong);
                total.setText(String.valueOf(getTotalPrice()));
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amounts = Integer.parseInt(mAmounts.getText().toString().trim());
                mAmounts.setText(String.valueOf(amounts + 1));
            }
        });
        btn_Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amounts = Integer.parseInt(mAmounts.getText().toString().trim());
                if(amounts > 1){
                    mAmounts.setText(String.valueOf(amounts - 1));
                }
            }
        });

        dialog.show();
    }
    @Override
    public int getItemCount() {
        return mArrayDish.size();
    }

    public void removeItem(int pos){
        mArrayDish.remove(pos);
        total.setText(String.valueOf(getTotalPrice()));
        notifyItemRemoved(pos);
        notifyItemRangeChanged(pos, mArrayDish.size());
    }
    public void restoreItem(DishChooseModel item, int position ){
        mArrayDish.add(position,item);
        total.setText(String.valueOf(getTotalPrice()));
        notifyItemInserted(position);

    }
    public int getTotalPrice() {
        int total = 0;
        for (int i = 0; i < mArrayDish.size(); i++){
            if(mArrayDish.get(i).getDiscount() > 0){
                total += ((int) (mArrayDish.get(i).getPrice() -  mArrayDish.get(i).getPrice() *  mArrayDish.get(i).getDiscount() / 100)) * mArrayDish.get(i).getCounter();
            }else{
                total += mArrayDish.get(i).getPrice() * mArrayDish.get(i).getCounter();
            }
        }
        return total;
    }

    public DishChooseModel getItem(int position){
        return mArrayDish.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImage, mImageNew, mImageSale;
        private TextView mPrice, mDiscount, mOrginPrice;
        private TextView mName, counter;

        private ViewHolder(View itemView) {
            super(itemView);
            this.mImage = itemView.findViewById(R.id.img_dish_choose);
            this.mImageNew = itemView.findViewById(R.id.img_new_choose);
            this.mImageSale = itemView.findViewById(R.id.img_sale_choose);
            this.mPrice = itemView.findViewById(R.id.cost_dish_choose);
            this.mOrginPrice = itemView.findViewById(R.id.origin_cost_dish_choose);
            this.mDiscount = itemView.findViewById(R.id.txt_discount_choose);
            this.mName = itemView.findViewById(R.id.name_dish_choose);
            this.counter = itemView.findViewById(R.id.amounts_dish_choose);
        }
    }
}
