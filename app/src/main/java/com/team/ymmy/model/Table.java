package com.team.ymmy.model;

import com.team.ymmy.interfaces.ITable;

/**
 * Created by Admin on 10/1/2018.
 */

public class Table  {
    private int id;
    private boolean status;
    private ITable table;

    public Table() {

    }

    public Table(int mID, boolean mStatus) {
        this.id = mID;
        this.status = mStatus;
        if(mStatus) {
            table = new TableHasCustomer();
        } else {
            table = new TableHasnotCustomer();
        }

    }

    public int getID() {
        return id;
    }

    public void setID(int mID) {
        this.id = mID;
    }

    public int getImage() {
        return table.getImage();
    }

    public void setStatus(boolean mStatus) {
        this.status = mStatus;
        if(this.status) {
            table = new TableHasCustomer();
        } else {
            table = new TableHasnotCustomer();
        }
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.id + " " + this.status;
    }
}
