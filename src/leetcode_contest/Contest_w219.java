package leetcode_contest;

/**
 * https://leetcode.com/contest/weekly-contest-219
 */
public class Contest_w219 {

    public static int minOperations(int[] nums, int x) {
        int result = minOperationsR(nums, x, 0, 0, nums.length-1);

        return result > 0 ? result : -1;
    }

    public static int minOperationsR(int[] nums, int x, int ops, int l, int r) {
        if (x == 0)
            return ops;
        if (x < 0 || l >= r)
            return -1;

        int o1 = minOperationsR(nums, x - nums[l], ops+1, l+1, r);
        int o2 = minOperationsR(nums, x - nums[r], ops+1, l, r-1);


        if (o1 != -1 && o2 != -1)
            return Math.min(o1, o2);

        if (o1 == -1)
            return o2;

        if (o2 == -1)
            return o1;

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(minOperations(new int[]{1241, 8769, 9151, 3211, 2314, 8007, 3713, 5835, 2176, 8227, 5251, 9229, 904, 1899, 5513, 7878, 8663, 3804, 2685, 3501, 1204, 9742, 2578, 8849, 1120, 4687, 5902, 9929, 6769, 8171, 5150, 1343, 9619, 3973, 3273, 6427, 47, 8701, 2741, 7402, 1412, 2223, 8152, 805, 6726, 9128, 2794, 7137, 6725, 4279, 7200, 5582, 9583, 7443, 6573, 7221, 1423, 4859, 2608, 3772, 7437, 2581, 975, 3893, 9172, 3, 3113, 2978, 9300, 6029, 4958, 229, 4630, 653, 1421, 5512, 5392, 7287, 8643, 4495, 2640, 8047, 7268, 3878, 6010, 8070, 7560, 8931, 76, 6502, 5952, 4871, 5986, 4935, 3015, 8263, 7497, 8153, 384, 1136},
                894887480));
    }
}