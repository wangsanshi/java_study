package com.wang.study.data_structures.recursive;

/**
 * 解决斐波那契数列
 * <p>
 * 1 1 2 3 5 8 13 21 34 ....
 */
public class Fibonacci {

    public static void main(String[] args) {
        System.out.println("第1项为：" + func(1));
        System.out.println("第2项为：" + func(2));
        System.out.println("第5项为：" + func(5));
        System.out.println("第9项为：" + func(9));
        long t1 = System.currentTimeMillis();
        System.out.print("第30项为：" + func(50));
        long t2 = System.currentTimeMillis();
        System.out.println(" , 耗时：" + (t2 - t1) + "ms");
    }

    /**
     * 使用递归输出斐波那契数列的第 n 项
     */
    private static long func(long n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return func(n - 1) + func(n - 2);
        }
    }

}
