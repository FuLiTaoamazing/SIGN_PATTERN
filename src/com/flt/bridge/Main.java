package com.flt.bridge;

public class Main {
    public static void main(String[] args) {
        Red red = new Red();
        Yellow yellow = new Yellow();
        Bag redWallet=new Wallet(red);
        Bag yellowWallet=new Wallet(yellow);
        System.out.println(yellowWallet.getName());
        System.out.println(redWallet.getName());
    }
}
