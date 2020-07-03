package grokking;

import java.util.ArrayList;
import java.util.List;

public class P6CyclicSort {

    /**
     * We are given an array containing ‘n’ objects. Each object, when created, was assigned a unique number from 1 to ‘n’ based on their creation sequence.
     * This means that the object with sequence number ‘3’ was created just before the object with sequence number ‘4’.
     * Write a function to sort the objects in-place on their creation sequence number in O(n)O(n)O(n) and without any extra space.
     * For simplicity, let’s assume we are passed an integer array containing only the sequence numbers, though each number is actually an object.
     */
    public static void cyclicSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1) {
                int t = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = t;
            }
        }
    }

    /**
     * We are given an array containing ‘n’ distinct numbers taken from the range 0 to ‘n’.
     * Since the array has only ‘n’ numbers out of the total ‘n+1’ numbers, find the missing number.
     */
    public static int findMissingNumber(int[] nums) {
        int sum = nums.length;
        for (int i = 0; i < nums.length; i++) {
            sum += i - nums[i];
        }
        return sum;
    }

    /**
     * We are given an unsorted array containing numbers taken from the range 1 to ‘n’.
     * The array can have duplicates, which means some numbers will be missing. Find all those missing numbers.
     */
    public static List<Integer> findNumbers(int[] nums) {
        List<Integer> missingNumbers = new ArrayList<>();

        for (int i = 0; i < nums.length;) {
            if (nums[i] != nums[nums[i]-1]) {
                int t = nums[i];
                nums[i] = nums[t - 1];
                nums[t - 1] = t;
            } else {
                i++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1)
                missingNumbers.add(i + 1);
        }

        return missingNumbers;
    }

    /**
     * We are given an unsorted array containing ‘n+1’ numbers taken from the range 1 to ‘n’.
     * The array has only one duplicate but it can be repeated multiple times.
     * Find that duplicate number without using any extra space. You are, however, allowed to modify the input array.
     */
    public static int findNumber(int[] nums) {
        // TODO: Write your code here
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findNumbers(new int[]{2,4,1,2}));
        System.out.println(findNumbers(new int[]{2,3,2,1}));
    }
}
