package practice.leetcodeproblem;

import java.util.HashSet;
import java.util.Set;

public class SlidingWindow {
    public static void log() {
        int[] arr1 = {0, 1, 2, 3, 2, 5};
        System.out.println(containsNearbyDuplicate(arr1, 3));
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums.length == 1 || k == 0) {
            return false;
        }

        Set<Integer> slidingWindow = new HashSet<>();
        slidingWindow.add(nums[0]);

        for (int index = 1; index < nums.length; index++) {
            if (slidingWindow.contains(nums[index])) {
                return true;
            }

            slidingWindow.add(nums[index]);
            if (index >= k) {
                slidingWindow.remove(nums[index - k]);
            }

        }

        return false;
    }
}
