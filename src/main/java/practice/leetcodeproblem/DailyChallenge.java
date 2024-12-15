package practice.leetcodeproblem;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DailyChallenge {
    public void log() {
        System.out.println("daily challenge");
        System.out.println(maximumBeauty(new int[]{4, 6, 1, 2}, 2));
        System.out.println(maximumBeauty(new int[]{1, 1, 1, 1}, 10));

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
