package com.team.ymmy.model;

public class DishChooseModel extends DishModel {
    private int counter;
    public DishChooseModel(DishModel d, int counter){
        super(d.getId(), d.getName(), d.getImage(), d.getPrice());
        this.counter = counter;
    }
    public DishChooseModel(int ID, String mName, String mImage, long mPrice , int counter){
        super(ID, mName, mImage, mPrice);
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public String toString() {
        return  "DishChooseModel{" +
                "counter=" + counter +
                '}';
    }
}
