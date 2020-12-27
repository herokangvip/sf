package com.hk.leetcode.base;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 */
public class Test08 {

    public static void main(String[] args) {
        boolean palindrome = isPalindrome(121);
        System.out.println("");
    }
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x <= 9) {
            return true;
        }
        if (x % 10 == 0) {
            return false;
        }
        int num = 0;
        while (num < x) {
            int y = x % 10;
            x /= 10;
            num = num * 10 + y;
        }
        return num == x || num/10==x;

    }
}
