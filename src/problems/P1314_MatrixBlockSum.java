package problems;

public class P1314_MatrixBlockSum {

    public int[][] matrixBlockSum(int[][] mat, int K) {
        int m = mat.length;
        int n = mat[0].length;
/*
        int[] mSum = new int[m];
        int[] nSum = new int[n];

        // 1 2 3
        // 4 5 6
        // 7 8 9

        for (int i = 0; i < m; i++) {
            if (m > 0)
                mSum[i] = mSum[i - 1];

            for (int j = 0; j < n; j++) {
                mSum[i] += mat[i][j];

                if (i == m - 1 && j > 0)
                    nSum[j] += nSum[j-1];

                nSum[j] += mat[i][j];
            }
        }
  */

        int[][] dp = new int[m][n];

        dp[0][0] = mat[0][0];

        for (int i = 1; i < m; i++)
            dp[i][0] = dp[i-1][0] + mat[i][0];

        for (int i = 1; i < n; i++)
            dp[0][i] = dp[0][i-1] + mat[0][i];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = mat[i][j] + dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1];
            }
        }

        int[][] result = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x = Math.min(i + K, m - 1);
                int y = Math.min(j + K, n - 1);

                result[i][j] = dp[x][y];

                int xMin = i - K - 1;
                int yMin = j - K - 1;

                if (xMin >= 0)
                    result[i][j] -= dp[xMin][y];

                if (yMin >= 0)
                    result[i][j] -= dp[x][yMin];

                if (xMin >= 0 && yMin >= 0)
                    result[i][j] += dp[xMin][yMin];
            }
        }

        return result;
    }
//
//    public static void main(String[] args) {
//        P1314_MatrixBlockSum b = new P1314_MatrixBlockSum();
//        b.matrixBlockSum(new int[][]{{1,2,3},{4,5,6},{7,8,9}}, 1);
//    }
}
