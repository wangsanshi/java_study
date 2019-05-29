package com.wang.study.algorithms.sort;

/**
 * 归并排序
 * <p>
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(n)
 * 稳定排序
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] array = Utils.array;

        System.out.print("原数组为：");
        Utils.printArray(array);

        System.out.println("\n=============================================");

        System.out.print("排序后为：");
        mergeSort(array);
        Utils.printArray(array);
    }

    private static void mergeSort(int[] array) {
        int[] temp = new int[array.length];
        sort(array, 0, array.length - 1, temp);
    }

    private static void sort(int[] src, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) >>> 1;
            sort(src, left, mid, temp);
            sort(src, mid + 1, right, temp);
            merge(src, left, mid, right, temp);
        }
    }

    /**
     * 归并两个数组
     */
    private static void merge(int[] src, int left, int mid, int right, int[] temp) {
        int i = left;      //左边数组范围为： left ~ mid
        int j = mid + 1;   //右边数组范围为： (mid + 1) ~ right
        int t = 0;         //temp数组的数据索引

        while ((i <= mid) && (j <= right)) {
            if (src[i] <= src[j]) {
                temp[t++] = src[i++];
            } else {
                temp[t++] = src[j++];
            }
        }

        while (i <= mid) {
            temp[t++] = src[i++];
        }

        while (j <= right) {
            temp[t++] = src[j++];
        }

        t = 0;
        while (left <= right) {
            src[left++] = temp[t++];
        }
    }

}
