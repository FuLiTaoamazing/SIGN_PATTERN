package com.flt.decorator;
//具体组件的实现类
public class Juice extends Beverage {
    public Juice() {
        super.description="this is a juice";
    }

    @Override
    public int cost() {
        return 5;
    }
}
