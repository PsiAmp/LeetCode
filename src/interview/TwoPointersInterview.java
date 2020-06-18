package interview;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}
