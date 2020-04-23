package problems;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
 * Return the length of the longest (contiguous) subarray that contains only 1s.
 */
public class MaxConsecutiveOnes3_1004 {

    public int longestOnes(int[] A, int K) {
        int left = 0;
        int right = 0;
        int maxLength = 0;

        int[] zerosAndOnes = {0, 0};
        while (right < A.length) {
            if (K - zerosAndOnes[0] >= 0) {
                zerosAndOnes[A[right]]++;

                int len = zerosAndOnes[1] + Math.min(zerosAndOnes[0], K);
                if (len > maxLength) maxLength = len;

                right++;
            } else {
                zerosAndOnes[A[left]]--;

                left++;
            }
        }

        return maxLength;
    }

    // TODO implement algorithm above with precomputed array of lengths. Ones will be positive, zeroes will be negative
    public int longestOnesWithStack(int[] data, int K) {
        List<Integer> precomputedData = precompute(data);
        Integer[] a = precomputedData.toArray(new Integer[precomputedData.size()]);

        int left = 0;
        int right = 0;
        int maxLength = 0;
        int zeros = 0;
        int ones = 0;
        //int[] zerosAndOnes = {0, 0};

        while (right < a.length) {
            if (a[right] < 0)
                zeros -= a[right];
            else
                ones += a[right];

            maxLength = Math.max(maxLength, ones + Math.min(K, zeros));

            // Decide if we have to move left
            // Do this if we can't use leftmost zeros as a K-buffer
            if (zeros > K) {
                if (a[left] > 0) {
                    // Decrease ones
                    ones -= a[left];

                    // Move left index
                    left++;
                    right++;
                // Check if there will be enough zeros to match K-buffer if we remove leftmost zeros
                } else if (zeros + a[left] > K) {
                    // Decrease zeros
                    zeros += a[left];
                    ones -= a[left+1];
                    // Move left index
                    left+=2;
                }
            } else {
                right++;
            }
        }

        return maxLength;
    }

    public List<Integer> precompute(int[] A) {
        ArrayList<Integer> s = new ArrayList<>();
        int counter = 0;
        int currentNum = A[0];
        int[] signAdapter = {-1, 1};

        for (int i = 0; i < A.length; i++) {
            if (currentNum != A[i]) {
                s.add(counter * signAdapter[currentNum]);
                currentNum = A[i];
                counter = 1;
            } else {
                counter++;
            }
        }
        s.add(counter * signAdapter[currentNum]);
        return s;
    }

    public int solution(int[] A, int K) {
        int left = 0;
        int right = 0;
        while (right < A.length) {
            if (A[right] == 0) K--;
            if (K < 0) {
                if (A[left] == 0)
                    K++;
                left++;
            }

            right++;
        }
        return right - left;
    }

    public static void main(String[] args) {
        MaxConsecutiveOnes3_1004 m = new MaxConsecutiveOnes3_1004();
//        int[] data = {1,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,1,1,1,1,0,1,0,1,1,1,1,1,1,0,1,0,1,0,0,1,1,0,1,1};
//        int K = 8;
        int[] data = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        int K = 3;

        int i = m.longestOnes(data, K);
        System.out.println(i);
        int o = m.longestOnesWithStack(data, K);
        System.out.println("OPTIMIZED=" + o);
        int sol = m.solution(data, K);
        System.out.println("REAL=" + sol);
    }

}


