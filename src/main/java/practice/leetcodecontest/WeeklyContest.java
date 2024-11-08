package practice.leetcodecontest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WeeklyContest {
    public static void log() {
        System.out.println("Hello World");
        System.out.println(countBalancedPermutations("4567"));
        // sample2();
    }

    public static int countBalancedPermutations(String num) {
        String[] arr = num.split("");
        Arrays.sort(arr);
        List<Integer> arrEven = new ArrayList<>();
        List<Integer> arrOdd = new ArrayList<>();

        if (arr.length % 2 == 0) {
            int sumEven = 0;
            int sumOdd = 0;
            for (int i = 0; i < arr.length; i++) {
                if (i % 2 == 0) {
                    arrEven.add(Integer.parseInt(arr[i]));
                    sumEven += Integer.parseInt(arr[i]);
                } else {
                    arrOdd.add(Integer.parseInt(arr[i]));
                    sumOdd += Integer.parseInt(arr[i]);
                }
            }

            if (sumEven != sumOdd) {
                return 0;
            }

            return countUniquePermutations(arrEven) * countUniquePermutations(arrOdd);
        }

        int sumEven = 0;
        int sumOdd = 0;
        arrEven.add(Integer.parseInt(arr[0]));
        sumEven += Integer.parseInt(arr[0]);
        arrEven.add(Integer.parseInt(arr[1]));
        sumEven += Integer.parseInt(arr[1]);
        for (int i = 2; i < arr.length; i++) {
            if (i % 2 == 0) {
                arrOdd.add(Integer.parseInt(arr[i]));
                sumOdd += Integer.parseInt(arr[i]);
            } else {
                arrEven.add(Integer.parseInt(arr[i]));
                sumEven += Integer.parseInt(arr[i]);
            }
        }

        if (sumEven != sumOdd) {
            return 0;
        }

        return countUniquePermutations(arrEven) * countUniquePermutations(arrOdd);
    }

    public static int countUniquePermutations(List<Integer> list) {
        int n = list.size();
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int num : list) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        int numerator = factorial(n);

        int denominator = 1;
        for (int freq : frequencyMap.values()) {
            denominator *= factorial(freq);
        }

        return numerator / denominator;
    }

    private static int factorial(int num) {
        int result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    public static void sample2() {
        ArrayList<Integer> input1 = new ArrayList<>(List.of(1, 1, 2));
        ArrayList<Integer> input2 = new ArrayList<>(List.of(1, 2, 3));

        System.out.println("Unique permutations count for [1, 1, 2]: " + countUniquePermutations(input1)); // Output: 3
        System.out.println("Unique permutations count for [1, 2, 3]: " + countUniquePermutations(input2)); // Output: 6
    }

    public static void sample() {
        String input = "112";
        Set<String> permutations = new HashSet<>();
        permute(input.toCharArray(), 0, permutations);

        System.out.println("Permutations: " + permutations);
    }

    private static void permute(char[] chars, int index, Set<String> result) {
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }
        for (int i = index; i < chars.length; i++) {
            swap(chars, i, index);
            permute(chars, index + 1, result);
            swap(chars, i, index); // backtrack
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static int minTimeToReach(int[][] moveTime) {
        return 0;
    }

    public static boolean isBalanced(String num) {
        int sumInEvenIndex = 0;
        int sumInOddIndex = 0;
        String[] arr = num.split("");
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                sumInEvenIndex += Integer.parseInt(arr[i]);
            } else {
                sumInOddIndex += Integer.parseInt(arr[i]);
            }
        }

        return sumInOddIndex == sumInEvenIndex;
    }
}
