package com.flt.decorator;

public class IceDecorator extends AbstractDecorator{

    public IceDecorator(Beverage concreteComponent) {
        super(concreteComponent);
    }
    @Override
    public String getDescription() {
        return super.concreteComponent.description+" append ice";
    }

    @Override
    public int cost() {
        return super.concreteComponent.cost()+1;
    }
}
