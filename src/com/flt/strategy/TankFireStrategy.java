package com.flt.strategy;

public class TankFireStrategy implements FireStrategy {
    @Override
    public String getSampleName() {
        return "tank";
    }

    @Override
    public void fire() {
        System.out.println("坦克开火咯");
    }
}
