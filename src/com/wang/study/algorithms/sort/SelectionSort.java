package com.wang.study.algorithms.sort;

/**
 * 选择排序
 * <p>
 * 时间复杂度：O(n²)
 * 空间复杂度：O(1)
 * 不稳定排序
 */
class SelectionSort {

    public static void main(String[] args) {
        int[] array = Utils.array;

        System.out.print("原数组为：");
        Utils.printArray(array);

        System.out.println("\n=============================================");

        System.out.print("排序后为：");
        slectionSort(array);
        Utils.printArray(array);
    }

    private static void slectionSort(int[] array) {
        int temp;
        int index;

        for (int i = 0; i < array.length; i++) {
            temp = array[i];
            index = i;

            //找到 (i + 1) ~ (array.length - 1) 项中最小的数据项，并用 index 保存其索引，用 temp 保存其值
            for (int j = i + 1; j < array.length; j++) {
                if (temp > array[j]) {
                    index = j;
                    temp = array[j];
                }
            }

            //交换最小的项和第i项
            if (index > i) {
                array[index] = array[i];
                array[i] = temp;
            }
        }
    }

}
