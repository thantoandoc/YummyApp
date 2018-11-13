package com.team.ymmy.adapters;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.team.ymmy.model.DishChooseModel;
import com.team.ymmy.model.DishModel;
import com.team.ymmy.yummyapp.CatalogActivity;
import com.team.ymmy.yummyapp.R;

import java.util.ArrayList;

public class DishAdapterRecycler  extends RecyclerView.Adapter<DishAdapterRecycler.ViewHolder>{
    private Context mContext;
    private int mResource;
    private ArrayList<DishModel> mArray;
    private int TYPE;

    public DishAdapterRecycler(Context mContext, int mResource, ArrayList<DishModel> mArray, int TYPE) {
        this.mContext = mContext;
        this.mResource = mResource;
        this.mArray = mArray;
        this.TYPE = TYPE;
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

        Picasso.with(mContext).load(mArray.get(position).getImage()).into(holder.mImage);
        int w = (int) convertDpToPixel(1, mContext);
        int width = mContext.getResources().getDisplayMetrics().widthPixels / 2 - 4 * w;
        int height = (int) ((mContext.getResources().getDisplayMetrics().widthPixels / 2 - 4 * w) * 1.1);
        CardView.LayoutParams layoutParams = new FrameLayout.LayoutParams(width, height);
        layoutParams.setMargins(w, w, w, w);
        holder.mParentLayout.setLayoutParams(layoutParams);
    }
    private static float convertPixelsToDp(float px, Context context){
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
    private static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
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
        public ViewHolder(final View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.img_dish);
            mName = itemView.findViewById(R.id.txt_dish_name);
            mPrice = itemView.findViewById(R.id.txt_dish_price);
            mParentLayout = itemView.findViewById(R.id.dish_layout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext, R.style.DialogTransparentStyle);
                    View mRootView = LayoutInflater.from(mContext).inflate(R.layout.dialog_choose, null);
                    ImageView imgDish = mRootView.findViewById(R.id.img_dish_dialog);
                    TextView txt_Name = mRootView.findViewById(R.id.txt_dish_name_dialog);
                    TextView txt_Price = mRootView.findViewById(R.id.txt_dish_price_dialog);
                    final TextView mAmounts = mRootView.findViewById(R.id.amounts);
                    Button mCancel = mRootView.findViewById(R.id.btn_cancel);
                    Button mOK = mRootView.findViewById(R.id.btn_ok);
                    Button btn_Add = mRootView.findViewById(R.id.btn_add);
                    Button btn_Sub = mRootView.findViewById(R.id.btn_sub);

                    Picasso.with(mContext).load(mArray.get(getAdapterPosition()).getImage()).into(imgDish);
                    txt_Name.setText(mArray.get(getAdapterPosition()).getName());
                    txt_Price.setText(String.valueOf(mArray.get(getAdapterPosition()).getPrice()));
                    mAmounts.setText("1");

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
                            CatalogActivity.mDSMonAn.add(new DishChooseModel(mArray.get(getAdapterPosition()), soLuong));
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
            });
        }
    }
}
