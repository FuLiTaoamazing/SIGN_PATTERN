package com.flt.decorator;

import java.net.JarURLConnection;

public class Main {
    public static void main(String[] args) {
        Beverage coffee = new Coffee();

        System.out.println(coffee.getDescription() + "::::" + coffee.cost());
        Beverage juice = new Juice();
        System.out.println(juice.getDescription() + "::::" + juice.cost());

        Beverage iceCoffee = new IceDecorator(coffee);
        System.out.println(iceCoffee.getDescription()+"::::"+iceCoffee.cost());
        Beverage iceJuice = new IceDecorator(juice);
        System.out.println(iceJuice.getDescription()+"::::"+iceJuice.cost());

        Beverage hotCoffee = new HotDecorator(coffee);
        System.out.println(hotCoffee.getDescription()+"::::"+hotCoffee.cost());
        Beverage hotJuice = new HotDecorator(juice);
        System.out.println(hotJuice.getDescription()+"::::"+hotJuice.cost());
    }
}
