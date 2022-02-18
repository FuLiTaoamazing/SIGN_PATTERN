package com.flt.abstractfactory;
//可以生产一系列不同产品的 抽象工厂
public abstract class AbstractFactor {
    //生产座驾
     abstract Vehicle createVehicle();

    //生产武器
     abstract Weapon createWeapon();
}
