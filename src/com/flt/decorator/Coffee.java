package com.flt.decorator;
//具体组件的实现类
public class Coffee extends Beverage {
    public Coffee() {
        super.description="this is a coffee";
    }

    @Override
    public int cost() {
        return 15;
    }
}
