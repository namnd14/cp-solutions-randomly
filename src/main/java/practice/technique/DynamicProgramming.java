package practice.technique;

public class DynamicProgramming {
    public void log() {
        System.out.println("Dynamic programming");
        System.out.println(tribonacci(3));
        System.out.println(tribonacci(4));
        System.out.println(tribonacci(25));
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
