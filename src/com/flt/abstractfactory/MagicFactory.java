package com.flt.abstractfactory;

public class MagicFactory extends AbstractFactor {
    @Override
    Vehicle createVehicle() {
        return new Broom();
    }

    @Override
    Weapon createWeapon() {
        return new MagicStick();
    }
}
