package com.flt.decorator;

//顶层的饮料类
public abstract class Beverage {
    //饮料的描述信息
    protected String description="unknown beverage....";


    public String getDescription() {
        return description;
    }


    //计算价钱的方法
    public abstract int cost();
}
