package com.hk.total;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class LinkedListTest {

    //https://leetcode-cn.com/problems/add-two-numbers/
    //链表两数相加逆序
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode node = head;
        //商
        int div = 0;
        while (l1 != null || l2 != null || div > 0) {
            int num = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + div;
            div = num / 10;
            int remainder = num % 10;//余数
            node.next = new ListNode(remainder);
            node = node.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return head.next;
    }


    //https://leetcode-cn.com/problems/add-two-numbers-ii
    //链表两数相加
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode node = head;
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        Stack<ListNode> resStack = new Stack<>();
        while (l1 != null) {
            stack1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2);
            l2 = l2.next;
        }
        //商
        int div = 0;
        while (stack1.size() != 0 || stack2.size() != 0 || div > 0) {
            ListNode pop1 = null;
            ListNode pop2 = null;
            if (stack1.size() != 0) {
                pop1 = stack1.pop();
            }
            if (stack2.size() != 0) {
                pop2 = stack2.pop();
            }
            int num = (pop1 == null ? 0 : pop1.val) + (pop2 == null ? 0 : pop2.val) + div;
            div = num / 10;
            int remainder = num % 10;//余数
            resStack.push(new ListNode(remainder));
            //node.next = new ListNode(remainder);
            //node = node.next;
        }
        while (resStack.size() > 0) {
            ListNode pop = resStack.pop();
            node.next = pop;
            node = node.next;
        }
        return head.next;
    }

    //https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/
    //链表反转
    public ListNode reverseList(ListNode head) {
        if (head == null) return head;
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    //递归
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode last = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }


    //https://leetcode-cn.com/problems/reverse-linked-list-ii/
    //反转从位置 m 到 n 的链表
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode now = head;
        int index = 1;
        if (m == n) {
            return head;
        }
        ListNode before = null;
        while (now != null) {
            if (index == m) {
                //递归反转
                ListNode node = reverseBefore(now, n - m + 1);
                if (m == 1) {
                    return node;
                }
                before.next = node;
                break;
            }
            before = now;
            now = now.next;
            index++;
        }
        return head;
    }

    static ListNode after = null;

    public static ListNode reverseBefore(ListNode head, int m) {
        if (m == 1) {
            after = head.next;
            return head;
        }
        ListNode last = reverseBefore(head.next, m - 1);
        head.next.next = head;
        head.next = after;
        return last;
    }


    //链表相交:https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode nodeA = headA, nodeB = headB;
        while (nodeA != nodeB) {
            nodeA = nodeA == null ? headB : nodeA.next;
            nodeB = nodeB == null ? headA : nodeB.next;
        }
        return nodeA;
    }


    //链表是否有环：https://leetcode-cn.com/problems/linked-list-cycle
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    //链表进入环的第一个节点：https://leetcode-cn.com/problems/linked-list-cycle-ii/
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }


    //https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) return head;
        ListNode pre = null;
        ListNode now = head;
        while (now != null) {
            if (now.val == val) {
                if (pre == null) return now.next;
                pre.next = now.next;
            }
            pre = now;
            now = now.next;
        }
        return head;
    }

    //删除链表倒数第n个节点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return head;
        ListNode pre = null;
        ListNode slow = head;
        ListNode fast = head;
        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next;
        }
        pre.next = slow.next;
        return head;
    }

    //https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        int num = head.val;
        ListNode now = head.next;
        ListNode pre = head;
        while (now != null) {
            if (now.val == num) {
                pre.next = now.next;
                now = now.next;
            } else {
                pre = now;
                num = now.val;
                now = now.next;
            }
        }
        return head;
    }


    //插入排序：先找它 - 临时保存它 - 删掉它 - 给它找位置 - 插入它
    //https://leetcode-cn.com/problems/insertion-sort-list/
    public static ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }
        //虚拟头结点
        ListNode virtualHead = new ListNode(0);
        virtualHead.next = head;
        //最后排过序的节点
        ListNode lastSorted = head;
        ListNode curr = head.next;
        while (curr != null) {
            if (lastSorted.val <= curr.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode prev = virtualHead;
                while (prev.next.val <= curr.val) {
                    prev = prev.next;
                }
                //删除节点
                lastSorted.next = curr.next;
                //节点插入到prev和prev.next之间
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastSorted.next;
        }
        return virtualHead.next;
    }

    //归并排序
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;//打断
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        ListNode h = new ListNode(0);
        ListNode res = h;
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left != null ? left : right;
        return res.next;
    }

    //https://leetcode-cn.com/problems/partition-list/
    public ListNode partition(ListNode head, int x) {
        if (head == null) return head;
        ListNode virtualHead = new ListNode();
        virtualHead.next = head;
        ListNode pre = virtualHead;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                pre.next = cur.next;
                virtualHead.next = cur;
                cur.next = head;
                head = cur;
                continue;
            }
            pre = pre.next;
            cur = cur.next;
        }
        return virtualHead.next;
    }


    //判断是不是二叉搜索树
    //https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
    public static boolean verifyPostorder(int[] postorder) {
        return verifyPostorderFuc(postorder, 0, postorder.length - 1);
    }

    private static boolean verifyPostorderFuc(int[] postorder, int left, int right) {
        if (left >= right) {
            return true;
        }
        int root = postorder[right];
        int mid = right-1;
        for (int i = mid; i >= left; i--) {
            if (postorder[i] > root) {
                mid--;
            }
        }
        for (int i = mid; i >= left; i--) {
            if (postorder[i] > root) {
                return false;
            }
        }

        return verifyPostorderFuc(postorder, left, mid) && verifyPostorderFuc(postorder, mid + 1, right - 1);
    }





    public static void main(String[] args) {
        int [] arr = new int[]{4, 8, 6, 12, 16, 14, 10};
        boolean b = verifyPostorder(arr);
        System.out.println("");
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


}
