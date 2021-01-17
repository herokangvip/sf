package com.hk.mt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test2 {
    /**
     * 二叉树的层序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        if (root == null) return res;
        linkedList.addLast(root);
        while (linkedList.size() > 0) {
            List<Integer> temp = new ArrayList<>();
            int size = linkedList.size();
            for (int i = 0; i < size; i++) {
                TreeNode first = linkedList.removeFirst();
                temp.add(first.val);
                if (first.left != null) {
                    linkedList.addLast(first.left);
                }
                if (first.right != null) {
                    linkedList.addLast(first.right);
                }
            }
            res.add(temp);
        }
        return res;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
