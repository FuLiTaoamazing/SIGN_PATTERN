package com.flt.bridge;

public abstract class Bag {
    protected Color color;

    public Bag(Color color) {
        this.color = color;
    }

    abstract String getName();
}
