package com.wang.study.algorithms.sort;

/**
 * 插入排序
 * <p>
 * 时间复杂度：O(n²)
 * 空间复杂度：O(1)
 * 稳定排序
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] array = Utils.array;

        System.out.print("原数组为：");
        Utils.printArray(array);

        System.out.println("\n=============================================");

        System.out.print("排序后为：");
        insertSort(array);
        Utils.printArray(array);
    }

    private static void insertSort(int[] array) {
        int temp;
        int index;

        for (int i = 1; i < array.length; i++) {
            temp = array[i];
            index = i;

            while (index > 0 && array[index - 1] >= temp) {
                array[index] = array[index - 1];
                index--;
            }

            array[index] = temp;
        }
    }

}
