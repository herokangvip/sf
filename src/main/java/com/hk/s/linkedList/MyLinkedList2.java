package com.hk.s.linkedList;

import java.io.Serializable;
import java.util.NoSuchElementException;

/**
 * @author k
 * @version 1.0
 * @date 2020/12/21 21:31
 */
public class MyLinkedList2<E> implements Serializable {
    private static final long serialVersionUID = 5092641710841945170L;

    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;

    public void addFirst(E e) {
        final Node<E> f = first;
        if (f == null) {
            first = last = new Node<E>(null, e, null);
        } else {
            first = new Node<E>(null, e, f);
            f.prev = first;
        }
        size++;
    }

    public void addLast(E e) {
        final Node<E> las = last;
        if (las == null) {
            first = last = new Node<E>(null, e, null);
        } else {
            last = new Node<E>(las, e, null);
            las.next = last;
        }
        size++;
    }


    public E removeFirst() {
        final Node<E> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        E data = f.data;
        if (size == 1) {
            first = last = null;
        } else {
            first = f.next;
            first.prev = null;
        }
        size--;
        return data;
    }

    public E removeLast() {
        final Node<E> la = last;
        if (la == null) {
            throw new NoSuchElementException();
        }
        E data = la.data;
        if (size == 1) {
            first = last = null;
        } else {
            last = la.prev;
            last.next = null;
        }
        size--;
        return data;
    }

    static class Node<E> {
        Node<E> prev;
        E data;
        Node<E> next;

        public Node(Node<E> prev, E data, Node<E> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MyLinkedList2{");
        if (first != null) {
            Node<E> node = first;
            sb.append(node.data);
            while (node.next != null) {
                sb.append(",").append(node.next.data);
                node = node.next;
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
