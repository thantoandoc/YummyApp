package com.team.ymmy.model;

import java.io.Serializable;

/**
 * Created by Admin on 9/26/2018.
 */

public class DishModel implements Serializable {
    private int id;
    private String name;
    private String image;
    private long price;

    public DishModel() {
    }

    public DishModel(int ID, String mName, String mImage, long mPrice) {
        this.id = ID;
        this.name = mName;
        this.image = mImage;
        this.price = mPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String mName) {
        this.name = mName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String mImage) {
        this.image = mImage;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long mPrice) {
        this.price = mPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
