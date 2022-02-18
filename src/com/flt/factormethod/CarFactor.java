package com.flt.factormethod;
//工厂方法来创建对象
public class CarFactor {
    public Car createCar() {
        //before process  可以做日志和权限管理
        System.out.println("a car create....");
        return new Car();
    }
}
