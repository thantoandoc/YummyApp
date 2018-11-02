package com.team.ymmy.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.team.ymmy.async.ImageAsync;
import com.team.ymmy.model.DishModel;
import com.team.ymmy.yummyapp.R;

import java.util.ArrayList;

public class DishAdapterRecycler  extends RecyclerView.Adapter<DishAdapterRecycler.ViewHolder>{
    private Context mContext;
    private int mResource;
    private ArrayList<DishModel> mArray;

    public DishAdapterRecycler(Context mContext, int mResource, ArrayList<DishModel> mArray) {
        this.mContext = mContext;
        this.mResource = mResource;
        this.mArray = mArray;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        ViewHolder viewHolder = new ViewHolder(rootView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mPrice.setText(String.valueOf(mArray.get(position).getPrice()));
        holder.mName.setText(mArray.get(position).getName());
        new ImageAsync(mContext, holder.mImage).execute(mArray.get(position).getImage());
        int width = mContext.getResources().getDisplayMetrics().widthPixels / 2 - 8;
        int height =  mContext.getResources().getDisplayMetrics().widthPixels / 2;
        CardView.LayoutParams layoutParams = new FrameLayout.LayoutParams(width, height);
        holder.mParentLayout.setLayoutParams(layoutParams);


        holder.mParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
                View mRootView = LayoutInflater.from(mContext).inflate(R.layout.dialog_choose, null);
                ImageView imgDish = mRootView.findViewById(R.id.img_dish_dialog);
                TextView txt_Name = mRootView.findViewById(R.id.txt_dish_name_dialog);
                TextView txt_Price = mRootView.findViewById(R.id.txt_dish_price_dialog);
                Button mCancel = mRootView.findViewById(R.id.btn_cancel);
                Button mOK = mRootView.findViewById(R.id.btn_ok);

                new ImageAsync(mContext, imgDish).execute(mArray.get(holder.getAdapterPosition()).getImage());
                txt_Name.setText(mArray.get(holder.getAdapterPosition()).getName());
                txt_Price.setText(String.valueOf(mArray.get(holder.getAdapterPosition()).getPrice()));


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

                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImage;
        private TextView mPrice;
        private TextView mName;
        private CardView mParentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.img_dish);
            mName = itemView.findViewById(R.id.txt_dish_name);
            mPrice = itemView.findViewById(R.id.txt_dish_price);
            mParentLayout = itemView.findViewById(R.id.dish_layout);
        }
    }
}
