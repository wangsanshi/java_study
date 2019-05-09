package com.wang.study.class_load.demo2;

/**
 * 测试多线程同时去加载类的情况
 *
 * ----------------------------------------------------------
 *
 * 本示例会打印如下内容(程序永远不会停止)：
 *
 * thread-1 start.
 * thread-2 start.
 * thread-1执行 Loop 类的静态代码块
 *
 * ----------------------------------------------------------
 *
 * 在thread-1开始加载Loop的时候，thread-2处于阻塞状态，说明同一时刻只能有一个线程
 * 来进行类的加载。
 * 在本示例中，thread-1在加载Loop的过程中，进行到Loop初始化的阶段时，会执行Loop的
 * <cinit>方法，该方法会顺序执行用static修饰的变量以及代码块，然后在静态代码块中陷入
 * 死循环，永远也不会结束，thread-2也永远处于阻塞状态。
 *
 */
@SuppressWarnings("all")
public class Demo2Main {

    public static class Loop{
        static {
            if(true){
                System.out.println(Thread.currentThread().getName() + "执行 Loop 类的静态代码块");

                while (true){
                    //do nothing ....
                }
            }
        }
    }

    public static void main(String [] args){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " start.");
                Loop loop = new Loop();//创建一个实例，用来加载Loop类
                System.out.println(Thread.currentThread().getName() + " end.");
            }
        };

        Thread thread1 = new Thread(runnable,"thread-1");
        Thread thread2 = new Thread(runnable,"thread-2");
        thread1.start();
        thread2.start();
    }

}
