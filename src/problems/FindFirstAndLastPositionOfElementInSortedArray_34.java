package problems;

import java.util.Arrays;
import java.util.Random;

public class FindFirstAndLastPositionOfElementInSortedArray_34 {

    /**
     * @param nums - sorted array, such as tst.A[i] <= tst.A[i+1] (monotonic non-decreasing array)
     * @param left - left index of a range in nums to search target
     * @param right - right index of a range in nums to search target
     * @param target - value to search in array
     * @param searchLeft - true to search left border of range, false to search right border
     * @return index of a target in nums; -1 in case target is not in nums of provided range
     */
    public int binary(int nums[], int left, int right, int target, boolean searchLeft) {
        // In case such left index is higher then right index means target is not in nums
        if (left > right) return -1;

        // Calculate a point in the middle of range defined by left and right
        int mid = (right + left) / 2;

        // Check if target found
        if (nums[mid] == target) {
            // Pick of of the conditions searching left or right border
            if (searchLeft) {
                // Check if target is a left border
                if (mid == 0 || nums[mid - 1] != target)
                    return mid;
                else
                    return binary(nums, left, mid - 1, target, searchLeft);
            } else {
                // Check if target is a right border
                if (mid == nums.length - 1 || nums[mid + 1] != target)
                    return mid;
                else
                    return binary(nums, mid + 1, right, target, searchLeft);
            }
        }

        // Check if target is to the left or right of mid point
        if (nums[mid] < target) {
            // Target is too the right of mid point. Use mid+1 as a left range border
            return binary(nums, mid + 1, right, target, searchLeft);
        } else {
            // Target is to the left of mid point. Ise mid-1 as a right range border
            return binary(nums, left, mid - 1, target, searchLeft);
        }
    }

    public int[] searchRangeBinary(int[] nums, int target) {
        // Init default result with negative indices depicting target not found
        int[] result = {-1, -1};

        // Search for any index of target in nums
        int leftIndex = binary(nums, 0, nums.length - 1, target, true);

        // In case target is not in nums return {-1, -1}
        if (leftIndex == -1) return result;

        int rightIndex = binary(nums, 0, nums.length - 1, target, false);

        //System.out.println("Search range binary result: [" + leftIndex + "; " + rightIndex + "]");

        result[0] = leftIndex;
        result[1] = rightIndex;

        return result;
    }

    /**
     * This solution utilizes binary search with the following optimization:
     * 1. Search for any target in nums
     * 2. Reuse left and right indices to search lefmost and rightmost target positions
     * 3. Use separate functions for each search direction, to save on unnecessary branching
     *
     *  Downside is 3 functions that look almost identical, instead of 1
     *
     * @param nums
     * @param left
     * @param right
     * @param target
     * @return
     */
    public int[] binaryRangeOptimized(int nums[], int left, int right, int target) {
        int[] result = {-1, -1};

        // In case such left index is higher then right index means target is not in nums
        if (left > right) {
            //System.out.println("NOT FOUND");
            return result;
        }

        // Calculate a point in the middle of range defined by left and right
        int mid = (right + left) / 2;

        // Check if target found
        if (nums[mid] == target) {
            // Here we separate search for left and right range borders
            int leftRangeBorder = binaryLeftOptimized(nums, left, mid, target);
            int rightRangeBorder = binaryRightOptimized(nums, mid, right, target);
            result[0] = leftRangeBorder;
            result[1] = rightRangeBorder;
            //System.out.println("OPTIMIZED: [" + leftRangeBorder + "; " + rightRangeBorder + "]");
            return result;
        }

        // Check if target is to the left or right of mid point
        if (nums[mid] < target) {
            // Target is too the right of mid point. Use mid+1 as a left range border
            return binaryRangeOptimized(nums, mid + 1, right, target);
        } else {
            // Target is to the left of mid point. Ise mid-1 as a right range border
            return binaryRangeOptimized(nums, left, mid - 1, target);
        }
    }

    public int binaryLeftOptimized(int[] nums, int left, int right, int target) {
        // Calculate a point in the middle of range defined by left and right
        int mid = (right + left) / 2;

        // Check if mid is a target value and it is leftmost in nums or value before it is not a target
        if (nums[mid] == target && (mid == 0 || nums[mid-1] != target)) {
            // Found left border
            return mid;
        }

        // Check if target is to the left or right of mid point
        if (nums[mid] < target) {
            // Search right side of mid
            return binaryLeftOptimized(nums, mid+1, right, target);
        } else {
            // Search left side of mid
            return binaryLeftOptimized(nums, left, mid-1, target);
        }
    }

    public int binaryRightOptimized(int[] nums, int left, int right, int target) {
        // Calculate a point in the middle of range defined by left and right
        int mid = (right + left) / 2;

        // Check if mid is a target value and it is rightmost in nums or value before it is not a target
        if (nums[mid] == target && (mid == nums.length-1 || nums[mid+1] != target)) {
            return mid;
        }

        if (nums[mid] > target) {
            // Search leftmost of mid
            return binaryRightOptimized(nums, left, mid-1, target);
        } else {
            // Search rightmost of mid
            return binaryRightOptimized(nums, mid+1, right, target);
        }
    }

    public static void main(String[] args) {
        FindFirstAndLastPositionOfElementInSortedArray_34 f = new FindFirstAndLastPositionOfElementInSortedArray_34();

        //int[] nums = {7,8,8,8,8,8,8,8,8,8,9,10};
        //f.searchRangeBinary(nums, 9);
        //f.binaryRangeOptimized(nums, 0, nums.length-1, 11);

        int[] benchmark;
        if (args != null && args.length == 4) {
            benchmark = f.benchmark(Integer.valueOf(args[0]), Integer.valueOf(args[1]), Integer.valueOf(args[2]), Integer.valueOf(args[3]));
        } else {
            benchmark = f.benchmark(1000, 100000, 100000, 1000);
        }
        System.out.println(benchmark[0]);
    }

    /***    BENCHMARKING     ***/

    public int[] benchmark(int arrayAmount, int arraySize, int maxValue, int iterations) {
        // Generate enough arrays to exceed CPU cache limit
        int[][] data = new int[arrayAmount][];
        for (int i = 0; i < data.length; i++) {
            data[i] = generateSortedArray(arraySize, maxValue);
        }

        Random rand = new Random();
        int[] targets = new int[data.length];
        for (int i = 0; i < targets.length; i++) {
            targets[i] = data[i][rand.nextInt(data[i].length)];
        }

        int[] result = new int[2];
        long time;

        time = System.currentTimeMillis();
        for (int j = 0; j < iterations; j++)
            for (int i = 0; i < data.length; i++) {
                result = binaryRangeOptimized(data[i], 0, data[i].length-1, targets[i]);
            }
        System.out.println("OPTIMIZED BINARY TIME: " + (System.currentTimeMillis() - time));

        time = System.currentTimeMillis();
        for (int j = 0; j < iterations; j++)
            for (int i = 0; i < data.length; i++) {
                result = searchRangeBinary(data[i], targets[i]);
            }
        System.out.println("BINARY TIME: " + (System.currentTimeMillis() - time));

        return result;
    }

    public int[] generateSortedArray(int size, int maxValue) {
        int[] result = new int[size];
        Random rand = new Random();
        for (int i = 0; i < result.length; i++) {
            result[i] = rand.nextInt(maxValue);
        }
        Arrays.sort(result);
        return result;
    }

}
