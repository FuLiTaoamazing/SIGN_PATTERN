package com.flt.bridge;

public class Handbag extends Bag {

    public Handbag(Color color) {
        super(color);
    }

    @Override
    String getName() {
        return color.getColor() + " Handbag";
    }
}
