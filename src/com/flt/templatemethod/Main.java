package com.flt.templatemethod;

public class Main {
    public static void main(String[] args) {
        Template template=new Concrete1();
        template.concreteMethod();
    }
}

abstract class Template{
    public void concreteMethod(){
        method1();
        method2();
        method3();
    }

    public abstract void method1();
    public abstract void method2();
    public abstract void method3();
}

class Concrete1 extends Template{
    @Override
    public void method1() {
        System.out.println("Concrete1 method1");
    }

    @Override
    public void method2() {
        System.out.println("Concrete1 method2");
    }

    @Override
    public void method3() {
        System.out.println("Concrete1 method3");
    }
}