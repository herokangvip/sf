package com.hk.leetcode.base;

/**
 * 动态规划：
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2zsx1/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Test02 {


    /**
     * //动态规划
     * 拆分问题，定义问题状态和状态之间的关系，使得问题能够以递推（或者说分治）的方式去解决。
     * 由于动态规划解决的问题多数有重叠子问题这个特点，为减少重复计算，对每一个子问题只解一次，
     * 将其不同阶段的不同状态保存在一个二维数组中。
     * 优化点可以不用二维数组，使用四个变量保存中间状态即可
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int length = prices.length;
        //二维数组表示当天持有股票或没有股票的状态,值为总资产
        int[][] arr = new int[length][2];
        arr[0][0] = 0;
        arr[0][1] = -prices[0];
        for (int i = 1; i < length; i++) {
            arr[i][0] = Math.max(arr[i - 1][0], arr[i - 1][1] + prices[i]);
            arr[i][1] = Math.max(arr[i - 1][1], arr[i - 1][0] - prices[i]);
        }
        return arr[length - 1][0];
    }


    /**
     * //贪心算法
     * 总是做出在当前看来是最好的选择。也就是说，不从整体最优上加以考虑，算法得到的是在某种意义上的局部最优解
     *
     * @param prices
     * @return
     */
    public int maxProfitTx(int[] prices) {
        int length = prices.length;
        if (length < 2) {
            return 0;
        }
        int total = 0;
        for (int i = 1; i < length; i++) {
            if (prices[i] > prices[i - 1]) {
                total += prices[i] - prices[i - 1];
            }
        }
        return total;
    }
}
