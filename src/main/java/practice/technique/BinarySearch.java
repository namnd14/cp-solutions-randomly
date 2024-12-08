package practice.technique;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BinarySearch {
    public void log() {
        System.out.println("Binary search technique");
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        System.out.println(findElement(arr, 1));
        System.out.println(findElement(arr, 2));
        System.out.println(findElement(arr, 5));
        System.out.println(findElement(arr, 8));
        System.out.println(findElement(arr, 12));
        System.out.println(findElement(arr, 16));
        System.out.println(findElement(arr, 23));
        System.out.println(findElement(arr, 38));
        System.out.println(findElement(arr, 56));
        System.out.println(findElement(arr, 72));
        System.out.println(findElement(arr, 91));

    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int num : nums1) {
            map1.put(num, num);
        }

        Set<Integer> result = new HashSet<>();
        for (int num : nums2) {
            if (map1.containsKey(num)) {
                result.add(num);
            }
        }

        Object[] a = result.toArray();
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = (int) a[i];
        }

        return b;
    }

    public int findElement(int[] arr, int num) {
        // constrain: arr.length >= 1;
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int halfIndex = (left + right) / 2;
            if (arr[halfIndex] == num) {
                return halfIndex;
            } else if (arr[halfIndex] > num) {
                right = halfIndex - 1;
            } else {
                left = halfIndex + 1;
            }
        }

        return -1;
    }
}
