package com.hk.mt;

import java.util.*;

/**
 *
 */
public class Test {

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * 链接：https://leetcode-cn.com/problems/two-sum
     */
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(target - num)) {
                res[0] = map.get(target - num);
                res[1] = i;
                return res;

            } else {
                map.put(num, i);
            }
        }
        return res;
    }


    /**
     * 双指针 滑动窗口
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) return 1;
        HashSet<Character> set = new HashSet<>();
        int left = 0;
        int right = 1;
        int res = 1;
        set.add(s.charAt(0));
        while (right < s.length()) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            res = Math.max(res, right - left + 1);
            right++;

        }
        return res;
    }

    /**
     * 以xx结尾的最大和动态规划
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 示例
     * 输入: [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * 链接：https://leetcode-cn.com/problems/maximum-subarray
     */
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] arr = new int[nums.length];
        int res = arr[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            arr[i] = Math.max(arr[i - 1] + nums[i], nums[i]);
            res = Math.max(res, arr[i]);
        }
        return res;
    }

    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
     * 示例：
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode temp = head;
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        temp.next = l1 == null ? l2 : l1;
        return head.next;
    }


    /**
     * 反转一个单链表。
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     * 进阶:
     * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
     * 链接：https://leetcode-cn.com/problems/reverse-linked-list
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode pre = null;
        ListNode now = head;
        while (now != null) {
            ListNode nowNext = now.next;
            now.next = pre;
            pre = now;
            now = nowNext;
        }
        return pre;
    }

    /**
     * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
     * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     * 链接：https://leetcode-cn.com/problems/merge-sorted-array
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        //双指针从后往前遍历
        int point1 = m - 1;
        int point2 = n - 1;
        int point = m + n - 1;
        while (point1 >= 0 && point2 >= 0) {
            if (nums1[point1] > nums2[point2]) {
                nums1[point] = nums1[point1];
                point1--;
            } else {
                nums1[point] = nums2[point2];
                point2--;
            }
            point--;
        }
        //point2小于0的不用处理
        if (point1 < 0) {
            System.arraycopy(nums2, 0, nums1, 0, point2 + 1);
        }
    }

    /**
     * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
     * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
     * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] arr = new int[nums1.length + nums2.length];
        System.arraycopy(nums1, 0, arr, 0, nums1.length);
        System.arraycopy(nums2, 0, arr, nums1.length, nums2.length);
        Arrays.sort(arr);
        int length = arr.length;
        if (length == 0) return 0.0d;
        if (length % 2 == 0) {
            return (arr[length / 2] + arr[length / 2 - 1]) / 2.0d;
        } else {
            return arr[length / 2];
        }
    }

    //O(log (m+n))
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }

    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1];
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);
        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;
        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }


    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     * 链接：https://leetcode-cn.com/problems/add-two-numbers
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode node = head;
        //用来存商
        int temp = 0;
        while (l1 != null || l2 != null || temp > 0) {
            int num = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + temp;
            temp = num / 10;//商
            int b = num % 10;//余数
            node.next = new ListNode(b);
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


    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
     * 使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     * 链接：https://leetcode-cn.com/problems/3sum
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);//排序
        HashSet<Integer> set = new HashSet<>();
        //转为两数之和
        for (int i = 0; i < nums.length; i++) {
            int sum = -nums[i];
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] > 0) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int total = nums[left] + nums[right];
                if (left > i + 1 && nums[left] == nums[left - 1]) {
                    left++;
                    continue;
                }
                if (total == sum) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(list);
                    left++;
                    right--;
                } else if (total < sum) {
                    left++;
                } else {
                    right--;
                }
            }

        }
        return res;
    }

    /**
     * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //决策树
        LinkedList<Integer> track = new LinkedList<>();
        //可用元素集合
        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, track, res);
        return res;
    }

    private static void backtrack(int[] nums, boolean[] used, LinkedList<Integer> track, List<List<Integer>> res) {
        if (track.size() == nums.length) {
            res.addAll(Collections.singleton(new ArrayList<>(track)));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 排除不合法的选择
            if (used[i]) {
                continue;
            }
            // 做选择
            track.addLast(nums[i]);
            used[i] = true;
            // 进入下一层决策树
            backtrack(nums, used, track, res);
            // 取消选择
            track.removeLast();
            used[i] = false;
        }
    }

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;
        int length = s.length();
        boolean[][] arr = new boolean[length][length];
        String res = "";
        //边界单个字母或两个字母相同
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int start = j;
                int end = j + i;
                if (start > end || end > length - 1) {
                    continue;
                }
                if (end - start == 1) {
                    arr[start][end] = s.charAt(start) == s.charAt(end);
                } else if (end - start == 0) {
                    arr[start][end] = true;
                } else {
                    arr[start][end] = arr[start + 1][end - 1] && s.charAt(start) == s.charAt(end);
                }
                if (arr[start][end] && (end - start + 1) > res.length()) {
                    res = s.substring(start, end + 1);
                }
            }
        }
        return res;
    }


    /**
     * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();

        return res;
    }

    /**
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     * <p>
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     * <p>
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/unique-paths-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            arr[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                break;
            }
            arr[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    arr[i][j] = 0;
                } else {
                    arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
                }
            }
        }
        return arr[m - 1][n - 1];
    }

    /**
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int lengthOfLIS(int[] nums) {
        int length = nums.length;
        if (length <= 1) return length;

        int[] arr = new int[length];
        arr[0] = 1;
        int max = 1;
        for (int i = 1; i < length; i++) {
            arr[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    arr[i] = Math.max(arr[i], arr[j] + 1);
                }
            }
            max = Math.max(max, arr[i]);
        }
        return max;
    }

    /**
     * 给你一个整数数组 nums ，返回该数组所有可能的子集（幂集）。解集不能包含重复的子集。
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        //决策树
        LinkedList<Integer> track = new LinkedList<>();
        backtrack2(0,nums, track, res);
        return res;
    }

    private static void backtrack2(int start, int[] nums, LinkedList<Integer> track, List<List<Integer>> res) {
        if (start == nums.length-1) {
            res.addAll(Collections.singleton(new ArrayList<>(track)));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            // 做选择
            track.addLast(nums[i]);
            // 进入下一层决策树
            backtrack2(i+1, nums, track, res);
            // 取消选择
            track.removeLast();
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};
        List<List<Integer>> subsets = subsets(arr);
        System.out.println("-");

    }


    public class ListNode {
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