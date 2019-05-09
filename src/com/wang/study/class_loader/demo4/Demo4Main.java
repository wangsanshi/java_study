package com.wang.study.class_loader.demo4;

/**
 * 本示例的运行结果如下：
 *
 * 2
 * 3
 * a = 110 , b = 0
 * 1
 * 4
 *
 * -------------------------------------------------------------------------------------
 *
 * 分析：
 *
 * 因为要执行{@link Demo4Main#main(String[])}方法，需加载类{@link Demo4Main}。
 *
 * 1、在加载类的准备阶段，先对静态变量(即类变量)进行初始化，此时:
 *   static Demo4Main demo4 = null;
 *   static int b = 0;
 *
 * 2、在加载类的初始化阶段，会对静态变量进行赋值，所以先执行：
 *   static Demo4Main demo4 = new Demo4Main();
 *
 *   在创建一个对象时，会先执行普通代码块(c)且对成员变量赋值(e)，再执行构造方法(d)，所以将打印如下内容:
 *   2
 *   3
 *   a = 110 , b = 0
 *
 *   然后再执行:
 *   static int b = 112;
 *
 *   最后再执行{@link Demo4Main#main(String[])}中的{@link Demo4Main#staticFunction()}方法，
 *   所以最后打印：
 *   4
 *
 */
@SuppressWarnings("all")
public class Demo4Main {

    static Demo4Main demo4 = new Demo4Main();                            //(a)

    static {
        System.out.println("1");                                         //(b)
    }

    {
        System.out.println("2");                                         //(c)
    }

    public Demo4Main() {                                                 //(d)
        System.out.println("3");
        System.out.println("a = " + a + " , b = " + b);
    }

    int a = 110;                                                         //(e)
    static int b = 112;

    public static void staticFunction(){
        System.out.println("4");                                         //(f)
    }

    public static void main(String[] args) {
        staticFunction();
    }

}
