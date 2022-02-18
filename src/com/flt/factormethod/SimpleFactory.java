package com.flt.factormethod;
//简单工程代码扩展性较差 没增加一个新的设备都要增加代码
public class SimpleFactory {
    public Car createCar() {
        //before process
        return new Car();
    }

    public Plane createPlane() {
        //before process
        return new Plane();
    }
}
