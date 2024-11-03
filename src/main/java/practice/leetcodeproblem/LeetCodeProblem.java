package practice.leetcodeproblem;

import com.sun.source.tree.Tree;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCodeProblem {
    public static void leetcode118() {
        System.out.println(generate(5));
        System.out.println(generate(1));
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> trianglePascal = new ArrayList<>();

        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        trianglePascal.add(firstRow);
        if (numRows == 1) {
            return trianglePascal;
        }

        List<Integer> secondRow = new ArrayList<>();
        secondRow.add(1);
        secondRow.add(1);
        trianglePascal.add(secondRow);

        if (numRows == 2) {
            return trianglePascal;
        }

        List<List<Integer>> previousTriangle = generate(numRows - 1);
        List<Integer> previousRow = previousTriangle.get(previousTriangle.size() - 1);
        List<Integer> nextRow = new ArrayList<>();
        nextRow.add(1);
        for (int i = 0; i < previousRow.size() - 1; i++) {
            nextRow.add(previousRow.get(i) + previousRow.get((i + 1)));
        }
        nextRow.add(1);
        previousTriangle.add(nextRow);

        return previousTriangle;
    }

    public static void leetcode169() {
        int[] nums = {2, 2, 1, 1, 2, 1, 2, 2};

        System.out.println(majorityElement(nums));
    }

    public static int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public static void leetcode108() {
        int[] nums = {-10, -3, 0, 5, 9};
        int[] nums2 = {-1, 0, 1, 2};
        TreeNode result = sortedArrayToBST(nums);
        System.out.println("Done");
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        int middleIndex = nums.length / 2;
        int rootValue = nums[middleIndex];
        TreeNode root = new TreeNode(rootValue);

        if (nums.length == 1) {
            return root;
        }

        if (nums.length == 2) {
            TreeNode left = new TreeNode(nums[0]);
            root.left = left;
            return root;
        }

        if (nums.length == 3) {
            TreeNode left = new TreeNode(nums[0]);
            TreeNode right = new TreeNode(nums[(nums.length / 2) + 1]);
            root.left = left;
            root.right = right;

            return root;
        }

        nums.clone();

        int[] leftArr = Arrays.copyOfRange(nums, 0, middleIndex);
        int[] rightArr = Arrays.copyOfRange(nums, middleIndex + 1, nums.length);

        root.left = sortedArrayToBST(leftArr);
        root.right = sortedArrayToBST(rightArr);

        return root;
    }

    public static void leetcode796() {
        System.out.println(rotateString("a", "a"));
        System.out.println(rotateString("a", "b"));
        System.out.println(rotateString("abcde", "cdeab"));
        System.out.println(rotateString("abcde", "abced"));
        System.out.println(rotateString("abaa", "aaab"));


    }

    public static boolean rotateString(String s, String goal) {
        // s = 100
        // for 0 -> 100 - 1
        // new array = 100 item
        // return boolean contains goal in array
        // a - c | b - d | c - e | d - a | e - b
        List<String> allPossibleRotateString = new ArrayList<>();
        String[] arr = s.split("");

        for (int i = 0; i < arr.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < arr.length; j++) {
                if (i + j >= arr.length) {
                    sb.append(arr[i + j - arr.length]);
                } else {
                    sb.append(arr[i + j]);
                }
            }
            allPossibleRotateString.add(sb.toString());
        }
        System.out.println(allPossibleRotateString);
        return allPossibleRotateString.contains(goal);
    }

    public static void leetcode104() {
//        TreeNode leaf31 = new TreeNode(3);
//        TreeNode leaf41 = new TreeNode(4);
//        TreeNode leaf42 = new TreeNode(4);
//        TreeNode leaf32 = new TreeNode(3);
//        TreeNode node21 = new TreeNode(2, leaf31, leaf41);
//        TreeNode node22 = new TreeNode(2, leaf42, leaf32);
//        TreeNode root = new TreeNode(1, node21, node22);

        // test case 2
        TreeNode leaf31 = new TreeNode(3);
        TreeNode leaf41 = new TreeNode(4);
        TreeNode leaf42 = new TreeNode(4);
        TreeNode leaf32 = new TreeNode(3);
        TreeNode node21 = new TreeNode(2, null, leaf31);
        TreeNode node22 = new TreeNode(2, null, leaf32);
        TreeNode root = new TreeNode(1, node21, node22);
        System.out.println(maxDepth(root));
    }

    public static int maxDepth(TreeNode root) {
        int maxDepth = 0;
        if (root == null) {
            return maxDepth;
        }

        List<TreeNode> currentLevel = new ArrayList<>();
        currentLevel.add(root);
        while (!currentLevel.isEmpty()) {
            maxDepth++;
            List<TreeNode> nextLevel = new ArrayList<>();
            for (TreeNode treeNode : currentLevel) {
                if (treeNode.left != null) {
                    nextLevel.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    nextLevel.add(treeNode.right);
                }
            }

            currentLevel = nextLevel;
        }

        return maxDepth;
    }

    public static void leetcode101() {
//        TreeNode leaf31 = new TreeNode(3);
//        TreeNode leaf41 = new TreeNode(4);
//        TreeNode leaf42 = new TreeNode(4);
//        TreeNode leaf32 = new TreeNode(3);
//        TreeNode node21 = new TreeNode(2, leaf31, leaf41);
//        TreeNode node22 = new TreeNode(2, leaf42, leaf32);
//        TreeNode root = new TreeNode(1, node21, node22);

        // test case 2
        TreeNode leaf31 = new TreeNode(3);
        TreeNode leaf41 = new TreeNode(4);
        TreeNode leaf42 = new TreeNode(4);
        TreeNode leaf32 = new TreeNode(3);
        TreeNode node21 = new TreeNode(2, null, leaf31);
        TreeNode node22 = new TreeNode(2, null, leaf32);
        TreeNode root = new TreeNode(1, node21, node22);
        System.out.println(isSymmetric(root));
    }

    public static boolean isSymmetric(TreeNode root) {
        return compareTwoNode(root.left, root.right);
    }

    public static boolean compareTwoNode(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null) {
            return false;
        }

        if (left.val != right.val) {
            return false;
        }

        if (!compareTwoNode(left.left, right.right)) {
            return false;
        }
        return compareTwoNode(left.right, right.left);
    }

    public static void leetcode83() {
        // Test case 1
//        ListNode node1 = new ListNode(2);
//        ListNode node2 = new ListNode(1, node1);
//        ListNode node3 = new ListNode(1, node2);

        // test case 2
        ListNode node31 = new ListNode(3);
        ListNode node32 = new ListNode(3, node31);

        ListNode node1 = new ListNode(2, node32);
        ListNode node2 = new ListNode(1, node1);
        ListNode node3 = new ListNode(1, node2);

        ListNode result = deleteDuplicates(node3);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static ListNode deleteDuplicates(ListNode head) {
        // zero item in linked list
        if (head == null) {
            return head;
        }

        // only 1 item in linked list
        if (head.next == null) {
            return head;
        }

        // store appeared item from linked list
        List<Integer> appeared = new ArrayList<>();
        appeared.add(head.val);
        // pointer to keep current ListNode
        ListNode tail = head.next;
        // pointer keep the previous ListNode of tail
        ListNode previous = head;

        while (tail != null) {
            if (appeared.contains(tail.val)) {
                previous.next = tail.next;
            } else {
                appeared.add(tail.val);
                previous = previous.next;
            }
            tail = tail.next;
        }

        return head;
    }

    public static void leetcode67() {
        System.out.println(addBinary("1010", "1011"));
        System.out.println(addBinary("0", "1"));
        System.out.println(addBinary("1", "0"));
        System.out.println(addBinary("1", "1"));
        System.out.println(addBinary("11", "11"));
        System.out.println(addBinary("11", "1"));

    }

    public static String addBinary(String a, String b) {
        StringBuilder sum = new StringBuilder();
        if (a.length() > b.length()) {
            int zeroNumNeeded = a.length() - b.length();
            for (int i = 0; i < zeroNumNeeded; i++) {
                b = "0" + b;
            }
        } else if (a.length() < b.length()) {
            int zeroNumNeeded = b.length() - a.length();
            for (int i = 0; i < zeroNumNeeded; i++) {
                a = "0" + a;
            }
        }
        boolean isAdd = false;
        int index = a.length() - 1;

        while (index >= 0) {
            if (a.charAt(index) == '1' && b.charAt(index) == '1') {
                if (isAdd) {
                    sum.insert(0, "1");
                } else {
                    sum.insert(0, "0");
                    isAdd = true;
                }
            } else if (
                    (a.charAt(index) == '1' && b.charAt(index) == '0')
                            || (a.charAt(index) == '0' && b.charAt(index) == '1')
            ) {
                if (isAdd) {
                    sum.insert(0, "0");
                } else {
                    sum.insert(0, "1");
                }
            } else {
                if (isAdd) {
                    sum.insert(0, "1");
                    isAdd = false;
                } else {
                    sum.insert(0, "0");
                }
            }
            index--;
        }

        if (isAdd) {
            sum.insert(0, "1");
        }

        return sum.toString();
    }

    public static void leetcode2490() {
        System.out.println(isCircularSentence("Leetcode eisc cooL"));
        System.out.println(isCircularSentence("Leetcode eisc cool"));

        System.out.println(isCircularSentence("leetcode"));
        System.out.println(isCircularSentence("eetcode"));
        System.out.println(isCircularSentence("leetcode exercises sound delightful"));

    }

    public static boolean isCircularSentence(String sentence) {
        if (sentence.charAt(0) != sentence.charAt(sentence.length() - 1)) {
            return false;
        }

        String[] words = sentence.split(" ");
        if (words.length == 1) {
            return true;
        }

        int index = 0;
        while (index < words.length - 1) {
            if (words[index].charAt(words[index].length() - 1) != words[index + 1].charAt(0)) {
                return false;
            }
            index++;
        }
        return true;
    }
}
