package com.hk.s.linkedList;

/**
 * 带头节点的单向链表
 *
 * @author k
 * @version 1.0
 * @date 2020/12/21 22:10
 */
public class SingleLinkedList<E> {

    Node<E> head;
    Node<E> tail;
    int size = 0;

    void add(E e) {
        final Node<E> ta = tail;
        if (head == null) {
            head = tail = new Node<E>(e, null);
        } else {
            Node<E> eNode = new Node<E>(e, null);
            ta.next = eNode;
            tail = eNode;
        }
        size++;
    }

    static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SingleLinkedList{");
        if (head != null) {
            sb.append(head.data);
            Node<E> node = head.next;
            while (node != null) {
                sb.append(",").append(node.data);
                node = node.next;
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
