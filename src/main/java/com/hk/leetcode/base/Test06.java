package com.hk.leetcode.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 */
public class Test06 {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        //n行需要n个队列，打印时填充n-2个空格
        List<StringBuilder> map = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            map.add(i, new StringBuilder());

        }
        //false递增，true递减
        boolean flag = false;
        int row = 0;
        for (Character ch : s.toCharArray()) {
            StringBuilder sb = map.get(row);
            sb.append(ch);
            if (!flag) {
                row++;
            } else {
                row--;
            }
            if (row % numRows == 0) {
                flag = false;
            } else if (row % numRows == numRows - 1) {
                flag = true;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            StringBuilder list = map.get(i);
            sb.append(list.toString());
        }
        return sb.toString();
    }
}
