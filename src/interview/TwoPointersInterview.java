package interview;


import java.util.*;

public class TwoPointersInterview {

    public static List<List<Integer>> subarrayProductLessThanK(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int left = 0;
        int mul = 1;

        for (int right = 0; right < arr.length; right++) {
            mul *= arr[right];
            // 1, 2, 3
            // 1 2
            // 1 2 3
            // 2 3

            // 1 2 3 4 = 24
            // 1 2 3 4 1 1 1



            if (mul < target) {
                result.add(Arrays.asList(arr[right]));
                if (right - left > 1) {
                    ArrayList<Integer> ll = new ArrayList<>();
                    for (int i = left; i <= right; i++) {
                        ll.add(arr[i]);
                    }
                    result.add(ll);
                }
            } else if (right - left > 1) {
                List<Integer> ss = new ArrayList<>();
                for (int i = left; i < right; i++) {
                    //ss.add()
                }
            }
        }

        return result;
    }

    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;

        if (nums[left] == target || nums[left] > target)
            return 0;
        if (nums[right] == target)
            return right;
        if (nums[right] < target)
            return right+1;


        while (right != left) {
            int mid = (right + left) / 2;
            if (nums[mid] == target) return mid;

            if (nums[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }

        if (target > nums[left])
            return left+1;

        return left;
    }

    public static int findCircleNum(int[][] M) {
        int[] F = new int[M.length];
        for (int i = 0; i < F.length; i++) {
            F[i] = i;
        }

        for (int i = 0; i < M.length-1; i++) {
            for (int j = i+1; j < M.length; j++) {
                if (M[i][j] == 1 && F[i] != F[j]) {
                    if (F[j] == j)
                        F[j] = F[i];
                    else {
                        int ip = getParent(F, i);
                        int jp = getParent(F, j);
                        if (ip > jp) {
                            F[ip] = F[jp];
                        } else {
                            F[jp] = F[ip];
                        }
                    }
                }
            }
        }

        int count = 0;
        for (int i = 0; i < F.length; i++) {
            if (F[i] == i)
                count++;
        }

        return count;
    }

    public static int getParent(int[] f, int i) {
        while (f[i] != i)
            i = f[i];
        return i;
    }

    public static void main(String[] args) {
        findCircleNum(new int[][]
                        {
                                {1,1,0,0,0,0,0,1,0,0,0,0,0,0,0},
                                {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,1,0,1,1,0,0,0,0,0,0,0,0},
                                {0,0,0,0,1,0,0,0,0,1,1,0,0,0,0},
                                {0,0,0,1,0,1,0,0,0,0,1,0,0,0,0},
                                {0,0,0,1,0,0,1,0,1,0,0,0,0,1,0},
                                {1,0,0,0,0,0,0,1,1,0,0,0,0,0,0},
                                {0,0,0,0,0,0,1,1,1,0,0,0,0,1,0},
                                {0,0,0,0,1,0,0,0,0,1,0,1,0,0,1},
                                {0,0,0,0,1,1,0,0,0,0,1,1,0,0,0},
                                {0,0,0,0,0,0,0,0,0,1,1,1,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
                                {0,0,0,0,0,0,1,0,1,0,0,0,0,1,0},
                                {0,0,0,0,0,0,0,0,0,1,0,0,0,0,1}
                        }
                );
    }

}
