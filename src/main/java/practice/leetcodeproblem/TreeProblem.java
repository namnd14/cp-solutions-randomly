package practice.leetcodeproblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TreeProblem {
    public void log() {
        System.out.println("Tree problem!");
        // root = [1,3,null,null,2]
//        TreeNode a = new TreeNode(-33);
//        TreeNode b = new TreeNode(321, a, null);
//        TreeNode c = new TreeNode(55, b, null);
//        TreeNode d = new TreeNode(71, c, null);
//        TreeNode e = new TreeNode(231);
//        TreeNode f = new TreeNode(399);
//        TreeNode g = new TreeNode(-13, e, f);
//        TreeNode i = new TreeNode(146, d, g);
//
//        recoverTree(i);
        // [5,3,9,-2147483648,2]
        TreeNode a = new TreeNode(5);
        TreeNode b = new TreeNode(2, null, a);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(1, b, c);
        // TreeNode e = new TreeNode(5, c, d);

        System.out.println(binaryTreePaths(d));
        System.out.println("Done");
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        String currentPath = "";
        currentPath += root.val;
        if (root.left == null && root.right == null) {
            result.add(currentPath);
            return result;
        }

        buildPathByDFS(root.left, result, currentPath);
        buildPathByDFS(root.right, result, currentPath);

        return result;
    }

    private void buildPathByDFS(TreeNode root, List<String> result, String s) {
        if (root == null) {
            return;
        }

        s += "->" + root.val;
        if (root.left == null && root.right == null) {
            if (!result.contains(s)) {
                result.add(s);
            }
            return;
        }

        buildPathByDFS(root.left, result, s);
        buildPathByDFS(root.right, result, s);
    }

    public void recoverTree(TreeNode root) {
        List<Integer> arr = new ArrayList<>();
        buildArrayByDFS(arr, root.left);
        arr.add(root.val);
        buildArrayByDFS(arr, root.right);
        List<Integer> wrongNodes = new ArrayList<>();
        List<Integer> copyArr = new ArrayList<>(arr.size());
        for (Integer item : arr) {
            copyArr.add(item);
        }

        copyArr.sort(null);

        for (int i = 0; i < arr.size(); i++) {
            if (!arr.get(i).equals(copyArr.get(i))) {
                wrongNodes.add(arr.get(i));
            }
        }
        traversalDFSAndChangeValue(wrongNodes.get(0), wrongNodes.get(1), root);
        System.out.println("Debug");
    }

    public void buildArrayByDFS(List<Integer> arr, TreeNode root) {
        if (root == null) {
            return;
        }

        buildArrayByDFS(arr, root.left);
        arr.add(root.val);
        buildArrayByDFS(arr, root.right);
    }

    public void traversalDFSAndChangeValue(int first, int second, TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.val == first) {
            root.val = second;
        } else if (root.val == second) {
            root.val = first;
        }
        traversalDFSAndChangeValue(first, second, root.left);
        traversalDFSAndChangeValue(first, second, root.right);
    }


}
