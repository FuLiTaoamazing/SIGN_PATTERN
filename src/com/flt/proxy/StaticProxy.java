package com.flt.proxy;

public class StaticProxy {
    public static void main(String[] args) {
        Moveable tank = new Tank();
        tank.move();
        Moveable proxy = new TankProxy(tank);
        System.out.println("-------------");
        proxy.move();
    }


}

interface Moveable {
    void move();
}

class Tank implements Moveable {
    @Override
    public void move() {
        System.out.println("tank is move....");
    }
}

class TankProxy implements Moveable {
    private Moveable moveable;

    public TankProxy(Moveable moveable) {
        this.moveable = moveable;
    }

    @Override
    public void move() {
        long start = System.currentTimeMillis();
        moveable.move();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("move time:" + (end - start)+"");
    }
}
