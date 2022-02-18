package com.flt.abstractfactory;

public class ModernFactory extends AbstractFactor {
    @Override
    Vehicle createVehicle() {
        return new Car();
    }

    @Override
    Weapon createWeapon() {
        return new Ak47();
    }
}
