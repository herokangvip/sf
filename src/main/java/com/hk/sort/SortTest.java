package com.hk.sort;

import java.util.Arrays;

public class SortTest {

    //快排
    private static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            //不用包含index，因为他前面都比他小后面都比他大
            int index = partition(arr, start, end);
            quickSort(arr, start, index - 1);
            quickSort(arr, index + 1, end);
        }
    }

    //取第一个数，然后比他小的放到左边，打的放到右边并返回交换后的索引
    private static int partition(int[] arr, int start, int end) {
        int base = start;
        int pointer = start + 1;
        for (int i = start + 1; i < end + 1; i++) {
            if (arr[i] < arr[start]) {
                swap(arr, pointer, i);
                pointer++;
            }
        }
        swap(arr, base, pointer - 1);
        return pointer - 1;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    //归并排序,jdk集合数组排序使用的归并+TimSort
    public static int[] mergeSort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        if (arr.length < 2) {
            return arr;
        }
        int middle = arr.length / 2;

        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);

        return merge(mergeSort(left), mergeSort(right));
    }

    protected static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }

        while (left.length > 0) {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while (right.length > 0) {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }

        return result;
    }

    //堆排序：要点只做堆排序不用构建二叉搜索树，只要保证顶点最大就行
    public static int[] heapSort(int[] sourceArr) {
        int length = sourceArr.length;
        if (length <= 1) return sourceArr;
        buildMaxHeap(sourceArr);
        for (int i = length - 1; i > 0; i--) {
            swap(sourceArr, 0, i);
            length--;
            build(sourceArr, 0, length);
        }
        return sourceArr;
    }

    private static void buildMaxHeap(int[] sourceArr) {
        for (int i = sourceArr.length / 2 - 1; i >= 0; i--) {
            build(sourceArr, i, sourceArr.length);
        }

    }

    private static void build(int[] sourceArr, int i, int length) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < length && sourceArr[left] > sourceArr[largest]) {
            largest = left;
        }
        if (right < length && sourceArr[right] > sourceArr[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(sourceArr, i, largest);
            build(sourceArr, largest, length);
        }
    }

    public static void main(String[] args) throws Exception {
        int[] arr = new int[]{3, 7, 34, 65, 24, 88, 16, 10, 21, 23};
        //mergeSort(arr);
        int[] ints = heapSort(arr);
        System.out.println("-");
    }
}
