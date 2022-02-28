package com.flt.state;

public class Main {
    public static void main(String[] args) {
        MM mm=new MM(new HappyState());
        mm.smile();
        mm.cry();
        mm.say();
    }
}



