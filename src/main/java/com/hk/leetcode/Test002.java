package com.hk.leetcode;

import java.util.List;

/**
 * 第二题：两数相加
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * @author k
 * @version 1.0
 * @date 2020/12/22 22:53
 */
public class Test002 {

    public static void main(String[] args) {
        ListNode listNode2 = new ListNode(2);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);

        listNode2.next = listNode4;
        listNode4.next = listNode3;

        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        ListNode listNode44 = new ListNode(4);
        listNode5.next = listNode6;
        listNode6.next = listNode44;
        Test002 test002 = new Test002();
        ListNode listNode = test002.addTwoNumbers(listNode2, listNode5);
        System.out.println("-");
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode head = new ListNode();
        ListNode temp = head;
        int b = 0;
        while (l1 != null || l2 != null || b > 0) {
            int num = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + b;
            int a = num % 10;
            b = num / 10;
            temp.val = a;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            if (l1 != null || l2 != null || b > 0) {
                temp.next = new ListNode();
                temp = temp.next;
            }
        }
        return head;
    }
}
