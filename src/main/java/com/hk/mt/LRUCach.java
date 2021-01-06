package com.hk.mt;

import java.util.HashMap;

/**
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class LRUCache {
    int capacity = 0;
    int size = 0;
    Node head;
    Node tail;

    HashMap<Integer, Node> cache = null;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new Node(0, 0, null, null);
        tail = new Node(0, 0, head, null);
        head.next = tail;
    }

    //如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1
    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            //移动到链表头部
            moveToHead(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.val = value;
            moveToHead(node);
        } else {
            //不存在则添加到头部
            addToHead(key, value);
        }
    }

    private void addToHead(int key, int value) {
        Node node = new Node(key, value, null, null);
        if (size == capacity) {
            //满了
            cache.remove(tail.pre.key);
            tail.pre.pre.next = tail;
            tail.pre = tail.pre.pre;

            node.pre = head;
            node.next = head.next;
            head.next.pre = node;
            head.next = node;
            cache.put(key, node);
        } else {
            node.pre = head;
            node.next = head.next;
            head.next.pre = node;
            head.next = node;
            cache.put(key, node);
            size++;
        }

    }

    private void moveToHead(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }



    static class Node {
        int key;
        int val;
        Node pre;
        Node next;

        public Node(int key, int val, Node pre, Node next) {
            this.key = key;
            this.val = val;
            this.pre = pre;
            this.next = next;
        }
    }


    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        int i = lRUCache.get(1);// 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        int i1 = lRUCache.get(2);// 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        int i2 = lRUCache.get(1);// 返回 -1 (未找到)
        int i3 = lRUCache.get(3);// 返回 3
        int i4 = lRUCache.get(4);// 返回 4
        System.out.println("-");
    }
}
