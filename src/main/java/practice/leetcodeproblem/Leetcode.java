package practice.leetcodeproblem;

import edu.princeton.cs.algs4.In;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Leetcode {
    public static void log() {
        System.out.println("Leetcode");
//        int[] nums1 = {2, 0, 2};
//        int[] query11 = {0, 2, 1};
//        int[] query12 = {0, 2, 1};
//        int[] query13 = {1, 1, 3};
//        int[][] queries1 = {query11, query12, query13};
//        System.out.println(minZeroArray(nums1, queries1));
//
//        int[] nums2 = {4, 3, 2, 1};
//        int[] query21 = {1, 3, 2};
//        int[] query22 = {0, 2, 1};
//        int[][] queries2 = {query21, query22};
//        System.out.println(minZeroArray(nums2, queries2));
//
//        int[] nums3 = {3, 6, 5};
//        int[] query31 = {0, 2, 1};
//        int[] query32 = {0, 2, 2};
//        int[] query33 = {1, 2, 3};
//        int[][] queries3 = {query31, query32, query33};
//        System.out.println(minZeroArray(nums3, queries3));
//
//        int[] nums4 = {0};
//        int[] query41 = {0, 0, 2};
//        int[] query42 = {0, 0, 4};
//        int[] query43 = {0, 0, 4};
//        int[][] queries4 = {query41, query42, query43};
//        System.out.println(minZeroArray(nums4, queries4));

        //[7,6,8]
        //queries =
        //[[0,0,2],[0,1,5],[2,2,5],[0,2,4]]
//        int[] nums1 = {-2, 3, 2, -5, 6, 8, 3};
//        System.out.println(shortestSubarray(nums1, 8));
        int[] nums2 = {-34, 37, 51, 3, -12, -50, 51, 100, -47, 99, 34, 14, -13, 89, 31, -14, -44, 23, -38, 6};
        System.out.println(shortestSubarray(nums2, 151));
//        int[] nums3 = {2, -1, 2};
//        System.out.println(shortestSubarray(nums3, 3));

//        int[] temperatures1 = {73, 74, 75, 71, 69, 72, 76, 73};
//        System.out.println(Arrays.toString(dailyTemperatures(temperatures1)));
//        int[] temperatures2 = {30, 40, 50, 60};
//        System.out.println(Arrays.toString(dailyTemperatures(temperatures2)));
//        int[] temperatures3 = {30};
//        System.out.println(Arrays.toString(dailyTemperatures(temperatures3)));
    }

    public static int shortestSubarray(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int currentSum = 0;
        Deque<Pair<Integer, Integer>> q = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {
            currentSum = currentSum + nums[i];

            if (currentSum >= k) {
                min = Math.min(min, i + 1);
            }

            // Find the minimum valid window ending at r
            while (!q.isEmpty() && currentSum - q.peekFirst().getValue() >= k) {
                Pair<Integer, Integer> front = q.pollFirst();
                min = Math.min(min, i - front.getKey());
            }

            // Validate the monotonic deque
            while (!q.isEmpty() && q.peekLast().getValue() > currentSum) {
                q.pollLast();
            }
            q.offerLast(new Pair<>(i, currentSum));
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<Pair<Integer, Integer>> stack = new Stack<>();

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > stack.peek().getValue()) {
                Pair<Integer, Integer> first = stack.pop();
                result[first.getKey()] = i - first.getKey();
            }
            stack.add(new Pair<>(i, temperatures[i]));
        }

        return result;
    }

    public static int shortestSubarray2(int[] nums, int k) {
        int res = Integer.MAX_VALUE;
        long curSum = 0;
        Deque<Pair<Long, Integer>> q = new ArrayDeque<>();  // (prefix_sum, end_idx)

        for (int r = 0; r < nums.length; r++) {
            curSum += nums[r];

            if (curSum >= k) {
                res = Math.min(res, r + 1);
            }

            // Find the minimum valid window ending at r
            while (!q.isEmpty() && curSum - q.peekFirst().getKey() >= k) {
                Pair<Long, Integer> front = q.pollFirst();
                res = Math.min(res, r - front.getValue());
            }

            // Validate the monotonic deque
            while (!q.isEmpty() && q.peekLast().getKey() > curSum) {
                q.pollLast();
            }
            q.offerLast(new Pair<>(curSum, r));
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    // Helper class to store pairs
    static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public static int shortestSubarray3(int[] nums, int k) {
        if (nums.length == 1) {
            return nums[0] == k ? 1 : -1;
        }

        int min = Integer.MAX_VALUE;
        int start = 0;
        int end = 1;
        int sum = nums[0];
        int countNumberLessThanZero = nums[0] < 0 ? 1 : 0;

        while (end <= nums.length) {
            if (sum < k) {
                if (countNumberLessThanZero > 0 && start < end - 1) {
                    start++;
                    sum = sum - nums[start - 1];
                    if (nums[start - 1] < 0) {
                        countNumberLessThanZero--;
                    }
                } else {
                    end++;
                    if (end <= nums.length) {
                        sum = sum + nums[end - 1];
                        if (nums[end - 1] < 0) {
                            countNumberLessThanZero++;
                        }
                    }
                }
            } else {
                min = Math.min(min, end - start);
                if (start < end - 1) {
                    start++;
                    sum = sum - nums[start - 1];
                    if (nums[start - 1] < 0) {
                        countNumberLessThanZero--;
                    }
                } else {
                    end++;
                    if (end <= nums.length) {
                        sum = sum + nums[end - 1];
                        if (nums[end - 1] < 0) {
                            countNumberLessThanZero++;
                        }
                    }
                }

            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static int minZeroArrayFromOtherCandidate(int[] nums, int[][] queries) {
        int l = 0;
        int r = queries.length;
        while (l < r) {
            int mid = (l + r) / 2;
            long[] arr = new long[nums.length];
            for (int i = 0; i < mid; i++) {
                arr[queries[i][0]] += queries[i][2];
                if (queries[i][1] < nums.length - 1) {
                    arr[queries[i][1] + 1] -= queries[i][2];
                }
            }
            for (int i = 1; i < arr.length; i++) {
                arr[i] += arr[i - 1];
            }
            boolean good = true;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] < nums[i]) good = false;
            }
            if (good) r = mid;
            else l = mid + 1;
        }
        long[] arr = new long[nums.length];
        for (int i = 0; i < l; i++) {
            arr[queries[i][0]] += queries[i][2];
            if (queries[i][1] < nums.length - 1) {
                arr[queries[i][1] + 1] -= queries[i][2];
            }
        }
        for (int i = 1; i < arr.length; i++) {
            arr[i] += arr[i - 1];
        }
        boolean good = true;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < nums[i]) good = false;
        }
        if (good) return l;
        return -1;
    }

    public static int minZeroArray(int[] nums, int[][] queries) {
        int[] newArray = new int[nums.length];
        if (checkZeroArray(nums, newArray)) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int left = 0;
        int right = queries.length - 1;
        while (true) {
            int currentPosition = (left + right) / 2;
            int[][] subQueries = Arrays.copyOfRange(queries, 0, currentPosition + 1);

            if (isValidZeroArray(nums, subQueries)) {
                min = currentPosition + 1;
                if (currentPosition == left || currentPosition == right) {
                    break;
                }
                right = currentPosition;
            } else {
                if (currentPosition == right) {
                    break;
                }
                left = currentPosition + 1;
            }


        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private static boolean isValidZeroArray(int[] nums, int[][] queries) {
        int[] arr = new int[nums.length];
        for (int[] query : queries) {
            arr[query[0]] = arr[query[0]] + query[2];
            if (query[1] < nums.length - 1) {
                arr[query[1] + 1] = arr[query[1] + 1] - query[2];
            }
        }

        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i] + arr[i - 1];
        }
        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > arr[i]) {
                return false;
            }
        }

        return true;
    }

    public static int minZeroArray2(int[] nums, int[][] queries) {
        int[] newArray = new int[nums.length];
        if (checkZeroArray(nums, newArray)) {
            return 0;
        }

        for (int i = 0; i < queries.length; i++) {
            for (int j = queries[i][0]; j <= queries[i][1]; j++) {
                newArray[j] = newArray[j] + queries[i][2];
            }

            if (checkZeroArray(nums, newArray)) {
                return i + 1;
            }
        }

        return -1;
    }

    private static boolean checkZeroArray(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] > b[i]) {
                return false;
            }
        }

        return true;
    }

    public static int countValidSelections(int[] nums) {
        if (nums.length == 1) {
            return 2;
        }

        int sum = 0;
        for (int j : nums) {
            sum = sum + j;
        }

        int count = 0;
        int currentSum = 0;
        for (int i = 0; i < nums.length; i++) {
            currentSum = currentSum + nums[i];
            if (nums[i] == 0) {
                if (currentSum == sum - currentSum) {
                    count = count + 2;
                }
                if (Math.abs(sum - 2 * currentSum) == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    private static int sumArray(int[] arr) {
        int sum = 0;
        for (int j : arr) {
            sum = sum + j;
        }

        return sum;
    }

    public static boolean isZeroArray(int[] nums, int[][] queries) {
        int[] arr = new int[nums.length];
        for (int[] query : queries) {
            arr[query[0]] = arr[query[0]] + 1;
            if (query[1] < nums.length - 1) {
                arr[query[1] + 1] = arr[query[1] + 1] - 1;
            }
        }

        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i] + arr[i - 1];
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > arr[i]) {
                return false;
            }
        }

        return true;
    }

    public static int findLengthOfShortestSubarray(int[] arr) {
        if (arr.length == 1) {
            return 0;
        }

        int left = 0;
        while (left < arr.length - 1 && arr[left] <= arr[left + 1]) {
            left++;
        }

        if (left == arr.length - 1) {
            return 0;
        }

        int right = arr.length - 1;
        while (right > 0 && arr[right] >= arr[right - 1]) {
            right--;
        }

        int min = Math.min(arr.length - left - 1, right);

        int i = 0, j = right;
        while (i <= left && j < arr.length) {
            if (arr[i] <= arr[j]) {
                min = Math.min(min, j - i - 1);
                i++;
            } else {
                j++;
            }
        }

        return min;
    }

    // exceeded time limit
    public static int findLengthOfShortestSubarray2(int[] arr) {
        if (arr.length == 1) {
            return 0;
        }

        int start = 0;
        int end = 0;
        int min = Integer.MAX_VALUE;

        while (start <= arr.length) {
            if (isSorted(Arrays.copyOfRange(arr, 0, end), Arrays.copyOfRange(arr, start, arr.length))) {
                min = Math.min(min, start - end);
                if (end < start) {
                    end++;
                    continue;
                }
                start++;
            }

            start++;

        }

        return min;
    }

    private static boolean isSorted(int[] a, int[] b) {
        if (a.length >= 2) {
            for (int i = 1; i < a.length; i++) {
                if (a[i] < a[i - 1]) {
                    return false;
                }
            }
        }

        if (a.length != 0 && b.length != 0 && b[0] < a[a.length - 1]) {
            return false;
        }

        if (b.length >= 2) {
            for (int i = 1; i < b.length; i++) {
                if (b[i] < b[i - 1]) {
                    return false;
                }
            }
        }

        return true;
    }
}
