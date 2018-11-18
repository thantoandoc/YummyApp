package com.team.ymmy.model;

public class DishChooseModel extends DishModel {
    private int counter;
    public DishChooseModel(){
        super();
    }
    public DishChooseModel(DishModel d, int counter){
        super(d.getId(), d.getName(), d.getImage(), d.getPrice(), d.getStartAt(), d.getUpdateAt(), d.getDiscount());
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
