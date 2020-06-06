package grokking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class P2SlidingWIndow {

    public double[] intro(int[] a, int k) {
        double[] result = new double[a.length - k + 1];
        int left = 0;
        int right = 0;
        int sum = 0;

        while (right < k) {
            sum += a[right];
            if (right >= k) {
                result[left] = sum / k;
                sum -= a[left];
                left++;
            }
            right++;
        }

        return result;
    }

    /**
     * Given an array of positive numbers and a positive number ‘k’, find the maximum sum of any contiguous subarray of size ‘k’.
     */
    public int[] maxSumSubArray(int[] a, int k) {
        int left = 0;
        int right = 0;
        int sum = 0;
        int maxSum = 0;
        int maxL = 0;

        while (right < k) {
            sum += a[right];

            if (right >= k) {
                if (sum > maxSum) {
                    maxSum = sum;
                    maxL = left;
                }
                sum -= a[left];
                left++;
            }
            right++;
        }

        int[] result = new int[k];
        System.arraycopy(a, maxL, result, 0, k);

        return result;
    }

    public int smallestSubarrayWithAGivenSum(int[] a, int s) {
        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < a.length; right++) {
            sum += a[right];

            while (sum <= s) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= a[left++];
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    /**
     * Given a string, find the length of the longest substring in it with no more than K distinct characters.
     */
    public static int longestSubstringWithLessThanKDistinctCharacters(String s, int k) {
        int left = 0;
        int right = 0;
        HashMap<Character, Integer> charSet = new HashMap<>();

        while (right < s.length()) {
            char c = s.charAt(right);

            int rightCharCount = charSet.containsKey(c) ? charSet.get(c) : 0;
            charSet.put(c, rightCharCount + 1);

            if (charSet.size() > k) {
                Integer leftCharCount = charSet.get(s.charAt(left));
                leftCharCount--;
                if (leftCharCount == 0)
                    charSet.remove(s.charAt(left));
                else
                    charSet.put(s.charAt(left), leftCharCount);
                left++;
            }

            right++;
        }

        return right - left;
    }

    /**
     * Given an array of characters where each character represents a fruit tree, you are given two baskets and your goal is to put maximum number of fruits in each basket. The only restriction is that each basket can have only one type of fruit.
     * You can start with any tree, but once you have started you can’t skip a tree. You will pick one fruit from each tree until you cannot, i.e., you will stop when you have to pick from a third fruit type.
     * Write a function to return the maximum number of fruits in both the baskets.
     */
    public static int fruitsToBaskets(char[] a) {
        int maxFruits = 0;
        int left = 0;
        int right = 0;
        Set<Character> baskets = new HashSet<>();

        while (right < a.length) {
            baskets.add(a[right]);

            while (baskets.size() > 2) {
                baskets.remove(a[left]);
                left++;
            }

            maxFruits = Math.max(maxFruits, right - left + 1);
            right++;
        }

        return maxFruits;
    }

    public static void main(String[] args) {
        char[] c = {'A', 'B', 'C', 'B', 'B', 'C'};
        System.out.println(fruitsToBaskets(c));
    }

}
