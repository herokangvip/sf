package com.hk.sf001.yuesefu;

/**
 * 约瑟夫单向链表
 *
 * @author k
 * @version 1.0
 * @date 2020/12/21 21:54
 */
public class MyRingList2<E> {

    transient Node<E> first;
    transient Node<E> last;

    public void add(E e) {
        final Node<E> fs = first;
        final Node<E> la = last;
        if (fs == null) {
            first = last = new Node<E>(e, null);
            first.next = first;
        } else {
            Node<E> node = new Node<E>(e, first);
            last = node;
            la.next = node;
        }
    }

    static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }
}
