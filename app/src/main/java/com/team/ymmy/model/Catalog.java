package com.team.ymmy.model;

import java.io.Serializable;

/**
 * Created by Admin on 9/22/2018.
 */

public class Catalog implements Serializable {
    private String mTen;
    private Integer mHinh;

    public Catalog(String mTen, Integer mHinh) {
        this.mTen = mTen;
        this.mHinh = mHinh;
    }

    public String getTen() {
        return mTen;
    }

    public void setTen(String mTen) {
        this.mTen = mTen;
    }

    public Integer getHinh() {
        return mHinh;
    }

    public void setHinh(Integer mHinh) {
        this.mHinh = mHinh;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
