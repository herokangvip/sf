package com.hk.b;

/**
 * 两个正序数组的中位数
 */
public class Test03 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int totalLength = length1 + length2;
        int flag = totalLength % 2;
        if (flag == 1) {
            //基数,找到第k小的数,k=n/2+1
            return getMinNum(nums1, nums2, totalLength / 2 + 1);
        } else {
            //偶数,找到第k和k-1小的数,k=n/2+1和n/2
            int num1 = getMinNum(nums1, nums2, totalLength / 2 + 1);
            int num2 = getMinNum(nums1, nums2, totalLength / 2);
            return (num1 + num2) / 2.0;
        }

    }

    private int getMinNum(int[] nums1, int[] nums2, int k) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int index1 = 0;
        int index2 = 0;

        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }

    }


}
