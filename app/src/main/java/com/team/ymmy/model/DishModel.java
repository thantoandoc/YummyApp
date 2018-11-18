package com.team.ymmy.model;

import java.io.Serializable;


public class DishModel implements Serializable {
    private int id;
    private String name;
    private String image;
    private long price;
    private long startAt;
    private long updateAt;
    private int discount;

    public DishModel() {
    }

    public DishModel(int id, String name, String image, long price, long startAt, long updateAt, int discount) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.startAt = startAt;
        this.updateAt = updateAt;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getStartAt() {
        return startAt;
    }

    public void setStartAt(long startAt) {
        this.startAt = startAt;
    }

    public long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(long updateAt) {
        this.updateAt = updateAt;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
