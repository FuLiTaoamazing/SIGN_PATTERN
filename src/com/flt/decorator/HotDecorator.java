package com.flt.decorator;

public class HotDecorator extends AbstractDecorator {

    public HotDecorator(Beverage concreteComponent) {
        super(concreteComponent);
    }

    @Override
    public String getDescription() {
        return super.concreteComponent.description + " hot";
    }

    @Override
    public int cost() {
        return super.concreteComponent.cost() + 3;
    }
}
