package com.flt.proxy.where;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static java.lang.System.out;

public class DynamicProxyDemo {
    public static void main(String[] args) {
        Moveable tank = new Tank();
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        Moveable proxy = (Moveable) Proxy.newProxyInstance(Tank.class.getClassLoader(), Tank.class.getInterfaces(), new TimeHandler(tank));
        proxy.move();
    }

}

class TimeHandler implements InvocationHandler {
    Moveable moveable;

    public TimeHandler(Moveable moveable) {
        this.moveable = moveable;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        out.println("proxy method name:" + method.getName() + " start!");
        Object o = method.invoke(moveable, args);
        out.println("proxy method name:" + method.getName() + " end!");
        return o;
    }
}

interface Moveable {
    void move();
}

class Tank implements Moveable {
    @Override
    public void move() {
        out.println("tank is staring claclacla....");
    }
}
