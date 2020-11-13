package grokking;

public class Part_16_Knapsack_DP {

    /**
     * Given the weights and profits of ‘N’ items, we are asked to put these items in a knapsack which has a capacity ‘C’.
     * The goal is to get the maximum profit out of the items in the knapsack.
     * Each item can only be selected once, as we don’t have multiple quantities of any item.
     *
     * Time complexity: O(2^N)
     * Space complexity: O(N) for recursive stack. Ie the height of a binary tree
     */
    public static int solveKnapsack(int[] profits, int[] weights, int capacity) {
        return knap(profits, weights, capacity, 0);
    }

    private static int knap(int[] profits, int[] weights, int capacity, int i) {
        if (capacity <= 0 || i >= profits.length)
            return 0;

        int profit1 = 0;
        if (capacity >= weights[i]) {
            profit1 = profits[i] + knap(profits, weights, capacity - weights[i], i + 1);
        }

        int profit2 = knap(profits, weights, capacity, i+1);

        return Math.max(profit1, profit2);
    }

    /**
     * Time complexity: O(N*C), where C is a capacity
     * Space complexity: O(N*C + N)
     */
    public static int solveKnapsackWithMemorization(int[] profits, int[] weights, int capacity) {
        Integer[][] dp = new Integer[profits.length][capacity+1];
        return knapMem(profits, weights, capacity, 0, dp);
    }

    private static int knapMem(int[] profits, int[] weights, int capacity, int i, Integer[][] dp) {
        if (capacity <= 0 || i >= profits.length)
            return 0;

        if (dp[i][capacity] != null)
            return dp[i][capacity];

        int profit1 = 0;
        if (weights[i] <= capacity)
            profit1 = profits[i] + knapMem(profits, weights, capacity - weights[i], i+1, dp);

        int profit2 = knapMem(profits, weights, capacity, i+1, dp);

        dp[i][capacity] = Math.max(profit1, profit2);

        return dp[i][capacity];
    }

    /**
     * Time complexity: O(N*C)
     * Space complexity: O(N*C)
     */
    public static int solveKnapsackBottomUp(int[] profits, int[] weights, int capacity) {
        // TODO check args

        int n = weights.length;
        int[][] dp = new int[n][capacity+1];

        for (int c = 1; c <= capacity; c++) {
            if (weights[0] <= c)
                dp[0][c] = profits[0];
        }

        for (int i = 1; i < n; i++) {
            for (int c = 1; c <= capacity; c++) {
                int profit1 = 0;
                if (weights[i] <= c)
                    profit1 = profits[i] + dp[i-1][c - weights[i]];

                int profit2 = dp[i-1][c];

                dp[i][c] = Math.max(profit1, profit2);
            }
        }

        return dp[n-1][capacity];
    }

    /**
     * Time complexity: O(N*C)
     * Space complexity: O(2*C) => O(C)
     */
    public static int solveKnapsackBottomUp2C(int[] profits, int[] weights, int capacity) {
        int n = profits.length;
        int[][] dp = new int[2][capacity+1];

        for (int i = 1; i <= capacity; i++)
            if (weights[0] <= i)
                dp[0][i] = profits[0];

        int i = 1;
        for (; i < n; i++) {
            for (int c = 0; c <= capacity; c++) {
                int profit1 = 0;
                if (weights[i] <= c)
                    profit1 = profits[i] + dp[(i-1)%2][c - weights[i]];

                int profit2 = dp[(i-1)%2][c];

                dp[i%2][c] = Math.max(profit1, profit2);
            }
        }

        return dp[(i-1)%2][capacity];
    }

    /**
     * Given a set of positive numbers,
     * find if we can partition it into two subsets such that the sum of elements in both subsets is equal.
     */
    public static boolean canPartition(int[] num) {
        int sum = 0;
        for (int n : num)
            sum += n;

        if (sum % 2 == 1)
            return false;

        return canPartitionRec(num, 0, sum/2);
    }

    public static boolean canPartitionRec(int[] num, int i, int cap) {
        if (cap == 0)
            return true;

        if (i >= num.length || num[i] > cap)
            return false;

        return canPartitionRec(num, i+1, cap - num[i]) || canPartitionRec(num, i+1, cap);
    }

    public static boolean canPartitionMem(int[] num) {
        if (num == null || num.length == 0)
            return false;

        int sum = 0;
        for (int i : num)
            sum += i;
        if (sum % 2 != 0)
            return false;

        Boolean dp[][] = new Boolean[num.length][sum/2 + 1];
        return canPartitionMemRec(num, dp, sum/2, 0);
    }

    public static boolean canPartitionMemRec(int[] num, Boolean[][] dp, int sum, int i) {
        if (sum == 0)
            return true;

        if (i >= num.length || num[i] > sum)
            return false;

        if (dp[i][sum] != null)
            return false;

        boolean p1 = canPartitionMemRec(num, dp, sum - num[i], i+1);
        boolean p2 = canPartitionMemRec(num, dp, sum, i+1);

        if (!p1 && !p2)
            dp[i][sum] = false;

        return p1 || p2;
    }

    public static boolean canPartitionBottomUp(int[] num) {
        int sum = 0;
        for (int n : num)
            sum += n;

        if (sum % 2 != 0)
            return false;

        sum /= 2;

        boolean dp[][] = new boolean[num.length][sum + 1];

        for (int i = 0; i < num.length; i++)
            dp[i][0] = true;

        for (int i = 1; i <= sum; i++)
            dp[0][i] = num[0] == i;

        for (int i = 1; i < num.length; i++) {
            for (int s = 1; s <= sum; s++) {
                if (dp[i-1][s])
                    dp[i][s] = dp[i-1][s];
                else if (s >= num[i])
                    dp[i][s] = dp[i-1][s-num[i]];
            }
        }

        return dp[num.length-1][sum];
    }

    /**
     * Given a set of positive numbers, determine if a subset exists whose sum is equal to a given number ‘S’.
     */
    public static boolean canPartition(int[] num, int sum) {
        int n = num.length;
        boolean[][] dp = new boolean[2][sum+1];

        for (int i = 0; i < 2; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= sum; i++) {
            dp[0][i] = num[0] == i;
        }

        int i = 1;
        for (; i < n; i++) {
            for (int s = 0; s <= sum; s++) {
                if (dp[(i-1)%2][s])
                    dp[i%2][s] = true;
                else if (num[i] <= s)
                    dp[i%2][s] = dp[(i-1)%2][s - num[i]];
            }
        }

        return dp[(i-1)%2][sum];
    }

    /**
     * Given a set of positive numbers, partition the set into two subsets with minimum difference between their subset sums.
     */
    public static int canPartitionMinDiff(int[] num) {
        return canPartitionMinDiffRecursive(num, 0,0,0);
    }

    public static int canPartitionMinDiffRecursive(int[] num, int i, int s1, int s2) {
        if (i == num.length)
            return Math.abs(s1 - s2);

        int diff1 = canPartitionMinDiffRecursive(num, i+1, s1 + num[i], s2);
        int diff2 = canPartitionMinDiffRecursive(num, i+1, s1, s2 + num[i]);

        return Math.min(diff1, diff2);
    }

    public static int canPartitionMinDiffDP(int[] num) {
        int n = num.length;
        int sum  = 0;
        for (int i = 0; i < num.length; i++) {
            sum += num[i];
        }

        boolean[][] dp = new boolean[n][sum/2 +1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        if (num[0] <= sum/2)
            dp[0][num[0]] = true;

        for (int i = 1; i < n; i++) {
            for (int s = 1; s <= sum/2; s++) {
                if (dp[i-1][s])
                    dp[i][s] = true;
                else if (num[i] <= s)
                    dp[i][s] = dp[i-1][s - num[i]];
            }
        }

        int sum1 = 0;
        for (int i = sum/2; i >= 0; i--) {
            if (dp[n-1][i] == true) {
                sum1 = i;
                break;
            }
        }

        return sum - sum1 - sum1;
    }

    /**
     * Given a set of positive numbers, find the total number of subsets whose sum is equal to a given number ‘S’.
     */
    public static int countSubsets(int[] num, int sum) {
        int n = num.length;

        int[][] dp = new int[2][sum+1];

        for (int i = 0; i < 2; i++)
            dp[i][0] = 1;

        if (num[0] <= sum)
            dp[0][num[0]] = 1;

        int i = 1;
        for (; i < n; i++) {
            for (int s = 1; s <= sum; s++) {
                dp[i%2][s] = dp[(i-1)%2][s];

                if (num[i] <= s)
                    dp[i%2][s] += dp[(i-1)%2][s - num[i]];
            }
        }

        return dp[(i-1)%2][sum];
    }

    /**
     * You are given a set of positive numbers and a target sum ‘S’.
     * Each number should be assigned either a ‘+’ or ‘-’ sign.
     * We need to find the total ways to assign symbols to make the sum of the numbers equal to the target ‘S’.
     */
    public static int findTargetSubsets(int[] num, int s) {
        return findTargetSubsetsRec(num, s, 0);
    }

    public static int findTargetSubsetsRec(int[] num, int s, int i) {
        if (i == num.length)
            return s == 0 ? 1 : 0;

        int result1 = findTargetSubsetsRec(num, s - num[i], i+1);
        int result2 = findTargetSubsetsRec(num, s + num[i], i+1);

        return result1 + result2;
    }

    public static void main(String[] args) {
        int[] num = {1, 1, 2, 3};
        System.out.println(findTargetSubsets(num, 1));
        num = new int[]{1, 2, 7, 1};
        System.out.println(findTargetSubsets(num, 9));
    }
}
