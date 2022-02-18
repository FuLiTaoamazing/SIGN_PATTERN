package com.flt.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {
    public static void main(String[] args) {
        Tank1 tank1 = new Tank1();


        Moveable1 o = (Moveable1) Proxy.newProxyInstance(Tank1.class.getClassLoader()
                , Tank1.class.getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("proxy method name:" + method.getName() + " start");
                        Object o = method.invoke(tank1, args);
                        System.out.println("proxy method name " + method.getName() + " end");
                        return o;
                    }
                });
        o.move();
    }
}

interface Moveable1 {
    void move();
}

class Tank1 implements Moveable1 {
    @Override
    public void move() {
        System.out.println("tank moving claclacla....");
    }
}
