package com.wang.study.data_structures.recursive;

/**
 * 使用递归解决汉诺塔问题
 */
public class Hanoi {

    public static void main(String[] args) {
        /*
         * 输出：
         * 将第1个盘子从A移至C
         */
        func(1, 'A', 'B', 'C');

        System.out.println("===============================");

        /*
         * 输出：
         * 将第1个盘子从A移至B
         * 将第2个盘子从A移至C
         * 将第1个盘子从B移至C
         */
        func(2, 'A', 'B', 'C');

        System.out.println("===============================");

        /*
         * 输出：
         * 将第1个盘子从A移至C
         * 将第2个盘子从A移至B
         * 将第1个盘子从C移至B
         * 将第3个盘子从A移至C
         * 将第1个盘子从B移至A
         * 将第2个盘子从B移至C
         * 将第1个盘子从A移至C
         */
        func(3, 'A', 'B', 'C');
    }

    private static void func(int n, char from, char temp, char to) {
        if (n == 1) {
            //只有一个盘子时，只需将其从 from 移至 to 即可
            System.out.println("将第1个盘子从" + from + "移至" + to);
        } else {
            /*
             * 当有 n(n > 1) 个盘子时
             * 1、先将 (n - 1) 个盘子从 from 移至 temp
             * 2、再将第 n 个盘子从 from 移至 to
             * 3、最后将 (n - 1) 个盘子从 temp 移至 to 即可。
             */
            func(n - 1, from, to, temp);
            System.out.println("将第" + n + "个盘子从" + from + "移至" + to);
            func(n - 1, temp, from, to);
        }
    }

}
