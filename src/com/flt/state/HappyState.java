package com.flt.state;

public class HappyState extends MMState{
    @Override
    public void smile() {
        System.out.println("开怀大笑");
    }

    @Override
    public void cry() {
        System.out.println("笑出眼泪");
    }

    @Override
    public void say() {
        System.out.println("滔滔不绝");
    }
}
