package com.hk.b;

/**
 * 动态规划
 *
 * 整数数组最大和的连续子数组，返回最大和
 */
public class Test05 {
    public int maxSubArray(int[] nums) {
        int res = nums[0], temp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            temp = Math.max(temp + nums[i], nums[i]);
            res = Math.max(res, temp);
        }
        return res;
    }
}
