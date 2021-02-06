package com.hk.mt;

import java.util.*;

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

    /**
     * 二叉树的层序遍历  dfs深度优先实现
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        dfs(0, root, res);
        return res;
    }

    private void dfs(int level, TreeNode root, List<List<Integer>> res) {
        if (res.size() == level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);
        if (root.left != null) {
            dfs(level + 1, root.left, res);
        }
        if (root.right != null) {
            dfs(level + 1, root.right, res);
        }
    }


    /**
     * 二叉树最大深度
     *
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        int res = 0;
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        if (root == null) return res;
        linkedList.addLast(root);
        while (linkedList.size() > 0) {
            int size = linkedList.size();
            for (int i = 0; i < size; i++) {
                TreeNode first = linkedList.removeFirst();
                if (first.left != null) {
                    linkedList.addLast(first.left);
                }
                if (first.right != null) {
                    linkedList.addLast(first.right);
                }
            }
            res++;
        }
        return res;
    }

    /**
     * 二叉树最大深度
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    /**
     * 前序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        beforePrint(root, res);
        return res;
    }

    private void beforePrint(TreeNode root, List<Integer> res) {
        if (root != null) {
            res.add(root.val);
        }
        if (root.left != null) {
            beforePrint(root.left, res);
        }
        if (root.right != null) {
            beforePrint(root.right, res);
        }
    }


    //124. 二叉树中的最大路径和
    Integer res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxPathSumDfs(root);
        return res;
    }

    private int maxPathSumDfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftNum = Math.max(0, maxPathSumDfs(root.left));
        int rightNum = Math.max(0, maxPathSumDfs(root.right));
        res = Math.max(res, root.val + leftNum + rightNum);
        return root.val + Math.max(leftNum, rightNum);
    }

    //剑指offer  反转链表
    public static ListNode reverseList(ListNode head) {
        if (head == null) return head;
        ListNode pre = null;
        while (head.next != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        head.next = pre;
        return head;
    }

    //剑指offer  找到数组中任意一个重复的数字
    public int findRepeatNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return nums[i];
            } else {
                set.add(nums[i]);
            }
        }
        return -1;
    }


    //剑指offer  连续子数组的最大和
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int length = nums.length;
        int[] arr = new int[length];
        int res = nums[0];
        arr[0] = nums[0];
        for (int i = 1; i < length; i++) {
            arr[i] = Math.max(arr[i - 1] + nums[i], nums[i]);
            res = Math.max(res, arr[i]);
        }
        return res;
    }


    //剑指offer  合并两个递增的有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode temp = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                temp.next = l1;
                temp = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                temp = l2;
                l2 = l2.next;
            }
        }
        if (l1 == null) {
            temp.next = l2;
        }
        if (l2 == null) {
            temp.next = l1;
        }
        return head.next;
    }


    public static int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1, x = 0;
        int[] res = new int[(r + 1) * (b + 1)];
        while (true) {
            for (int i = l; i <= r; i++) {
                res[x++] = matrix[t][i];
            }// left to right.
            if (++t > b) break;
            for (int i = t; i <= b; i++) {
                res[x++] = matrix[i][r];
            } // top to bottom.
            if (l > --r) break;
            for (int i = r; i >= l; i--) {
                res[x++] = matrix[b][i];
            } // right to left.
            if (t > --b) break;
            for (int i = b; i >= t; i--) {
                res[x++] = matrix[i][l];
            } // bottom to top.
            if (++l > r) break;
        }
        return res;
    }

    //剑指offer 链表倒数第k节点
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) return null;
        ListNode pre = head;
        ListNode next = head;
        for (int i = 0; i < k; i++) {
            if (next.next == null) {
                return pre;
            }
            next = next.next;
        }
        while (next != null) {
            pre = pre.next;
            next = next.next;
        }
        return pre;
    }


    //剑指 Offer 48. 最长不含重复字符的子字符串,滑动窗口
    public static int lengthOfLongestSubstring(String s) {
        int res = 1;
        int start = 0;
        int end = 0;
        if (s == null || s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        map.put(s.charAt(0), 0);
        for (int i = 1; i < s.length(); i++) {
            end = i;
            Character c = s.charAt(i);
            Integer temp = map.get(c);
            if (temp != null && temp >= 0) {
                start = Math.max(start, temp + 1);
            }
            res = Math.max(res, end - start + 1);
            map.put(c, i);
        }
        return res;
    }


    //剑指offer  根据前序中序遍历重建二叉树
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        //中序遍历的索引map
        Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        int length = inorder.length;
        for (int i = 0; i < length; i++) {
            indexMap.put(inorder[i], i);
        }
        TreeNode root = buildTree(preorder, 0, length - 1, 0, length - 1, indexMap);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int inStart, int inEnd, Map<Integer, Integer> indexMap) {
        if (preStart > preEnd) {
            return null;
        }
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        if (preStart == preEnd) {
            return root;
        } else {
            int rootIndex = indexMap.get(rootVal);
            int leftNodes = rootIndex - inStart, rightNodes = inEnd - rootIndex;
            TreeNode leftSubtree = buildTree(preorder, preStart + 1, preStart + leftNodes, inStart, rootIndex - 1, indexMap);
            TreeNode rightSubtree = buildTree(preorder, preEnd - rightNodes + 1, preEnd, rootIndex + 1, inEnd, indexMap);
            root.left = leftSubtree;
            root.right = rightSubtree;
            return root;
        }
    }


    //剑指offer  青蛙跳台阶
    public int numWays(int n) {
        if (n == 0) return 1;
        if (n == 1) return 1;
        if (n == 2) return 2;
        int[] arr = new int[n + 1];
        arr[1] = 1;
        arr[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            arr[i] = (arr[i - 1] % 1000000007 + arr[i - 2] % 1000000007) % 1000000007;
        }
        return arr[n];
    }


    //剑指offer 反转打印链表
    public int[] reversePrint(ListNode head) {
        if (head == null) return new int[0];
        ListNode pre = null;
        int size = 0;
        while (head != null) {
            size++;
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = pre.val;
            pre = pre.next;
        }
        return arr;
    }

    //剑指offer  找到最小的k个数
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] res = new int[k];
        //快排
        quickSort(arr, 0, arr.length - 1);
        System.arraycopy(arr, 0, res, 0, k);
        return res;
    }

    private static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            //不用包含index，因为他前面都比他小后面都比他大
            int index = partition(arr, start, end);
            quickSort(arr, start, index - 1);
            quickSort(arr, index + 1, end);
        }
    }
    //取第一个数，然后比他小的放到左边，打的放到右边并返回交换后的索引
    private static int partition(int[] arr, int start, int end) {
        int res = start;
        int temp = start + 1;
        for (int i = start + 1; i < end + 1; i++) {
            if (arr[i] < arr[start]) {
                swap(arr, temp, i);
                temp++;
            }
        }
        swap(arr, res, temp - 1);
        return temp - 1;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 1};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("-");
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
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
