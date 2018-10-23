package com.team.ymmy.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.ymmy.yummyapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DishDialogFragment extends DialogFragment {

    private ImageView imgDish;
    private TextView txt_Name;
    private TextView txt_Price ;
    private Button mCancel;
    private Button mOK;
    public DishDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_choose, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapWidgets(view);
    }

    private void mapWidgets(View view) {
        imgDish = view.findViewById(R.id.img_dish_dialog);
        txt_Name = view.findViewById(R.id.txt_dish_name_dialog);
        txt_Price = view.findViewById(R.id.txt_dish_price_dialog);
        mCancel = view.findViewById(R.id.btn_cancel);
        mOK = view.findViewById(R.id.btn_ok);
    }
}
