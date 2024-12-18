package practice.leetcodeproblem;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DailyChallenge {
    public void log() {
        System.out.println("daily challenge");
        System.out.println(repeatLimitedString("cczazcc", 3));
        System.out.println(repeatLimitedString("aababab", 2));

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
