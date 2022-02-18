package com.flt.decorator;

//抽象的装饰器父类
public abstract class AbstractDecorator extends Beverage {
    protected Beverage concreteComponent;


    public AbstractDecorator(Beverage concreteComponent) {
        this.concreteComponent = concreteComponent;
    }

    //要给饮料加上新的描述 所以得把这个重写了;
    public abstract String getDescription();
}
