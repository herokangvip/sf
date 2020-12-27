package com.hk.leetcode.base;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Test04 {
    public static void main(String[] args) {
        String s = "ab";
        int i = lengthOfLongestSubstring(s);
        System.out.println("-");
    }

    //暴力法，有重复判断和空间浪费
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        HashSet<Character> set;
        char[] array = s.toCharArray();
        int max = 0;
        for (int i = 0; i < array.length - 1; i++) {
            set = new HashSet<>();
            set.add(array[i]);
            for (int j = i + 1; j < array.length; j++) {
                if (!set.contains(array[j])) {
                    set.add(array[j]);
                    if (set.size() > max) {
                        max = set.size();
                    }
                } else {
                    if ((j - i + 1) > max) {
                        max = j - i;
                    }
                    break;
                }
            }

        }
        return max;
    }

    //优化，双指针
    public static int lengthOfLongestSubstring2(String s) {
        Set<Character> set = new HashSet<Character>();
        int length = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int right = -1;
        int res = 0;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                set.remove(s.charAt(i - 1));
            }
            while (right + 1 < length && !set.contains(s.charAt(right + 1))) {
                // 不断地移动右指针
                set.add(s.charAt(right + 1));
                ++right;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            res = Math.max(res, right - i + 1);
        }
        return res;
    }

    //优化，双指针,自己写一遍上一个方法使copy的
    public static int lengthOfLongestSubstring3(String s) {
        Set<Character> set = new HashSet<Character>();
        int length = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int right = -1;
        int res = 0;
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                set.remove(s.charAt(i - 1));
            }
            while (right + 1 < length && !set.contains(s.charAt(right + 1))) {
                set.add(s.charAt(right + 1));
                right++;
            }
            res = Math.max(res, right - i + 1);
        }
        return res;
    }
}
