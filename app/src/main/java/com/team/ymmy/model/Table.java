package com.team.ymmy.model;

import java.io.Serializable;

/**
 * Created by Admin on 10/1/2018.
 */

public class Table implements Serializable {
    private int mID;
    private boolean mStatus;
    private ITable mTable;

    public Table(int mID, boolean mStatus) {
        this.mID = mID;
        this.mStatus = mStatus;
        if(mStatus) {
            mTable = new TableHasCustomer();
        } else {
            mTable = new TableHasnotCustomer();
        }

    }

    public int getID() {
        return mID;
    }

    public void setID(int mID) {
        this.mID = mID;
    }

    public int getImage() {
        return mTable.getImage();
    }

    public void setStatus(boolean mStatus) {
        this.mStatus = mStatus;
        if(this.mStatus) {
            mTable = new TableHasCustomer();
        } else {
            mTable = new TableHasnotCustomer();
        }
    }
}
