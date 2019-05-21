package com.wang.study.algorithms.sort;

/**
 * 作者：wanglei on 2019/4/24.
 * 邮箱：forwlwork@gmail.com
 */

class SortMain {
    private static void printArray(int[] array) {
        if (array != null) {
            for (int i : array) {
                System.out.print(i + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100);
        }

        System.out.print("原数组为：");
        printArray(array);

        System.out.println("\n=============================================");

        //冒泡排序
        bubbleSort(array);
        System.out.print("通过冒泡排序后的数组为：");
        printArray(array);
    }

    private static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

}
