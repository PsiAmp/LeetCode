package grokking;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Part_13_XOR {

    public static int findMissingNumber(int[] arr) {
        int x1 = 1;
        for (int i = 2; i <= arr.length+1; i++) {
            x1 = x1 ^ i;
        }

        int x2 = arr[0];
        for (int i = 1; i < arr.length; i++) {
            x2 = x2 ^ arr[i];
        }

        return x1 ^ x2;
    }

    public static int missingNumber(int[] nums) {
        int sum = nums.length;
        for (int i = 0; i < nums.length; i++) {
            sum += i - nums[i];
        }
        return sum;
    }

    /**
     * In a non-empty array of integers, every number appears twice except for one, find that single number.
     */
    public static int findSingleNumber(int[] arr) {
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
        }
        return xor;
    }

    /**
     * In a non-empty array of numbers, every number appears exactly twice except two numbers that appear only once.
     * Find the two numbers that appear only once.
     */
    public static int[] findingTwoSingleNumbers(int[] nums) {
        int x = 0;

        for (int num : nums) {
            x ^= num;
        }

        int bit = 1;
        while ((bit & x) == 0) {
            bit = bit << 1;
        }

        int num1 = 0;
        int num2 = 0;
        for (int num : nums) {
            if ((num & bit) == 0)
                num1 ^= num;
            else
                num2 ^= num;
        }

        return new int[] {num1, num2};
    }

    /**
     * For a given positive number N in base-10, return the complement of its binary representation as a base-10 integer.
     */
    public static int bitwiseComplement(int N) {
        int x = 0;
        do {
            x = ((x+1) << 1) - 1;
        } while (N > x);
        return x ^ N;
    }

    /**
     * Given a binary matrix representing an image, we want to flip the image horizontally, then invert it.
     * To flip an image horizontally means that each row of the image is reversed.
     * For example, flipping [0, 1, 1] horizontally results in [1, 1, 0].
     * To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0.
     * For example, inverting [1, 1, 0] results in [0, 0, 1].
     */
    public static int[][] flipAndInvertImage(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            int width = arr[i].length;
            for (int j = 0; j < (width+1)/2; j++) {
                int t = arr[i][j] ^ 1;
                arr[i][j] = arr[i][width-j-1] ^ 1;
                arr[i][width-j-1] = t;
            }
        }
        return arr;
    }
    public static void print(int[][] arr) {
        for(int i=0; i < arr.length; i++) {
            for (int j=0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        int[][] arr = {
                {1, 0, 1},
                {1, 1, 1},
                {0, 1, 1}};
        print(flipAndInvertImage(arr));
        System.out.println();

        int[][]arr2 = {
                {1,1,0,0},
                {1,0,0,1},
                {0,1,1,1},
                {1,0,1,0}};
        print(flipAndInvertImage(arr2));

    }

}
