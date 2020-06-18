package grokking;

import java.util.*;

public class P3TwoPointers {

    /**
     * Given an array of sorted numbers and a target sum, find a pair in the array whose sum is equal to the given target.
     */
    public static int[] pairWithTargetSum(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            if (nums[left] + nums[right] > target) {
                right--;
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                return new int[]{left, right};
            }
        }

        return new int[]{-1, -1};
    }

    public static int[] pairWithTargetSumUsingHashTable(int[] nums, int target) {
        HashMap<Integer, Integer> n = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (n.containsKey(target - nums[i])) {
                return new int[]{n.get(target - nums[i]), i};
            } else {
                n.put(nums[i], i);
            }
        }

        return new int[]{-1, -1};
    }

    /**
     * Given an array of sorted numbers, remove all duplicates from it. You should not use any extra space;
     * after removing the duplicates in-place return the new length of the array.
     */
    public static int removeDuplicates(int[] a) {
        int nonDuplacate = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[nonDuplacate] != a[i]) {
                a[++nonDuplacate] = a[i];
            }
        }

        return nonDuplacate + 1;
    }

    public static int removeElement(int[] nums, int key) {
        int keyIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != key) {
                nums[keyIndex] = nums[i];
                keyIndex++;
            }
        }
        return keyIndex;
    }

    public static int[] makeSquares(int[] nums) {
        int[] sq = new int[nums.length];

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int numLeft = nums[left] * nums[left];
            int numRight = nums[right] * nums[right];
            if (numLeft > numRight) {
                sq[right - left] = numLeft;
                left++;
            } else {
                sq[right - left] = numRight;
                right--;
            }
        }

        return sq;
    }

    /**
     * Given an array of unsorted numbers, find all unique triplets in it that add up to zero.
     */
    public static List<List<Integer>> searchTriplets(int[] nums) {
        List<List<Integer>> triplets = new ArrayList<>();
        Arrays.sort(nums);

        for (int left = 0; left < nums.length - 2; left++) {
            // No triplets with zero sum can be found for left value bigger than 0
            if (nums[left] > 0) break;

            // Since triplets should be unique, skip same elements
            if (left > 0 && nums[left - 1] == nums[left])
                continue;

            int right = nums.length - 1;
            int mid = left + 1;

            while (mid < right) {
                int currentSum = nums[mid] + nums[right];

                if (nums[left] + currentSum == 0) {
                    triplets.add(Arrays.asList(nums[left], nums[mid], nums[right]));
                    mid++;
                    right--;

                    while (nums[mid - 1] == nums[mid] && mid < right) {
                        mid++;
                    }
                    while (nums[right + 1] == nums[right] && mid < right) {
                        right--;
                    }
                } else if (currentSum > -nums[left]) {
                    right--;
                } else {
                    mid++;
                }
            }
        }

        return triplets;
    }

    public static int threeSumClosest(int[] arr, int targetSum) {
        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;


            while (left < right) {
                int diff = targetSum - arr[i] - arr[left] - arr[right];

                if (diff == 0)
                    return targetSum;

                if (Math.abs(diff) < Math.abs(minDiff)) {
                    minDiff = diff;
                }

                if (diff > 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return targetSum - minDiff;
    }

    public static int searchTripletsWithSmallerSum(int[] arr, int target) {
        int count = 0;
        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;

            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];

                if (sum < target) {
                    count += right - left;
                    left++;
                } else {
                    right--;
                }
            }
        }

        return count;
    }

    public static List<List<Integer>> tripletWithSmallerSumList(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            int currentTarget = target - nums[i];

            while (left < right) {
                int d = currentTarget - nums[left] - nums[right];

                if (d > 0) {
                    for (int j = right; j > left; j--) {
                        result.add(Arrays.asList(nums[i], nums[left], nums[j]));
                    }
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }

    public static List<List<Integer>> subarrayProductLessThanK(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int left = 0;
        int product = 1;

        for (int right = 0; right < arr.length; right++) {
            product *= arr[right];

            while (product >= target && left < arr.length) {
                product /= arr[left];
                left++;
            }

            List<Integer> tempList = new LinkedList<>();
            for (int i = right; i >= left; i--) {
                tempList.add(0, arr[i]);
                result.add(new ArrayList<>(tempList));
            }

        }

        return result;
    }

    public static List<List<Integer>> findSubarrays(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int product = 1, left = 0;

        for (int right = 0; right < arr.length; right++) {
            product *= arr[right];

            while (product >= target && left < arr.length) {
                product /= arr[left++];
            }

            // since the product of all numbers from left to right is less than the target therefore,
            // all subarrays from left to right will have a product less than the target too; to avoid
            // duplicates, we will start with a subarray containing only arr[right] and then extend it

            List<Integer> tempList = new LinkedList<>();

            for (int i = right; i >= left; i--) {
                tempList.add(0, arr[i]);
                result.add(new ArrayList<>(tempList));
            }
        }
        return result;
    }

    public static List<List<Integer>> findSubarrays2(int[] arr, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int product = 1;
        int left = 0;

        for (int right = 0; right < arr.length; right++) {
            product *= arr[right];

            while (product >= target && left < arr.length) {
                product /= arr[left++];
            }

            List<Integer> ll = new LinkedList<>();
            for (int i = right; i >= left; i--) {
                ll.add(0, arr[i]);
                res.add(new ArrayList<>(ll));
            }
        }

        return res;
    }

    public static void dutchFlag(int[] a) {
        int zeros = 0;
        int ones = 0;

        // 1, 0, 2, 1, 0
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 2 && i < a.length - 1) {
                swap(a, i, i + 1);
            }

            if (a[i] == 0) {
                if (i > zeros) {
                    swap(a, i, zeros);
                    zeros++;
                }
            } else if (a[i] == 1) {
                if (i > zeros + ones) {
                    swap(a, i, zeros + ones);
                    ones++;
                }
            }
        }

        System.out.println();
    }

    static int sss = 0;

    public static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
        sss++;
    }

    public static void dutchFlag2(int[] a) {
        int left = 0;
        int right = a.length - 1;

        for (int i = 0; i <= right; ) {
            if (a[i] == 0) {
                swap(a, i, left);
                left++;
                i++;
            } else if (a[i] == 1) {
                i++;
            } else {
                swap(a, i, right);
                right--;
            }
        }
    }

    public static int subarraySum(int[] nums, int k) {
        int sum = 0;
        int result = 0;
        HashMap<Integer, Integer> sumsMap = new HashMap<>();
        sumsMap.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (sumsMap.containsKey(sum - k)) {
                result += sumsMap.get(sum - k);
            }

            sumsMap.put(sum, sumsMap.getOrDefault(sum, 0) + 1);
        }

        return result;
    }

    public static boolean compareWithBackspaces(String str1, String str2) {
        int i1 = str1.length() - 1;
        int i2 = str2.length() - 1;

        while (i1 >= 0 && i2 >= 0) {
            while (str1.charAt(i1) == '#' && i1 >= 0) {
                i1 -= 2;
            }
            while (str2.charAt(i2) == '#' && i2 >= 0) {
                i2 -= 2;

            }

            if (i1 >= 0 && i2 >= 0 && str1.charAt(i1) != str2.charAt(i2))
                return false;

            i1--;
            i2--;
        }

        return i1 - i2 == 0;
    }

    public static boolean compareWithBackspaces2(String S, String T) {
        int i1 = S.length()-1;
        int i2 = T.length()-1;

        while (i1 >= 0 || i2 >= 0) {
            i1 = getNextChar(S, i1);
            i2 = getNextChar(T, i2);

            if (i1 < 0 && i2 < 0)
                return true;

            if (i1 < 0 || i2 < 0)
                return false;

            if (S.charAt(i1) != T.charAt(i2))
                return false;

            i1--;
            i2--;
        }

        return i1 - i2 == 0;
    }

    public static int getNextChar(String s, int i) {
        int counter = 0;
        while (i >= 0) {
            if (s.charAt(i) == '#') {
                counter++;
            } else if (counter > 0) {
                counter--;

            } else {
                return i;
            }
            i--;
        }

        return i;
    }


    public static int shortestWindowSort(int[] arr) {
        int left = 0;
        int right = arr.length-1;

        while (left < arr.length-1 && arr[left] <= arr[left+1])
            left++;

        if (left == arr.length-1)
            return 0;

        while (right > 0 && arr[right] >= arr[right-1])
            right--;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = left; i <= right; i++) {
            if (arr[i] < min)
                min = arr[i];
            if (arr[i] > max)
                max = arr[i];
        }

        while (left > 0 && arr[left-1] > min)
            left--;

        while (right < arr.length-1 && arr[right+1] < max)
            right++;

        return right-left+1;
    }

    public static void main(String[] args) {
        System.out.println(shortestWindowSort(new int[] { 1, 2, 5, 3, 7, 10, 9, 12 }));
        System.out.println(shortestWindowSort(new int[] { 1, 3, 2, 0, -1, 7, 10 }));
        System.out.println(shortestWindowSort(new int[] { 1, 2, 3 }));
        System.out.println(shortestWindowSort(new int[] { 3, 2, 1 }));
    }

}
