package com.flt.abstractfactory;

import com.flt.factormethod.Moveable;

public class Car  extends Vehicle {
    @Override
    public void go() {
        System.out.println("Car go wuwuwuwwu...");
    }
}
