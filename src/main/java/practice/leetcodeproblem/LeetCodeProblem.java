package practice.leetcodeproblem;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class LeetCodeProblem {
    public static void leetcode2601() {
        int[] arr1 = {4, 9, 6, 10};
        System.out.println(primeSubOperation(arr1));
//        int[] arr2 = {6, 8, 11, 12};
//        System.out.println(primeSubOperation(arr2));
//        int[] arr3 = {5, 8, 3};
//        System.out.println(primeSubOperation(arr3));
//        int[] arr4 = {5};
//        System.out.println(primeSubOperation(arr4));
//        int[] arr5 = {5, 3};
//        System.out.println(primeSubOperation(arr5));
    }

    public static boolean primeSubOperation(int[] nums) {
        if (nums.length == 1) {
            return true;
        }

        List<Integer> allEligiblePrimes = new ArrayList<>();
        for (int i = 2; i < 1010; i++) {
            if (isPrime(i)) {
                allEligiblePrimes.add(i);
            }
        }

        int currentMax = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < allEligiblePrimes.size() - 1; j++) {
                if (
                        nums[i] - allEligiblePrimes.get(j) > currentMax
                                && nums[i] - allEligiblePrimes.get(j + 1) <= currentMax
                ) {
                    nums[i] = nums[i] - allEligiblePrimes.get(j);
                }
            }
            currentMax = nums[i];
        }

        // check nums is strictly increase or not
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                return false;
            }
        }

        return true;
    }

    private static boolean isPrime(int n) {
        if (n == 1) {
            return false;
        }

        if (n == 2) {
            return true;
        }

        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void leetcode207() {
        // test case 1: numCourses = 2, prerequisites = [[1,0]]
//        int[] arr1 = {1, 0};
//        int[][] arr = {arr1};

        // test case 2: numCourses = 2, prerequisites = [[1,0], [0, 1]
        int[] arr1 = {1, 0};
        int[] arr2 = {0, 1};
        int[][] arr = {arr1, arr2};

        // test case 3: n = 5, [[1,4],[2,4],[3,1],[3,2]]
//        int[] arr1 = {1, 4};
//        int[] arr2 = {2, 4};
//        int[] arr3 = {3, 1};
//        int[] arr4 = {3, 2};
//
//        int[][] arr = {arr1, arr2, arr3, arr4};
        System.out.println(canFinish(2, arr));
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // update graph
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] inStack = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i] && hasCycleFromNode(graph, i, visited, inStack)) {
                return false;
            }
        }

        return true;
    }

    private static boolean hasCycleFromNode(
            List<List<Integer>> graph,
            int start,
            boolean[] visited,
            boolean[] inStack
    ) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int node = stack.peek();

            // If we haven't visited this node, mark it as visited and add to stack path
            if (!visited[node]) {
                visited[node] = true;
                inStack[node] = true;
            }

            boolean hasUnvisitedNeighbor = false;
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    stack.push(neighbor);
                    hasUnvisitedNeighbor = true;
                    break;
                } else if (inStack[neighbor]) {
                    // Found a cycle
                    return true;
                }
            }

            // If no unvisited neighbors, pop the node and mark it as not in the current path
            if (!hasUnvisitedNeighbor) {
                stack.pop();
                inStack[node] = false;
            }
        }

        return false;
    }

    public static boolean canFinish1(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // update graph
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];

        // Call the recursive helper function to
        // detect cycle in different DFS trees
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i] && isCyclicUtil(graph, i, visited, recStack)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isCyclicUtil(List<List<Integer>> adj, int u,
                                        boolean[] visited, boolean[] recStack) {

        if (!visited[u]) {

            // Mark the current node as visited
            // and part of recursion stack
            visited[u] = true;
            recStack[u] = true;

            // Recur for all the vertices adjacent
            // to this vertex
            for (int x : adj.get(u)) {
                if (!visited[x] &&
                        isCyclicUtil(adj, x, visited, recStack)) {
                    return true;
                } else if (recStack[x]) {
                    return true;
                }
            }
        }

        // Remove the vertex from recursion stack
        recStack[u] = false;
        return false;
    }

    public static boolean canFinish2(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // update graph
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (!isCircleCanFinish(graph, i, numCourses)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isCircleCanFinish(List<List<Integer>> graph, int start, int n) {
        Stack<Integer> stack = new Stack<>();
        stack.add(start);

        boolean[] visited = new boolean[n];
        List<Integer> over = new ArrayList<>();
        visited[start] = true;

        while (!stack.isEmpty()) {
            int current = stack.pop();
            for (int i = 0; i < graph.get(current).size(); i++) {
                over.add(current);
                int next = graph.get(current).get(i);
                if (over.contains(next)) {
                    return false;
                }
                if (!visited[next]) {
                    stack.add(next);
                    visited[next] = true;
                }


            }
        }

        return true;
    }

    public static void leetcode547() {
        // isConnected = [[1,1,0],[1,1,0],[0,0,1]]
//        int[] arr1 = {1, 1, 0};
//        int[] arr2 = {1, 1, 0};
//        int[] arr3 = {0, 0, 1};
//        int[][] arr = {arr1, arr2, arr3};

        // test case 2: [[1,0,0],[0,1,0],[0,0,1]]
        int[] arr1 = {1, 0, 0};
        int[] arr2 = {0, 1, 0};
        int[] arr3 = {0, 0, 1};
        int[][] arr = {arr1, arr2, arr3};
        System.out.println(findCircleNum(arr));
    }

    public static int findCircleNum(int[][] isConnected) {
        // step 1: convert to List List Integer graph;
        int graphLength = isConnected.length;
        List<List<Integer>> graph = new ArrayList<>();

        List<Integer> vertexes = new ArrayList<>();

        for (int i = 0; i < graphLength; i++) {
            graph.add(new ArrayList<>());
            vertexes.add(i);
        }

        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected[i].length; j++) {
                if ((i != j) && (isConnected[i][j] == 1)) {
                    graph.get(i).add(j);
                }
            }
        }
        // done created graph

        // step 2: traversal all vertexes not visited and count for each done one
        int count = 0;

        while (!vertexes.isEmpty()) {
            boolean[] visited = bfs(graph, vertexes.get(0), graphLength);

            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    vertexes.remove((Integer) i);
                }
            }
            count++;
        }

        return count;
    }

    public static boolean[] bfs(List<List<Integer>> graph, int start, int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        boolean[] visited = new boolean[n];
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int i = 0; i < graph.get(current).size(); i++) {
                int next = graph.get(current).get(i);
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }

        return visited;
    }

    public static void leetcode3133() {
        System.out.println(minEnd(3, 4));
    }

    public static long minEnd(int n, int x) {
        long result = x;
        long m = n - 1;
        int currentIndex = 0;

        while (m > 0) {
            while ((result & (1L << currentIndex)) != 0) {
                currentIndex++;
            }
            long lastBit = m & 1;
            m = m >> 1;
            result = result | (lastBit << currentIndex);
            currentIndex++;
        }

        return result;
    }

    public static long minEnd2(int n, int x) {
        StringBuilder sbX = new StringBuilder(Integer.toBinaryString(x));
        StringBuilder sbRemaining = new StringBuilder(Integer.toBinaryString(n - 1));

        int count = sbRemaining.length() - 1;
        for (int i = sbX.length() - 1; i > 0; i--) {
            if (sbX.charAt(i) == '0') {
                sbX.setCharAt(i, sbRemaining.charAt(count));
                count--;
                if (count < 0) {
                    return binaryToDouble(sbX.toString());
                }
            }
        }

        String remainStr = sbRemaining.substring(0, count + 1) + sbX;

        return binaryToDouble(remainStr);
    }

    public static long binaryToDouble(String binary) {
        char[] numbers = binary.toCharArray();
        long result = 0;
        for (int i = numbers.length - 1; i >= 0; i--)
            if (numbers[i] == '1')
                result += (long) Math.pow(2, (numbers.length - i - 1));
        return result;
    }

    public static long binaryToDouble2(String[] binaries) {
        long result = 0;
        for (int i = binaries.length - 1; i >= 0; i--)
            if (binaries[i].equals("1"))
                result += (long) Math.pow(2, (binaries.length - i - 1));
        return result;
    }


    public static void leetcode202() {
        System.out.println(isHappy(14));
    }

    // n = 12356
    // step = n % 10 = 6
    // n = n / 10 = 1235
    // step = n

    public static boolean isHappy(int n) {
        List<Integer> calculated = new ArrayList<>();

        while (!calculated.contains(n)) {
            calculated.add(n);
            int cal = 0;
            while (n != 0) {
                int current = n % 10;
                cal = cal + current * current;
                n = n / 10;
            }

            if (cal == 1) {
                System.out.println(calculated);
                return true;
            }

            n = cal;
        }

        System.out.println(calculated);
        return false;
    }

    public static void leetcode1971() {
        // [[4,3],[1,4],[4,8],[1,7],[6,4],[4,2],[7,4],[4,0],[0,9],[5,4]]
        int[] arr1 = {4, 3};
        int[] arr2 = {1, 4};
        int[] arr3 = {4, 8};
        int[] arr4 = {1, 7};
        int[] arr5 = {6, 4};
        int[] arr6 = {4, 2};
        int[] arr7 = {7, 4};
        int[] arr8 = {4, 0};
        int[] arr9 = {0, 9};
        int[] arr10 = {5, 4};

        int[][] arr = {arr1, arr2, arr3, arr4, arr5, arr6, arr7, arr8, arr9, arr10};

//        int[] arr1 = {0, 1};
//        int[] arr2 = {1, 2};
//        int[] arr3 = {2, 0};
//        int[][] arr = {arr1, arr2, arr3};

        System.out.println(validPath(10, arr, 5, 9));
    }

    public static boolean validPath(int n, int[][] edges, int source, int destination) {
        if (source == destination) {
            return true;
        }

        if (edges.length == 0) {
            return false;
        }


        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] ints : edges) {
            graph.get(ints[0]).add(ints[1]);
            graph.get(ints[1]).add(ints[0]);
        }

        Stack<Integer> stack = new Stack<>();
        stack.add(source);

        boolean[] visited = new boolean[n];

        while (!stack.empty()) {
            int currentVertex = stack.pop();
            if (!visited[currentVertex]) {
                visited[currentVertex] = true;
                for (int i = 0; i < graph.get(currentVertex).size(); i++) {
                    int nextVertex = graph.get(currentVertex).get(i);
                    if (nextVertex == destination) {
                        return true;
                    }

                    if (!visited[graph.get(currentVertex).get(i)]) {
                        stack.add(nextVertex);
                    }
                }
            }
        }

        return false;
    }

    public static boolean depthFirstSearchInGraphList(
            List<List<Integer>> graph,
            int start,
            int end,
            List<Integer> visited
    ) {
        if (!visited.contains(start)) {
            visited.add(start);
        }

        visited.add(end);

        for (int i = 0; i < graph.get(start).size(); i++) {
            Integer temp = graph.get(start).get(i);
            if (temp == end) {
                return true;
            }

            if (!visited.contains(end)) {
                visited.add(end);
                if (depthFirstSearchInGraphList(graph, temp, end, visited)) {
                    return true;
                }
            }

        }

        return false;
    }

    public static boolean validPath2(int n, int[][] edges, int source, int destination) {
        // time limit exceeded
        // create new List from edges to use contains method
        List<List<Integer>> edgesList = new ArrayList<>();
        for (int[] ints : edges) {
            List<Integer> newInts = new ArrayList<>();
            newInts.add(ints[0]);
            newInts.add(ints[1]);
            edgesList.add(newInts);
        }

        List<Integer> vertexesSource = new ArrayList<>();
        vertexesSource.add(source);

        for (int i = 0; i < edgesList.size(); i++) {
            if (vertexesSource.contains(edgesList.get(i).get(0))
                    || vertexesSource.contains(edgesList.get(i).get(1))) {
                if (!vertexesSource.contains(edgesList.get(i).get(0))) {
                    vertexesSource.add(edgesList.get(i).get(0));
                }
                if (!vertexesSource.contains(edgesList.get(i).get(1))) {
                    vertexesSource.add(edgesList.get(i).get(1));
                }
                edgesList.remove(i);
                i = -1;
            }
        }

        return vertexesSource.contains(destination);
    }

    public static void leetcode1414() {
        System.out.println(findMinFibonacciNumbers(1));
    }

    public static int findMinFibonacciNumbers(int k) {
        int minNumbers = 1;
        int count = 0;
        int currentSum = 0;
        List<Integer> storePreviousValue = new ArrayList<>();
        while (currentSum < k) {
            count++;
            storePreviousValue.add(fib(count));
            currentSum = storePreviousValue.get(storePreviousValue.size() - 1);
        }

        if (
                storePreviousValue.get(storePreviousValue.size() - 1) == k
        ) {
            return 1;
        }

        storePreviousValue.remove(storePreviousValue.size() - 1);

        if (storePreviousValue.get(storePreviousValue.size() - 1) == k) {
            return 1;
        }

        int remaining = k - storePreviousValue.get(storePreviousValue.size() - 1);
        while (remaining != 0) {
            for (int i = 0; i < storePreviousValue.size() - 1; i++) {
                if (
                        storePreviousValue.get(i) <= remaining
                                && storePreviousValue.get(i + 1) > remaining
                ) {
                    remaining = remaining - storePreviousValue.get(i);
                    minNumbers++;
                }
            }
        }
        return minNumbers;
    }

    public static int fib(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        // memo previous calculation
        List<Integer> previousResult = new ArrayList<>();
        previousResult.add(0);
        previousResult.add(1);

        return supportFibonacci(n, previousResult);
    }

    public static int supportFibonacci(int n, List<Integer> memo) {
        if (n <= memo.size() - 1) {
            return memo.get(n);
        }

        memo.add(
                supportFibonacci(n - 1, memo)
                        + supportFibonacci(n - 2, memo));

        return memo.get(n);
    }

    public static void leetcode1531() {
        System.out.println(getLengthOfOptimalCompression("aaabcccd", 2));
    }

    public static int getLengthOfOptimalCompression(String s, int k) {
        List<String> allCases = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (i + k > s.length()) {
                String currentCase = s.substring(k - (s.length() - i - 1), i + 1);
                allCases.add(currentCase);
                continue;
            }
            String currentCase = s.substring(0, i) + s.substring(i + k);
            allCases.add(currentCase);
        }

        System.out.println(allCases);
        return 0;
    }

    public static String runLengthEncoding(String s) {
        if (s.length() == 1) {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                sb.append(s.charAt(i - 1));
                if (count != 1) {
                    sb.append(count);
                }
                count = 1;

                if (i == s.length() - 1) {
                    sb.append(s.charAt(i));
                }
            } else {
                count++;

                if (i == s.length() - 1) {
                    sb.append(s.charAt(i));
                    sb.append(count);
                }

            }
        }

        return sb.toString();
    }

    public static int compress(char[] chars) {
        if (chars.length == 1) {
            return 1;
        }

        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] != chars[i - 1]) {
                sb.append(chars[i - 1]);
                if (count != 1) {
                    sb.append(count);
                }
                count = 1;

                if (i == chars.length - 1) {
                    sb.append(chars[i]);
                    if (count != 1) {
                        sb.append(count);
                    }
                    break;
                }
            } else {
                count++;

                if (i == chars.length - 1) {
                    sb.append(chars[i]);
                    sb.append(count);
                }

            }
        }

        for (int i = 0; i < sb.length(); i++) {
            chars[i] = sb.charAt(i);
        }

        return sb.length();
    }

    public static void leetcode125() {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("race a car"));
        System.out.println(isPalindrome(" "));

    }

    public static boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (
                    (s.charAt(i) >= 48 && s.charAt(i) <= 57)
                            || (s.charAt(i) >= 97 && s.charAt(i) <= 122)
            ) {
                sb.append(s.charAt(i));
            } else if (s.charAt(i) >= 65 && s.charAt(i) <= 90) {
                sb.append((char) (s.charAt(i) + 32));
            }
        }
        String forward = sb.toString();

        StringBuilder sbRevert = new StringBuilder();
        for (int i = forward.length() - 1; i >= 0; i--) {
            sbRevert.append(forward.charAt(i));
        }
        String backward = sbRevert.toString();

        return forward.equals(backward);
    }

    public static void leetcode119() {
        System.out.println(getRow(5));
        System.out.println(getRow(3));
        System.out.println(getRow(0));

        System.out.println(getRow(1));
    }

    public static List<Integer> getRow(int rowIndex) {
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        if (rowIndex == 0) {
            return firstRow;
        }

        List<Integer> secondRow = new ArrayList<>();
        secondRow.add(1);
        secondRow.add(1);

        if (rowIndex == 1) {
            return secondRow;
        }

        List<Integer> previousRow = getRow(rowIndex - 1);
        List<Integer> nextRow = new ArrayList<>();
        nextRow.add(1);
        for (int i = 0; i < previousRow.size() - 1; i++) {
            nextRow.add(previousRow.get(i) + previousRow.get((i + 1)));
        }
        nextRow.add(1);

        return nextRow;
    }

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
