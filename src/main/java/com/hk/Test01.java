package com.hk;

import java.util.ArrayList;

/**
 * 有 k 种颜色的涂料和一个包含 n 个栅栏柱的栅栏，每个栅栏柱可以用其中一种颜色进行上色。
 * <p>
 * 你需要给所有栅栏柱上色，并且保证其中相邻的栅栏柱 最多连续两个 颜色相同。然后，返回所有有效涂色的方案数。
 * <p>
 * 注意:
 * n 和 k 均为非负的整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/paint-fence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Test01 {


    public int numWays(int n, int k) {
        if (n == 0 || k == 0) return 0;
        if (n == 1) return k;
        int[] arr = new int[n];
        arr[0] = k;
        arr[1] = k * k;
        for (int i = 2; i < n; i++) {
            arr[i] = arr[i - 1] * (k - 1) + arr[i - 2] * (k - 1);
        }
        return arr[n - 1];
    }

    //s是否是t的子序列
    public boolean isSubsequence(String s, String t) {
        if ("".equals(s)) return true;
        if ("".equals(t)) return false;
        int pointer1 = 0;
        int pointer2 = 0;
        while (pointer2 < t.length()) {
            if (s.charAt(pointer1) == t.charAt(pointer2)) {
                pointer1++;
                pointer2++;
                if (pointer1 == s.length()) {
                    return true;
                }
            } else {
                pointer2++;
            }
        }
        return false;
    }


    public static int minCostClimbingStairs(int[] cost) {
        if (cost.length <= 2) {
            return 0;
        }
        int[] arr = new int[cost.length];
        arr[0] = cost[0];
        arr[1] = cost[1];
        for (int i = 2; i < cost.length - 1; i++) {
            arr[i] = Math.min(arr[i - 1], arr[i - 2]) + cost[i];
        }
        return Math.min(arr[cost.length - 2], arr[cost.length - 3]);
    }

    //你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
    //
    //给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
    //
    // 
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/house-robber
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 0) return 0;
        if (length == 1) return nums[0];
        if (length == 2) return Math.max(nums[0], nums[1]);
        int[] arr = new int[length];
        arr[0] = nums[0];
        arr[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            arr[i] = Math.max(arr[i - 2] + nums[i], arr[i - 1]);
        }
        return Math.max(arr[length - 1], arr[length - 2]);
    }


    public static void main(String[] args) {
        /*int[] arr = new int[]{-5, -2, 0, 0, 3, 9, -2, -5, 4};//5  9   12
        int i = kConcatenationMaxSum(arr, 5);
        System.out.println(i);*/
        int a = Integer.MAX_VALUE;
        int i = a + 1;
        System.out.println(i);
        int b = Integer.MIN_VALUE;
        int c = b - 1;
        System.out.println(c);
    }


    //硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，
    // 编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)

    /**
     * 01背包的状态转移方程：
     * F[i,v] = max{F[i-1,v], F[i-1,v-Ci] + Wi}
     * <p>
     * 完全背包的状态转移方程：
     * F[i,v] = max{F[i-1,v-kCi] + kWi | 0 <= k <= v/Ci}
     * <p>
     * 多重背包的状态转移方程：
     * F[i,v] = max{F[i-1,v-kCi] + kWi | 0 <= k <= Mi}
     */
    public int waysToChange(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        int[] coins = {1, 5, 10, 25};

        for (int i = 0; i <= n; i++)
            dp[i] = 1;

        for (int i = 1; i < 4; i++) {
            for (int j = 1; j <= n; j++) {
                if (j >= coins[i])
                    dp[j] = (dp[j] + dp[j - coins[i]]) % 1000000007;
            }
        }
        return dp[n];
    }

    public static int kConcatenationMaxSum(int[] arr, int k) {
        int length = arr.length;
        if (length == 0 || k <= 0) {
            return 0;
        }
        //单个数组的最大前缀和，最大后缀和，最大子序列和
        long prefix = arr[0];
        long suffix = arr[length - 1];
        long mid = arr[0];
        long prefixTotal = arr[0];
        long suffixTotal = arr[length - 1];
        int[] temp = new int[length];
        temp[0] = arr[0];
        for (int i = 1; i < length; i++) {
            temp[i] = Math.max(temp[i - 1] + arr[i], arr[i]);
            mid = Math.max(temp[i], mid);
            prefixTotal += arr[i];
            suffixTotal += arr[length - 1 - i];
            prefix = Math.max(prefix, prefixTotal);
            suffix = Math.max(suffix, suffixTotal);
        }
        if (mid < 0) {
            return 0;
        }
        long kRes2 = Math.max(Math.max(Math.max(prefix, (prefix + suffix)), suffix), mid) % 1000000007;
        if (k == 1) {
            return (int) mid % 1000000007;
        } else if (k == 2) {
            return (int) kRes2 % 1000000007;
        } else {
            if (prefixTotal > 0) {
                return (int) (kRes2 % 1000000007 + ((k - 2) * prefixTotal) % 1000000007) % 1000000007;
            }
            return (int) kRes2 % 1000000007;
        }
    }
}
