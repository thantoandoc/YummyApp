package com.team.ymmy.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.ymmy.constant.Constant;
import com.team.ymmy.model.Table;
import com.team.ymmy.yummyapp.CatalogActivity;
import com.team.ymmy.yummyapp.R;

import java.util.ArrayList;

/**
 * Created by Admin on 9/29/2018.
 */

public class TableAdapter extends RecyclerView.Adapter <TableAdapter.ViewHolder> {
    private Context mContext;
    private int mResource;
    private ArrayList<Table> mTables;
    public TableAdapter(Context context, int resource, ArrayList<Table> tables){
        this.mContext = context;
        this.mResource = resource;
        this.mTables = tables;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mRootView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        ViewHolder mViewHolder = new ViewHolder(mRootView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imgTableImage.setImageResource(mTables.get(position).getImage());
        holder.txtTableID.setText(String.valueOf(mTables.get(position).getID()));
    }


    @Override
    public int getItemCount() {
        return mTables.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtTableID;
        ImageView imgTableImage;
        View parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTableID = itemView.findViewById(R.id.txt_table);
            imgTableImage = itemView.findViewById(R.id.img_table);
            parentLayout = itemView.findViewById(R.id.parent_layout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, CatalogActivity.class);
                    intent.putExtra(Constant.TABLE_NAME, getAdapterPosition());
                    mContext.startActivity(intent);
                }
            });
        }
    }
}