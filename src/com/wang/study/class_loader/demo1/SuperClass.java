package com.wang.study.class_loader.demo1;

public class SuperClass  extends SSClass{
    public static int value = 123;

    static {
        System.out.println("SuperClass 静态代码块");
    }

    public SuperClass() {
        System.out.println("SuperClass 构造方法");
    }
}
