package com.flt.state;

import javax.swing.plaf.nimbus.State;

public class MM {

    private MMState concurrentState;

    public MM(MMState concurrentState) {
        this.concurrentState = concurrentState;
    }

    public void smile() {
        concurrentState.smile();
    }

    public void cry() {
        concurrentState.cry();
    }

    public void say() {
        concurrentState.say();
    }
}