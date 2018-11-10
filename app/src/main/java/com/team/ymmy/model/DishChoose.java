package com.team.ymmy.model;

import java.io.Serializable;

/**
 * Created by Admin on 11/4/2018.
 */

public class DishChoose implements Serializable{
    private int id;
    private int sl;
    private int type;

    public DishChoose() {
    }

    public DishChoose(int id, int sl, int type) {
        this.id = id;
        this.sl = sl;
        this.type = type;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getSL() {
        return sl;
    }

    public void setSL(int sl) {
        this.sl = sl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
