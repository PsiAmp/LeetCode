package problems;

import java.util.Arrays;

public class MovingStonesUntilConsecutive2_1040 {

    public int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);

        int n = stones.length;
        int left = 0;
        int right = stones.length - 1;

        int max = Math.max(stones[n-1] - stones[1] - n + 2,
                            stones[n-2] - stones[0] - n + 2);

        int min = stones.length;

        for (int i = 0; i < stones.length; i++) {

        }

        return new int[]{max, min};
    }
}