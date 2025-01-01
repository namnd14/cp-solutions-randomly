package practice.leetcodeproblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class DailyChallenge {
    public void log() {
        System.out.println("daily challenge");
        System.out.println(maxScore("011101"));


    }

    public int maxScore(String s) {
        int countOnes = 0;
        int maxScore = 0;
        int countOnesLeft = 0;
        int countZerosLeft = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                countOnes++;
            }
        }

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0') {
                countZerosLeft++;
            } else {
                countOnesLeft++;
            }

            int currentScore = countZerosLeft + countOnes - countOnesLeft;
            maxScore = Math.max(maxScore, currentScore);
        }

        return maxScore;
    }

    public int maxScore2(String s) {
        int maxScore = 0;
        int countLeft = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0') {
                countLeft++;
            }
            int countRight = 0;

            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(j) == '1') {
                    countRight++;
                }
            }

            maxScore = Math.max(maxScore, countLeft + countRight);
        }

        return maxScore;
    }

    public int mincostTickets2(int[] days, int[] costs) {
        // The last day on which we need to travel.
        int lastDay = days[days.length - 1];
        int dp[] = new int[lastDay + 1];
        Arrays.fill(dp, 0);

        int i = 0;
        for (int day = 1; day <= lastDay; day++) {
            // If we don't need to travel on this day, the cost won't change.
            if (day < days[i]) {
                dp[day] = dp[day - 1];
            } else {
                // Buy a pass on this day, and move on to the next travel day.
                i++;
                // Store the cost with the minimum of the three options.
                dp[day] = Math.min(
                        dp[day - 1] + costs[0],
                        Math.min(dp[Math.max(0, day - 7)] + costs[1], dp[Math.max(0, day - 30)] + costs[2])
                );
            }
        }

        return dp[lastDay];
    }

    HashSet<Integer> isTravelNeeded = new HashSet<>();

    private int solve(int[] dp, int[] days, int[] costs, int currDay) {
        // If we have iterated over travel days, return 0.
        if (currDay > days[days.length - 1]) {
            return 0;
        }

        // If we don't need to travel on this day, move on to next day.
        if (!isTravelNeeded.contains(currDay)) {
            return solve(dp, days, costs, currDay + 1);
        }

        // If already calculated, return from here with the stored answer.
        if (dp[currDay] != -1) {
            return dp[currDay];
        }

        int oneDay = costs[0] + solve(dp, days, costs, currDay + 1);
        int sevenDay = costs[1] + solve(dp, days, costs, currDay + 7);
        int thirtyDay = costs[2] + solve(dp, days, costs, currDay + 30);

        // Store the cost with the minimum of the three options.
        return dp[currDay] = Math.min(oneDay, Math.min(sevenDay, thirtyDay));
    }

    public int mincostTickets(int[] days, int[] costs) {
        // The last day on which we need to travel.
        int lastDay = days[days.length - 1];
        int dp[] = new int[lastDay + 1];
        Arrays.fill(dp, -1);

        // Mark the days on which we need to travel.
        for (int day : days) {
            isTravelNeeded.add(day);
        }

        return solve(dp, days, costs, 1);
    }

    public int maxScoreSightseeingPair(int[] values) {
        int maxLeftScore = values[0];
        int maxScore = 0;
        for (int i = 1; i < values.length; i++) {
            maxScore = Math.max(maxScore, maxLeftScore + values[i] - i);
            maxLeftScore = Math.max(maxLeftScore, values[i] + i);
        }

        return maxScore;
    }

    public int maxScoreSightseeingPair2(int[] values) {
        int n = values.length;
        int[] maxLeftScore = new int[n];
        maxLeftScore[0] = values[0];
        int maxScore = 0;
        for (int i = 1; i < n; i++) {
            int currentRightScore = values[i] - i;
            maxScore = Math.max(maxScore, maxLeftScore[i - 1] + currentRightScore);

            int currentLeftScore = values[i] + i;
            maxLeftScore[i] = Math.max(maxLeftScore[i - 1], currentLeftScore);
        }

        return maxScore;
    }

    public int countSubarrays(int[] nums) {
        int count = 0;

        for (int i = 2; i < nums.length; i++) {
            if ((nums[i] + nums[i - 2]) * 2 == nums[i - 1]) {
                count++;
            }
        }

        return count;
    }

    public int maxChunksToSorted(int[] arr) {
        int max = -1;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if (max == i) {
                count++;
            }
        }

        return count;
    }

    public String repeatLimitedString2(String s, int repeatLimit) {
        int[] frequency = new int[26];

        for (char c : s.toCharArray()) {
            frequency[c - 'a']++;
        }

        int pendingLetterIndex = -1;
        StringBuilder sb = new StringBuilder();
        int i = 25;
        while (i >= 0) {
            if (frequency[i] == 0) {
                i--;
                continue;
            }

            if (pendingLetterIndex > 0) {
                sb.append((char) ('a' + i));
                frequency[i]--;
                i = pendingLetterIndex;
                pendingLetterIndex = -1;

            } else {
                for (int j = 0; j < repeatLimit && frequency[i] > 0; j++, frequency[i]--) {
                    sb.append((char) ('a' + i));
                }

                if (frequency[i] > 0) {
                    pendingLetterIndex = i;
                }
                i--;
            }
        }

        return sb.toString();
    }

    public String repeatLimitedString(String s, int repeatLimit) {
        StringBuilder sb = new StringBuilder();
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        int left = 0;
        int right = 0;
        while (right < arr.length) {
            if (arr[right] == arr[left] && (right - left < repeatLimit)) {
                if (right - left < repeatLimit) {
                    sb.append(arr[right]);
                    right++;
                } else {
                    left = right;
                    while (right < arr.length) {
                        if (arr[right] == arr[left]) {
                            right++;
                        } else {
                            char temp = arr[left];
                            arr[left] = arr[right];
                            arr[right] = temp;
                            sb.append(arr[left]);
                            left++;
                            right = left;
                            break;
                        }
                    }
                }
            } else if (arr[right] != arr[left] && (right - left <= repeatLimit)) {
                left = right;
                right++;
                sb.append(arr[right]);
            }
        }

        return sb.toString();
    }

    public int[] finalPrices(int[] prices) {
        int[] result = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            result[i] = prices[i];
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] <= prices[i]) {
                    result[i] = prices[i] - prices[j];
                    break;
                }
            }
        }

        return result;
    }

    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<ClassRecord> pq = new PriorityQueue<>(new Compare());

        for (int[] currentClass : classes) {
            pq.add(new ClassRecord(currentClass));
        }

        while (extraStudents > 0) {
            ClassRecord currentClass = pq.remove();
            currentClass.addOneStudent();
            pq.add(currentClass);
            extraStudents--;
        }

        double sum = 0;
        while (!pq.isEmpty()) {
            ClassRecord currentClass = pq.remove();
            sum += (double) currentClass.pass / currentClass.total;
        }

        return sum / classes.length;
    }

    static class ClassRecord {
        int pass;
        int total;
        double increaseByAddOne;

        public ClassRecord(int[] array) {
            pass = array[0];
            total = array[1];
            increaseByAddOne = getIncrement();
        }

        public void addOneStudent() {
            pass++;
            total++;
            increaseByAddOne = getIncrement();
        }

        private double getIncrement() {
            return (pass + 1.0) / (total + 1.0) - (double) pass / total;
        }
    }

    static class Compare implements Comparator<ClassRecord> {
        public int compare(ClassRecord a, ClassRecord b) {
            if (a.increaseByAddOne < b.increaseByAddOne) {
                return 1;
            } else if (a.increaseByAddOne > b.increaseByAddOne) {
                return -1;
            }

            return 0;

        }
    }

    public int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums);
        int max = Integer.MIN_VALUE;
        int left = 0;
        int right = 0;

        while (right < nums.length) {
            if (nums[right] - nums[left] <= 2 * k) {
                right++;
                max = Math.max(max, right - left);
            } else {
                left++;
            }
        }

        return max;
    }
}
