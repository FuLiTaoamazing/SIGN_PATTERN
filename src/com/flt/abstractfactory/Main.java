package com.flt.abstractfactory;

public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.go();
        Ak47 ak47 = new Ak47();
        ak47.shoot();
    }
}
