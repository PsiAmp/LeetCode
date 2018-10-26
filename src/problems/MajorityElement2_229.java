package problems;

import java.util.ArrayList;
import java.util.List;

public class MajorityElement2_229 {

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();

        int counter[] = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums.length) System.out.println(nums[i]);
            else counter[nums[i]]++;
        }

        for (int i = 0; i < counter.length; i++) {
            if (counter[i] > nums.length/3) result.add(i);
        }

        return result;
    }
}
