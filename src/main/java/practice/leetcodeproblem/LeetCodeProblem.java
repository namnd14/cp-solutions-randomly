package practice.leetcodeproblem;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class LeetCodeProblem {
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
