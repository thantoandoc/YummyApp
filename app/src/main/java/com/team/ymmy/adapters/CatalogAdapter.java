package com.team.ymmy.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.team.ymmy.model.Catalog;
import com.team.ymmy.yummyapp.R;

import java.util.ArrayList;

/**
 * Created by Admin on 9/22/2018.
 */

public class CatalogAdapter extends BaseAdapter {
    private Context context;
    private   int resource;
    private ArrayList<Catalog> arrayListCatalog;

    public CatalogAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Catalog> arrayListCatalog) {
        this.context = context;
        this.resource = resource;
        this.arrayListCatalog = arrayListCatalog;
    }
    @Override
    public int getCount() {
        return arrayListCatalog.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListCatalog.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView txtCatalogName;
        View imgCatalogImage;
        public ViewHolder(){}
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(this.resource, null);
            holder.txtCatalogName = (TextView) convertView.findViewById(R.id.txt_catalog_name);
            holder.imgCatalogImage = (View) convertView.findViewById(R.id.img_catalog);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Catalog catalog = (Catalog) getItem(position);
        holder.imgCatalogImage.setBackgroundResource(catalog.getHinh());
        holder.txtCatalogName.setText(catalog.getTen());

        return convertView;
    }
}
