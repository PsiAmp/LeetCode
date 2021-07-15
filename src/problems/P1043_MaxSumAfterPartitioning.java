package problems;

public class P1043_MaxSumAfterPartitioning {

    public int maxSumAfterPartitioning(int[] arr, int K) {
        int n = arr.length;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            int max = 0;

            for (int k = 1; k <= K && i - k + 1 >= 0; k++) {
                max = Math.max(max, arr[i - k + 1]);
                dp[i] = Math.max(dp[i],  (i >= k ? dp[i-k] : 0) + k * max);
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        P1043_MaxSumAfterPartitioning p = new P1043_MaxSumAfterPartitioning();
        p.maxSumAfterPartitioning(new int[]{1, 2, 9, 30, 5}, 2);
    }
}
