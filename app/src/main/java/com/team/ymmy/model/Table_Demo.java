package com.team.ymmy.model;

import java.io.Serializable;

public class Table_Demo implements Serializable {
    private int mTableImage;
    private int mTableID;

    public Table_Demo() {
    }

    public Table_Demo(int mTableID, int mTableImage) {
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
