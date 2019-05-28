package com.wang.study.algorithms.sort;

/**
 * 作者：wanglei on 2019/4/24.
 * 邮箱：forwlwork@gmail.com
 * <p>
 * 冒泡排序
 * <p>
 * 时间复杂度：O(n²)
 * 空间复杂度：O(1)
 * 稳定排序
 */
class BubbleSort {

    public static void main(String[] args) {
        int[] array = Utils.array;

        System.out.print("原数组为：");
        Utils.printArray(array);

        System.out.println("\n=============================================");

        System.out.print("排序后为：");
        bubbleSort(array);
        Utils.printArray(array);
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
