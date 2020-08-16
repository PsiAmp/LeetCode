package grokking;

import java.util.ArrayList;
import java.util.List;

public class Part_06_CyclicSort {

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
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]-1 != i && nums[i] == nums[nums[i]-1])
                return nums[i];
            int t = nums[i];
            nums[i] = nums[t-1];
            nums[t-1] = t;
        }
        return -1;
    }

    /**
     * No array modification
     */
    public static int findNumberNoModification(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];

        // Find cycle using fast and slow pointers
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        // Find cycle length
        int current = slow;
        int len = 0;
        do {
            current = nums[current];
            len++;
        } while (current != slow);

        // Find start
        int p1 = nums[0];
        int p2 = nums[0];

        // Move p1 len-steps ahead
        while (len > 0) {
            p1 = nums[p1];
            len--;
        }

        // Move both pointers until they meet
        while (p1 != p2) {
            p1 = nums[p1];
            p2 = nums[p1];
        }

        return p1;
    }

    /**
     * We are given an unsorted array containing ‘n’ numbers taken from the range 1 to ‘n’.
     * The array has some duplicates, find all the duplicate numbers without using any extra space.
     */
    public static List<Integer> findAllDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();

        int i = 0;
        while (i < nums.length) {
            if (nums[nums[i]-1] != nums[i]) {
                int t = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = t;
            } else {
                i++;
            }
        }

        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i+1)
                result.add(nums[i]);
        }

        return result;
    }

    /**
     * We are given an unsorted array containing ‘n’ numbers taken from the range 1 to ‘n’.
     * The array originally contained all the numbers from 1 to ‘n’, but due to a data error,
     * one of the numbers got duplicated which also resulted in one number going missing.
     * Find both these numbers.
     */
    public static int[] findCorruptNums(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] != nums[nums[i]-1]) {
                int tmp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = tmp;
            } else {
                i++;
            }
        }

        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i+1) {
                return new int[]{nums[i], i+1};
            }
        }

        return new int[] { -1, -1 };
    }

    /**
     * Given an unsorted array containing numbers, find the smallest missing positive number in it.
     */
    public static int firstMissingPositive(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] > 0 && nums[i]-1 < nums.length && nums[i] != nums[nums[i]-1]) {
                int t = nums[i];
                nums[i] = nums[t-1];
                nums[t-1] = t;
            } else {
                i++;
            }
        }

        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i+1) {
                return i+1;
            }
        }

        return nums.length + 1;
    }

    /**
     * Given an unsorted array containing numbers and a number ‘k’, find the first ‘k’ missing positive numbers in the array.
     */
    public static List<Integer> firstKMissingPositive(int[] nums, int k) {
        List<Integer> missingNumbers = new ArrayList<>();

        int i = 0;
        while (i < nums.length) {
            if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i]-1]) {
                int t = nums[i];
                nums[i] = nums[t -1];
                nums[t-1] = t;
            } else {
                i++;
            }
        }

        int max = 0;
        for (i = 0; i < nums.length && missingNumbers.size() < k; i++) {
            if (nums[i] != i+1) {
                missingNumbers.add(i + 1);
            }
            max = Math.max(nums[i], max);
        }

        while (missingNumbers.size() < k) {
            missingNumbers.add(++max);
        }

        return missingNumbers;
    }

    public static void main(String[] args) {
        System.out.println(firstKMissingPositive(new int[]{2, 3, 4}, 3));
        System.out.println(firstKMissingPositive(new int[]{-2, -3, 4},2));
        System.out.println(firstKMissingPositive(new int[]{3, -1, 4, 5, 5}, 3));
    }
}
