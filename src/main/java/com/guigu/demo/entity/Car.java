package com.guigu.demo.entity;

public class Car {
    public Car() {
        System.out.println("car无参构造器");
    }

    public void init(){
        System.out.println("car自定义初始化方法");
    }

    public void destory(){
        System.out.println("car自定义销毁方法");
    }
}
