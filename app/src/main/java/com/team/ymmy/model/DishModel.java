package com.team.ymmy.model;

import java.io.Serializable;

/**
 * Created by Admin on 9/26/2018.
 */

public class DishModel implements Serializable {
    private String mName;
    private int mImage;
    private String mPrice;

    public DishModel() {
    }

    public DishModel(String mName, int mImage, String mPrice) {
        this.mName = mName;
        this.mImage = mImage;
        this.mPrice = mPrice;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int mImage) {
        this.mImage = mImage;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
