package com.team.ymmy.model;

import java.io.Serializable;

public class Table implements Serializable {
    private int mTableImage;
    private int mTableID;

    public Table() {
    }

    public Table(int mTableID, int mTableImage) {
        this.mTableImage = mTableImage;
        this.mTableID = mTableID;
    }

    public int getTableImage() {
        return mTableImage;
    }

    public void setTableImage(int mTableImage) {
        this.mTableImage = mTableImage;
    }

    public int getTableID() {
        return mTableID;
    }

    public void setTableID(int mTableID) {
        this.mTableID = mTableID;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
