package com.team.ymmy.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.ymmy.model.Table;
import com.team.ymmy.yummyapp.R;

import java.util.ArrayList;

/**
 * Created by Admin on 9/21/2018.
 */

public class TableAdapter extends BaseAdapter {
    private Context context;
    private   int resource;
    private ArrayList<Table> arrayListTable;

    public TableAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Table> arrayListTable) {
        this.context = context;
        this.resource = resource;
        this.arrayListTable = arrayListTable;
    }

    @Override
    public int getCount() {
        return arrayListTable.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListTable.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView txtTableID;
        ImageView imgTableImage;
        public ViewHolder(){}
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(this.resource, null);
            holder.txtTableID = (TextView) convertView.findViewById(R.id.txt_table);
            holder.imgTableImage = (ImageView) convertView.findViewById(R.id.img_table);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Table table = (Table) getItem(position);
        holder.imgTableImage.setImageResource(table.getTableImage());
        holder.txtTableID.setText(table.getTableID());

        return convertView;
    }
}
