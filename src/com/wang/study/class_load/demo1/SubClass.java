package com.wang.study.class_load.demo1;

public class SubClass extends SuperClass {
    static int a;

    static {
        System.out.println("SubClass 静态代码块");
    }

    public SubClass() {
        System.out.println("SubClass 构造方法");
    }
}
