package com.flt.state;

public class SadState extends MMState{
    @Override
    public void smile() {
        System.out.println("笑不出来");
    }

    @Override
    public void cry() {
        System.out.println("哭的半死");
    }

    @Override
    public void say() {
        System.out.println("不想说话");
    }
}
