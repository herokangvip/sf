package com.hk.leetcode.base;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Test03 {

    //暴力法O(n平方)
    public int maxProfit(int[] prices) {
        int length = prices.length;
        int total = 0;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                int temp = prices[j] - prices[i];
                if (temp > total) {
                    total = temp;
                }
            }
        }
        return total;
    }

    //O(n)
    public int maxProfit2(int[] prices) {
        int length = prices.length;
        int min = Integer.MAX_VALUE;
        //不是全局最大值而是当天与前几天的最小值的差额
        int max = 0;
        for (int i = 0; i < length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else if (prices[i] - min > max) {
                max = prices[i] - min;
            }
        }
        return max;
    }
}
