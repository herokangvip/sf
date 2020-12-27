package com.hk.leetcode.base;

/**
 * 提示：双指针法
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * 你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2gy9m/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Test01 {

    public static void main(String[] args) {
        int i = removeDuplicates(new int[]{1, 1, 2});
        System.out.println(i);
    }

    public static int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int length = nums.length;
        int i = 0;
        int j = 1;
        while (j < length) {
            if (nums[i] == nums[j]) {
                j++;
                continue;
            } else if (j != i + 1) {
                int temp = nums[i + 1];
                nums[i + 1] = nums[j];
                nums[j] = temp;
            }
            i++;
            j++;
        }
        return i + 1;
    }
}
