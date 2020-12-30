package com.hk.s.stack;

/**
 * @author k
 * @version 1.0
 * @date 2020/12/22 10:51
 */
public class MyStack<E> {
    Object[] items;
    int size = 0;
    int top = -1;
    int tail = -1;

    public MyStack(int size) {
        if (size == 0) {
            this.size = 16;
        }
        this.size = size;
        items = new Object[this.size];
    }

    public void push(E e) {
        if (top == size - 1) {
            throw new RuntimeException("队列已满");
        }
        items[++top] = e;
    }

    public E pop() {
        if (tail == top) {
            throw new RuntimeException("队列已空");
        }
        final E item = (E) items[top];
        items[top] = null;
        top--;
        return item;
    }

    public boolean isEmpty() {
        return top == tail;
    }
}
