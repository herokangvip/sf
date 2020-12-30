package com.hk.s.yuesefu;

/**
 * 约瑟夫问题：环形单向链表
 *
 * @author k
 * @version 1.0
 * @date 2020/12/21 18:01
 */
public class Test2 {

    /**
     * N个人围成一圈，从第一个开始报数，第M个将被杀掉，最后剩下一个，其余人都将被杀掉。例如N=6，M=5，被杀掉的顺序是：5，4，6，2，3
     *
     * @param args
     */
    public static void main(String[] args) {
        MyRingList2<Integer> myRingList = new MyRingList2<Integer>();
        int n = 6;
        int m = 5;

        for (int i = 1; i <= n; i++) {
            myRingList.add(i);
        }
        System.out.println(myRingList);


        MyRingList2.Node<Integer> node = myRingList.first;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                MyRingList2.Node<Integer> pre = node;
                MyRingList2.Node<Integer> next = pre.next;
                if (j == m - 1) {
                    System.out.println("移除:" + next.data);
                    pre.next = next.next;
                    node = next.next;
                    break;
                } else {
                    node = next;
                }
            }
        }
    }

}
