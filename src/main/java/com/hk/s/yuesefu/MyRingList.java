package com.hk.s.yuesefu;


/**
 * @author k
 * @version 1.0
 * @date 2020/12/21 18:11
 */
public class MyRingList<E> {

    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;

    public MyRingList() {
    }


    public void add(E e) {
        final Node<E> f = first;
        final Node<E> l = last;
        final Node<E> node = new Node<E>(e, null);
        if (f == null) {
            first = node;
            node.next = first;
            last = first;
        } else {
            Node<E> eNode = new Node<E>(e, f);
            l.next = eNode;
            last = eNode;
        }
        size++;
    }


    public static class Node<E> {
        E data;
        Node<E> next;

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MyRingList{");
        Node<E> node = first;
        sb.append(node.data);
        for (int i = 0; i < size -1; i++) {
            node = node.next;
            sb.append(",").append(node.data);
        }
        sb.append('}');
        return sb.toString();
    }
}
