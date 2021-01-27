package problems;

public class P1155_NumRollsToTarget {

    private int MOD = 1000000007;

    public int numRollsToTarget(int d, int f, int target) {
        int[][] dp = new int[d][target+1];

        for (int i = 1; i <= target && i <= f; i++)
            dp[0][i] = 1;

        // 0 1 2 3 4 5  6  7  8
        // 0 1 1 1 1 1  1  0  0
        // 0 0 1 2 3 4  5  6  5
        // 0 0 0 1 3 6 10 15 21
        //

        // dp[i][j] = dp[i-1][j-1] +

        for (int i = 1; i < d; i++) {
            for (int j = 1; j <= target; j++) {

                int sum = 0;

                for (int k = Math.max(0, j - f); k < j; k++)
                    sum += dp[i-1][k];

                dp[i][j] = sum % MOD;
            }

            System.out.println(print2dArray(dp));
        }

        return dp[d-1][target];
    }

    public static void main(String[] args) {
        P1155_NumRollsToTarget p = new P1155_NumRollsToTarget();
        p.numRollsToTarget(6, 6, 20);

    }

    public static String print2dArray(int[][] matrix) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sb.append("" + matrix[i][j] + "\t");
            }

            sb.append("\n");
        }
        return sb.toString();
    }

}
