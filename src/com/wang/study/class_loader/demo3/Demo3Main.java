package com.wang.study.class_loader.demo3;

/**
 * 通过定义数组来引用类，和调用类中常量来引用类(常量在编译阶段会存入调用类的常量池中，本质上并没有直接引用到定义常量的类)
 * ，均不会触发类的初始化，所以本示例的输出结果为空。
 */
@SuppressWarnings("all")
public class Demo3Main {
    private static class Test{

        static {
            System.out.println("Test 静态代码块");
        }

        public static final String HELLO = "hello world";
    }

    public static void main(String [] args){
        Test [] tests = new Test[10];
        System.out.println(Test.HELLO);
    }

}
