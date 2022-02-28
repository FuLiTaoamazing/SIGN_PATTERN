package com.flt.bridge;

public class Wallet extends Bag {

    public Wallet(Color color) {
        super(color);
    }

    @Override
    String getName() {
        return color.getColor() + " Wallet";
    }
}
