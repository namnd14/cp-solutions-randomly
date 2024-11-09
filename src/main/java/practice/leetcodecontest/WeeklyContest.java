package practice.leetcodecontest;

import edu.princeton.cs.algs4.In;

import java.math.BigInteger;
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
        int[] arr = {5, 11, 20, 20};
        long abc = (long) Math.pow(9, 100_000);
//        System.out.println(abc);
//        System.out.println(Long.MAX_VALUE);
        //  nums = [5,11,20,20], k = 5, numOperations = 1

        System.out.println(maxFrequency(arr, 5, 1));
//        System.out.println(smallestNumber("1234", 256));
//        System.out.println(smallestNumber("12355", 50));
    }

    public static int maxFrequency(int[] nums, int k, int numOperations) {
        Arrays.sort(nums);  // Sort the array for the sliding window approach
        int maxFrequency = 1;  // At least one element can have frequency of 1
        long operationsUsed = 0;
        int i = 0;

        for (int j = 1; j < nums.length; j++) {
            // Calculate the cost to make nums[i] through nums[j-1] equal to nums[j]
            operationsUsed += (long) (nums[j] - nums[j - 1]) * (j - i);

            // If operations used exceed numOperations, adjust the window by moving 'i'
            while (operationsUsed > numOperations) {
                operationsUsed -= nums[j] - nums[i];
                i++;
            }

            // Update max frequency as the current window size
            maxFrequency = Math.max(maxFrequency, j - i + 1);
        }

        return maxFrequency;
    }

    public static String smallestNumber(String num, long t) {
        if (!checkNum(t)) {
            return "-1";
        }

        BigInteger bigT = new BigInteger(String.valueOf(t));
        BigInteger zero = new BigInteger("0");
        BigInteger max = new BigInteger(String.valueOf(Integer.MAX_VALUE));

        while (true) {
            if (new BigInteger(num).compareTo(max) > 0) {
                return "-1";
            }

            BigInteger total = new BigInteger("1");
            String[] arr = num.split("");
            for (String s : arr) {
                total = total.multiply(new BigInteger(s));
            }

            if (total.remainder(bigT).equals(zero)) {
                String zeroFree = num.replaceAll("0", "");
                if (num.equals(zeroFree)) {
                    return num;
                }
            }

            num = String.valueOf(Integer.parseInt(num) + 1);
        }
    }

    private static boolean checkNum(long t) {
        while (true) {
            boolean isDecrease = false;
            if (t % 2 == 0) {
                isDecrease = true;
                t = t / 2;
            }

            if (t % 3 == 0) {
                isDecrease = true;
                t = t / 3;
            }

            if (t % 5 == 0) {
                isDecrease = true;
                t = t / 5;
            }

            if (t % 7 == 0) {
                isDecrease = true;
                t = t / 7;
            }

            if (!isDecrease) {
                break;
            }
        }

        if (t >= 1 && t <= 9) {
            return true;
        }

        BigInteger bigInt = new BigInteger(String.valueOf(t));
        return !bigInt.isProbablePrime(100);
    }

    public static int smallestNumber(int n, int t) {
        while (true) {
            int total = 1;
            String[] arr = String.valueOf(n).split("");
            for (String s : arr) {
                total = total * Integer.parseInt(s);
            }

            if (total % t == 0) {
                return n;
            }

            n++;
        }
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
