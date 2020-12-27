package com.hk.leetcode.base;

/**
 * 求最长回文子串
 */
public class Test05 {

    public static void main(String[] args) {
        String s = "babad";
        String s1 = longestPalindrome(s);
        System.out.println(s1);
    }

    /**
     * 时间复杂度O(n方)
     * tk只是确定循环次数，回文串确定从单个字符到两个三个。。。少的才可以推断出多的
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        int length = s.length();
        boolean[][] arr = new boolean[length][length];
        String res = "";
        for (int t = 0; t < length; t++) {
            for (int k = 0; k < length - t; k++) {
                int i = k;
                int j = k + t;
                if (i == j) {
                    arr[i][j] = true;
                } else if (j == i + 1 && (s.charAt(j) == s.charAt(i))) {
                    arr[i][j] = true;
                } else {
                    arr[i][j] = arr[i + 1][j - 1] & s.charAt(i) == s.charAt(j);
                }
                if (arr[i][j] & (j - i + 1) > res.length()) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }


    //边界扩展法
    public String longestPalindrome2(String s) {
        int length = s.length();
        String ans = "";
        for (int i = 0; i < length; i++) {
            int num1 = expandAroundCenter(s, i, i);
            int num2 = expandAroundCenter(s, i, i + 1);
            int max = Math.max(num1, num2);
            if (max > ans.length()) {
                //这里容易混乱
                ans = s.substring(i - (max - 1) / 2, i + max / 2 + 1);
            }
        }
        return ans;
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }


}
