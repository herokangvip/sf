package com.hk.s.linkedList;

/**  双向链表
 * @author k
 * @version 1.0
 * @date 2020/12/21 17:24
 */
public class Test1 {
    public static void main(String[] args) {
        //测试双向链表
        testLinkedList();
        //测试单向链表
        testSingleLinkedList();
    }

    private static void testSingleLinkedList() {
        SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<Integer>();
        for (int i = 0; i < 10; i++) {
            singleLinkedList.add(i);
        }
        System.out.println(singleLinkedList);
    }

    private static void testLinkedList() {
        MyLinkedList2<Integer> linkedList = new MyLinkedList2<Integer>();
        for (int i = 0; i < 10; i++) {
            linkedList.addLast(i);
        }
        for (int i = 0; i < 10; i++) {
            linkedList.addFirst(i);
        }
        System.out.println("===:" + linkedList);


        for (int i = 0; i < 10; i++) {
            linkedList.removeFirst();
            System.out.println("===:" + linkedList);
        }
        System.out.println("-========================");
        for (int i = 0; i < 10; i++) {
            linkedList.removeLast();
            System.out.println("===:" + linkedList);
        }
    }
}
