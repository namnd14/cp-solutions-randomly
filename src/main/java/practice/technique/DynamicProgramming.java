package practice.technique;

public class DynamicProgramming {
    public void log() {
        System.out.println("Dynamic programming");
        int[] cost1 = {10, 15, 20};
        System.out.println(minCostClimbingStairs(cost1));
        int[] cost2 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(minCostClimbingStairs(cost2));
    }

    public boolean divisorGame(int n) {
        if (n == 1) {
            return false;
        }

        boolean[] result = new boolean[n];
        result[1] = true;
        for (int i = 3; i < n; i++) {

        }

        return result[n - 1];
    }

    public int minCostClimbingStairs(int[] cost) {
        int[] totalCost = new int[cost.length];
        totalCost[0] = cost[0];
        totalCost[1] = cost[1];

        for (int i = 2; i < cost.length; i++) {
            if (totalCost[i - 1] > totalCost[i - 2]) {
                totalCost[i] = totalCost[i - 2] + cost[i];
            } else {
                totalCost[i] = totalCost[i - 1] + cost[i];
            }
        }

        return Math.min(totalCost[totalCost.length - 1], totalCost[totalCost.length - 2]);
    }

    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        int[] memo = new int[40];
        memo[0] = 0;
        memo[1] = 1;
        memo[2] = 1;

        for (int i = 3; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2] + memo[i - 3];
        }

        return memo[n];
    }
}
