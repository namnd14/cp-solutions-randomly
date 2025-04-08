package practice.leetcodeproblem;

import java.util.*;

public class Leetcode {
    public void log() {
        System.out.println(minimumOperations2(new int[]{4, 5, 6, 4, 4}));

    }

    public int minimumOperations2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            if (set.contains(nums[i])) {
                int result = (int) Math.ceil((i + 1) / 3.0);
                return result;
            }
            set.add(nums[i]);
        }

        return 0;
    }

    int count = 0;

    public int minimumOperations(int[] nums) {
        if (checkArrayIsDistinct(nums)) {
            return count;
        }

        count++;
        if (nums.length <= 3) {
            return count;
        }

        return minimumOperations(Arrays.copyOfRange(nums, 3, nums.length));
    }

    private boolean checkArrayIsDistinct(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return false;
            }
            set.add(num);
        }

        return true;
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }

        return false;
    }

    public boolean divideArray2(int[] nums) {
        int[] result = new int[501];
        for (int n : nums) {
            result[n]++;
        }

        for (int n : result) {
            if ((n & 1) == 1) {
                return false;
            }
        }

        return true;
    }

    public boolean divideArray(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i = i + 2) {
            if (nums[i] != nums[i + 1]) {
                return false;
            }
        }

        return true;
    }

    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] ints : nums1) {
            map.put(ints[0], ints[1]);
        }

        for (int[] ints : nums2) {
            if (map.containsKey(ints[0])) {
                map.put(ints[0], ints[1] + map.get(ints[0]));
            } else {
                map.put(ints[0], ints[1]);
            }
        }

        int[][] result = new int[map.size()][2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            result[index][0] = entry.getKey();
            result[index][1] = entry.getValue();
            index++;
        }

        Arrays.sort(result, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        return result;
    }

    public int[] applyOperations(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                nums[i] *= 2;
                nums[i + 1] = 0;
            }
        }

        int index = 0;
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result[index] = nums[i];
                index++;
            }
        }

        return result;
    }

    public long countBadPairs(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i] - i;
            if (!map.containsKey(current)) {
                map.put(current, 1);
            } else {
                map.put(current, map.get(current) + 1);
            }
        }

        long maxPairs = subFuncCount(nums.length - 1);
        long goodPairs = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            goodPairs += subFuncCount(entry.getValue() - 1);
        }

        return maxPairs - goodPairs;
    }

    private long subFuncCount(int n) {
        long result = 0;
        for (int i = 1; i <= n; i++) {
            result += i;
        }

        return result;
    }

    public int maxAscendingSum(int[] nums) {
        int max = nums[0];
        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }

            max = Math.max(max, sum);
        }

        return max;
    }

    public int longestMonotonicSubarray(int[] nums) {
        int max = 1;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                count++;
            }

            if (i >= 2 && nums[i] > nums[i - 1] && nums[i - 1] <= nums[i - 2]) {
                count = 2;
            }

            if (i >= 2 && nums[i] < nums[i - 1] && nums[i - 1] >= nums[i - 2]) {
                count = 2;
            }

            max = Math.max(max, count);
        }

        return max;
    }

    public boolean check(int[] nums) {
        boolean isRotated = false;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1] && !isRotated) {
                isRotated = true;
            } else if (nums[i] < nums[i - 1] && isRotated) {
                return false;
            }
        }

        if (isRotated && nums[nums.length - 1] > nums[0]) {
            return false;
        }

        return true;
    }

    public int minimumOperations(int[][] grid) {
        int rowLength = grid.length;
        int columnLength = grid[0].length;

        int totalNeededOperations = 0;
        for (int i = 0; i < columnLength; i++) {
            // first item in each column always need 0 operations
            for (int j = 1; j < rowLength; j++) {
                if (grid[j][i] < grid[j - 1][i] + 1) {
                    int neededOperations = grid[j - 1][i] + 1 - grid[j][i];
                    grid[j][i] = grid[j - 1][i] + 1;
                    totalNeededOperations += neededOperations;
                }
            }
        }

        return totalNeededOperations;
    }

    public char findTheDifference2(String s, String t) {
        // an array to store number of appearance of each character
        int[] appearance = new int[300];
        for (int i = 0; i < t.length(); i++) {
            appearance[t.charAt(i)]++;
        }

        for (int i = 0; i < s.length(); i++) {
            appearance[s.charAt(i)]--;
        }

        for (int i = 97; i < 122; i++) {
            if (appearance[i] > 0) {
                return (char) i;
            }
        }

        return 'z';
    }

    public char findTheDifference(String s, String t) {
        if (s.isEmpty()) {
            return t.charAt(0);
        }

        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return arr2[i];
            }
        }

        return arr2[arr2.length - 1];
    }

    public int countPrefixSuffixPairs(String[] words) {
        int count = 0;
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (isPrefixAndSuffix(words[i], words[j])) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isPrefixAndSuffix(String str1, String str2) {
        if (str2.length() < str1.length()) {
            return false;
        }

        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return false;
            }
        }

        int indexOfStr2 = str2.length() - 1;
        for (int i = str1.length() - 1; i >= 0; i--) {
            if (str1.charAt(i) != str2.charAt(indexOfStr2)) {
                return false;
            }
            indexOfStr2--;
        }

        return true;
    }

    public List<String> stringMatching(String[] words) {
        Arrays.sort(words, (a, b) -> b.length() - a.length());
        List<String> result = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (!result.contains(words[j]) && words[i].contains(words[j])) {
                    result.add(words[j]);
                }
            }
        }

        return result;
    }

    public int countGoodStrings(int low, int high, int zero, int one) {
        int modulo = 1_000_000_007;
        int[] dp = new int[high + 1];
        dp[0] = 1;

        for (int i = 1; i <= high; i++) {
            if (i >= zero) {
                dp[i] += dp[i - zero];
            }
            if (i >= one) {
                dp[i] += dp[i - one];
            }
            dp[i] %= modulo;
        }

        int sum = 0;
        for (int i = low; i <= high; i++) {
            sum += dp[i];
            sum %= modulo;
        }

        return sum;
    }

    public int minSubArrayLen(int target, int[] nums) {
        int min = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int currentSum = nums[0];

        while (right < nums.length) {
            if (currentSum < target) {
                right++;
                if (right < nums.length) {
                    currentSum = currentSum + nums[right];
                }
            } else {
                min = Math.min(min, right - left + 1);
                currentSum = currentSum - nums[left];
                left++;
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public boolean checkIfExist(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i : arr) {
            if (i == 0 || list.contains(i * 2) || (i % 2 == 0 && list.contains(i / 2))) {
                return true;
            }
            list.add(i);
        }

        return false;
    }

    public static int findChampion(int n, int[][] edges) {
        boolean[] weakTeams = new boolean[n];

        for (int[] edge : edges) {
            weakTeams[edge[1]] = true;
        }
        int count = 0;
        int ans = 0;

        for (int i = 0; i < weakTeams.length; i++) {
            if (!weakTeams[i]) {
                count++;
                ans = i;
            }
        }

        return count == 1 ? ans : -1;
    }

    public static int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<String, Integer> patternFrequency = new HashMap<>();

        for (int[] row : matrix) {
            StringBuilder pattern = new StringBuilder();
            if (row[0] == 0) {
                for (int bit : row) {
                    pattern.append(bit);
                }
            } else {
                for (int bit : row) {
                    pattern.append(bit ^ 1);
                }
            }
            patternFrequency.merge(pattern.toString(), 1, Integer::sum);
        }

        return Collections.max(patternFrequency.values());
    }

    public static int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        // 0 represent for not guarded cell
        // 1 represent for guarded cell
        // 2 represent for a wall
        // 3 represent for a guard
        int[][] grid = new int[m][n];

        // add walls into grid
        for (int[] wall : walls) {
            grid[wall[0]][wall[1]] = 2;
        }

        // add guard into grid
        for (int[] guard : guards) {
            grid[guard[0]][guard[1]] = 3;
        }

        // loop guard to add guarded cell
        for (int[] guard : guards) {
            // North
            int north = guard[0] - 1;
            while (north >= 0) {
                if (grid[north][guard[1]] >= 2) {
                    break;
                }

                grid[north][guard[1]] = 1;
                north--;
            }

            // South
            int south = guard[0] + 1;
            while (south < m) {
                if (grid[south][guard[1]] >= 2) {
                    break;
                }

                grid[south][guard[1]] = 1;
                south++;
            }

            // West
            int west = guard[1] - 1;
            while (west >= 0) {
                if (grid[guard[0]][west] >= 2) {
                    break;
                }

                grid[guard[0]][west] = 1;
                west--;
            }

            // East
            int east = guard[1] + 1;
            while (east < n) {
                if (grid[guard[0]][east] >= 2) {
                    break;
                }

                grid[guard[0]][east] = 1;
                east++;
            }
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    count++;
                }
            }
        }

        return count;
    }

    public static boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        }

        if (n == 1) {
            return true;
        }

        while (n % 3 == 0) {
            n = n / 3;
        }

        return n == 1;
    }

    public static boolean isUgly(int n) {
        if (n == 1) {
            return true;
        }

        while (true) {
            boolean isDecrease = false;
            if (n % 2 == 0) {
                n = n / 2;
                isDecrease = true;
            }

            if (n % 3 == 0) {
                n = n / 3;
                isDecrease = true;
            }

            if (n % 5 == 0) {
                n = n / 5;
                isDecrease = true;
            }

            if (!isDecrease) {
                break;
            }
        }

        return n == 1;
    }

    public static boolean isPowerOfTwo(int n) {
        double[] nums = new double[63];

        for (int i = -31, j = 0; i < 32; i++) {
            nums[j] = Math.pow(2, i);
            j++;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == (double) n) {
                return true;
            }
        }

        return false;
    }

    public static int addDigits(int num) {
        while (num >= 10) {
            String[] arr = String.valueOf(num).split("");
            num = 0;
            for (String s : arr) {
                num = num + Integer.parseInt(s);
            }
        }

        return num;
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
