package com.hk.sf001.linkedList;

import java.io.Serializable;
import java.util.NoSuchElementException;

/**
 * @author k
 * @version 1.0
 * @date 2020/12/21 15:31
 */
public class MyLinkedList<E> implements Serializable {

    private static final long serialVersionUID = -6173680544741545691L;
    /**
     * 应序列化实际数据，自己实现readObject和writeObject
     */
    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;

    public MyLinkedList() {
    }

    public void addFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<E>(e, null, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    public void addLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<E>(e, l, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    public E getFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        return first.data;
    }

    public E getLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        return last.data;
    }


    public E removeFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        final E data = first.data;
        if (size == 1) {
            first = last = null;
        } else {
            Node<E> next = first.next;
            next.prev = null;
            first = next;
        }
        return data;
    }

    public E removeLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        final E data = last.data;
        if (size == 1) {
            first = last = null;
        } else {
            Node<E> prev = last.prev;
            prev.next = null;
            last = prev;
        }
        return data;
    }

    private static class Node<E> {
        E data;
        Node<E> prev;
        Node<E> next;

        Node(E data, Node<E> prev, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MyLinkedList{");
        if (first != null) {
            sb.append(first.data);
            Node<E> node = first.next;
            while (node != null) {
                final Node<E> finalNode = node;
                sb.append(",").append(node.data);
                node = finalNode.next;
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
