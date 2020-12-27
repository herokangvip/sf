package com.hk.bytedance;

import sun.font.FontRunIterator;

import java.util.HashMap;

/**
 * m行n列网格，从左上角走到右下角共几种走法，每次只能向下或向右
 * f(i,j)=f(i−1,j)+f(i,j−1)
 */
public class Test06 {
    public int uniquePaths(int m, int n) {
        int[][] arr = new int[m][n];
        //因为只能向下或向右，所以最左边一列和最上面一行都是只有一条路，因此值都为1
        for (int i = 0; i < m; i++) {
            arr[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            arr[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
            }
        }
        return arr[m - 1][n - 1];
    }


    //有障碍物版本
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] arr = new int[m][n];

        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            arr[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                break;
            }
            arr[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    arr[i][j] = 0;
                    continue;
                }
                arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
            }
        }
        return arr[m - 1][n - 1];
    }


    //求和版本
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] arr = new int[m][n];
        arr[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            arr[i][0] = arr[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            arr[0][i] = arr[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                arr[i][j] = Math.min(arr[i - 1][j], arr[i][j - 1]) + grid[i][j];
            }
        }
        return arr[m - 1][n - 1];
    }


    /**
     * 爬n阶楼梯，每次可以爬1或2，共有多少种方法
     *
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if (n == 1) return 1;
        int[] arr = new int[n];
        arr[0] = 1;
        arr[1] = 2;
        for (int i = 2; i < n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n - 1];
    }


    /**
     * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
     * <p>
     * 题目数据保证答案肯定是一个 32 位的整数。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "12"
     * 输出：2
     * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
     * 示例 2：
     * <p>
     * 输入：s = "226"
     * 输出：3
     * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/decode-ways
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public static int numDecodings(String s) {
        if (s.startsWith("0")) {
            return 0;
        }
        int[] arr = new int[s.length()];
        arr[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            Character ch = s.charAt(i);
            int a = Integer.parseInt(ch.toString());
            Character ch2 = s.charAt(i+1);
            int b = Integer.parseInt(ch2.toString());
            if(a==0){
                arr[i] = arr[i - 1] - 1;
            }else if (a ==1) {
                arr[i] = arr[i - 1] + 1;
            } else {
                arr[i] = arr[i - 1];
            }
        }
        return arr[s.length() - 1];
    }

    public static void main(String[] args) {
        String s = "2101";
        int i = numDecodings(s);
        System.out.println("===:" + i);
    }
}
