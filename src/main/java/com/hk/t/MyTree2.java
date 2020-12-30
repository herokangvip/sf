package com.hk.t;

import java.util.*;

/**
 * 模拟树的深度优先（DFS  Depth-First-Search）和广度优先（BFS  Breath-First-Search）遍历
 * 深度优先一般解决连通性问题；广度优先解决最短路径
 *
 * @author k
 * @version 1.0
 * @date 2020/12/25 16:21
 */
public class MyTree2 {

    public static void main(String[] args) {
        MyTree2 myTree2 = new MyTree2();
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode head = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        head.left = node2;
        head.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node7.left = node8;
        System.out.println("===深度优先，递归，前序遍历");
        head.dfs();
        System.out.println("====深度优先，栈，非递归");
        head.dfsStack(stack);
        System.out.println("====广度优先，队列");
        head.bfs(linkedList);
        System.out.println("====树最大高度");
        int height = maxHeight(head);
        System.out.println("====树最大高度:" + height);
        System.out.println("====树最小高度");
        int minHeight = minHeight(head);
        System.out.println("====树最小高度:" + minHeight);
        List<List<Integer>> lists = bfsLevel(head);
        System.out.println("====按层级打印树:====");
        for (List<Integer> t : lists) {
            System.out.println(Arrays.toString(t.toArray()));
        }


    }


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        //树的深度优先遍历，前序遍历
        void dfs() {
            System.out.println(this.val);
            if (this.left != null) {
                this.left.dfs();
            }
            if (this.right != null) {
                this.right.dfs();
            }
        }

        //树的深度优先遍历，栈实现方式，此处采用非递归
        void dfsStack(Stack<TreeNode> stack) {
            stack.push(this);
            while (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                System.out.println(pop.val);
                TreeNode left = pop.left;
                TreeNode right = pop.right;
                if (right != null) {
                    stack.push(right);
                }
                if (left != null) {
                    stack.push(left);
                }
            }
        }

        //广度优先遍历,利用队列
        void bfs(LinkedList<TreeNode> linkedList) {
            System.out.println(this.val);
            TreeNode left = this.left;
            TreeNode right = this.right;
            if (left != null) {
                linkedList.addLast(left);
            }
            if (right != null) {
                linkedList.addLast(right);
            }
            if (!linkedList.isEmpty()) {
                TreeNode treeNode = linkedList.removeFirst();
                if (treeNode != null) {
                    treeNode.bfs(linkedList);
                }
            }
        }

    }


    //最大高度
    private static int maxHeight(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        return Math.max(maxHeight(treeNode.left) + 1, maxHeight(treeNode.right) + 1);
    }

    //最小高度
    private static int minHeight(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        return Math.min(minHeight(treeNode.left) + 1, minHeight(treeNode.right) + 1);
    }

    //利用bfs，分层打印树
    private static List<List<Integer>> bfsLevel(TreeNode node) {
        List<List<Integer>> res = new ArrayList<>();
        if (node == null) {
            return res;
        }
        LinkedList<TreeNode> nodeList = new LinkedList<>();
        LinkedList<Integer> levelList = new LinkedList<>();
        nodeList.addLast(node);
        levelList.addLast(1);
        while (!levelList.isEmpty()) {
            int num = levelList.removeFirst();
            if (num == 0) {
                break;
            }
            int newNum = 0;
            ArrayList<Integer> integers = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                TreeNode tempNode = nodeList.removeFirst();
                integers.add(tempNode.val);
                TreeNode left = tempNode.left;
                TreeNode right = tempNode.right;
                if (left != null) {
                    newNum++;
                    nodeList.addLast(left);
                }
                if (right != null) {
                    newNum++;
                    nodeList.addLast(right);
                }
            }
            levelList.addLast(newNum);
            res.add(integers);
        }
        return res;
    }
}
