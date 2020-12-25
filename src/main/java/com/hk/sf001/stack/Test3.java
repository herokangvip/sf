package com.hk.sf001.stack;

/**
 * @author k
 * @version 1.0
 * @date 2020/12/22 11:01
 */
public class Test3 {
    public static void main(String[] args) {
        MyStack<Integer> myStack = new MyStack<Integer>(16);
        for (int i = 0; i < 10; i++) {
            myStack.push(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(myStack.pop());
        }
    }
}
