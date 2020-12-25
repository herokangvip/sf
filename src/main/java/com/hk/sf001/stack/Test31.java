package com.hk.sf001.stack;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 计算器操作数栈和符号栈
 *
 * @author k
 * @version 1.0
 * @date 2020/12/22 11:08
 */
public class Test31 {
    public static void main(String[] args) {
        MyStack<Integer> numStack = new MyStack<Integer>(1000);
        MyStack<String> symbolStack = new MyStack<String>(1000);
        String s = "7*2*2-5+1-5+3-4";
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("*", 1);
        map.put("/", 2);
        map.put("+", 3);
        map.put("-", 4);
        char[] chars = s.toCharArray();

        for (char cha : chars) {
            boolean digit = Character.isDigit(cha);
            if (digit) {
                numStack.push(Character.getNumericValue(cha));
            } else {
                if (symbolStack.isEmpty()) {
                    symbolStack.push(String.valueOf(cha));
                } else {
                    Integer level = map.get(String.valueOf(cha));
                    String pop = symbolStack.pop();
                    Integer popLevel = map.get(pop);
                    if (level >= popLevel) {
                        Integer num1 = numStack.pop();
                        Integer num2 = numStack.pop();
                        switch (popLevel) {
                            case 1:
                                numStack.push(num1 * num2);
                                break;
                            case 2:
                                numStack.push(num2 / num1);
                                break;
                            case 3:
                                numStack.push(num1 + num2);
                                break;
                            case 4:
                                numStack.push(num2 - num1);
                                break;
                        }
                        symbolStack.push(String.valueOf(cha));

                    } else {
                        symbolStack.push(String.valueOf(cha));
                    }
                }
            }
        }
        System.out.println("-");
    }
}
