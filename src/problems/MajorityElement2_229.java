package problems;

import java.util.ArrayList;
import java.util.List;

public class MajorityElement2_229 {

    /**
     * Boyer–Moore majority vote algorithm
     * https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();

        if (nums == null || nums.length == 0) return result;

        int n1 = nums[0];
        int n2 = nums[0];
        int c1 = 0;
        int c2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (n1 == nums[i]) {
                c1++;
            } else if (n2 == nums[i]) {
                c2++;
            } else if (c1 == 0) {
                n1 = nums[i];
                c1 = 1;
            } else if (c2 == 0) {
                n2 = nums[i];
                c2 = 1;
            } else {
                c1--;
                c2--;
            }
        }

        c1 = 0;
        c2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (n1 == nums[i]) {
                c1++;
            } else if (n2 == nums[i]) {
                c2++;
            }
        }

        if (c1 > nums.length/3) result.add(n1);
        if (c2 > nums.length/3) result.add(n2);

        return result;
    }

    public int[] data = {2,2,9,3,9,3,9,3,9,3,9,3,9,3,9,3,9};
    public static void main(String[] args) {
        MajorityElement2_229 majorityElement2_229 = new MajorityElement2_229();
        majorityElement2_229.majorityElement(majorityElement2_229.data);
    }
}
