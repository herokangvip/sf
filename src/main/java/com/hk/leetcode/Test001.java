package com.hk.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 第一题两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * @author k
 * @version 1.0
 * @date 2020/12/22 22:19
 */
public class Test001 {
    public static void main(String[] args) {

        int target = 9;
        int[] nums = {2, 7, 11, 15};
        Test001 test001 = new Test001();
        int[] ints = test001.twoSum(nums, target);
        System.out.println(Arrays.toString(ints));
    }

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(target - num)) {
                res[0] = map.get(target - num);
                res[1] = i;
                return res;

            } else {
                map.put(num, i);
            }
        }
        return res;
    }
}
