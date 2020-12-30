package com.hk.b;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Test01 {
    public int trap(int[] height) {
        int res = 0;
        int length = height.length;
        if (length <= 2) {
            return res;
        }

        for (int i = 1; i < length - 1; i++) {
            int leftMax = getLeftMax(height, i);
            int rightMax = getRightMax(height, i);
            if (leftMax <= height[i] || rightMax <= height[i]) {
                continue;
            }
            res += Math.min(leftMax, rightMax) - height[i];
        }

        return res;
    }

    private int getRightMax(int[] height, int i) {
        int max = 0;
        for (int j = height.length - 1; j > i; j--) {
            max = Math.max(max, height[j]);
        }
        return max;
    }

    private int getLeftMax(int[] height, int i) {
        int max = 0;
        for (int j = 0; j < i; j++) {
            max = Math.max(max, height[j]);
        }
        return max;
    }
}
