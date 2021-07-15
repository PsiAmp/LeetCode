package problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P0869_ReorderedPowerof2 {

    public boolean reorderedPowerOf2(int N) {
        // convert to char array
        List<Integer> nums = new ArrayList<>();

        while (N > 0) {
            nums.add(N % 10);
            N /= 10;
        }

        // go through all the valid permutations of nums

        Queue<List<Integer>> permutations = new LinkedList<>();
        permutations.add(new ArrayList<>());

        for (int num : nums) {
            int len = permutations.size();

            for (int i = 0; i < len; i++) {
                List<Integer> oldPerm = permutations.poll();

                for (int j = 0; j <= oldPerm.size(); j++) {
//                    if (j == 0 && num == 0)
//                        continue;

                    List<Integer> perm = new ArrayList<>(oldPerm);
                    perm.add(j, num);

                    if (perm.size() == nums.size() && perm.get(0) != 0) {
                        if (isAPowerOfTwo(perm))
                            return true;
                    } else {
                        permutations.add(perm);
                    }
                }
            }
        }

        return false;

        // 2. check if given permutation is a power of 2
        // think of analytical way of rearranging determening if num can be a power of two
    }

    private boolean isAPowerOfTwo(List<Integer> nums) {
        // todo convert
        int n = 0;

        for (int num : nums) {
            n *= 10;
            n += num;
        }

        return (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {
        P0869_ReorderedPowerof2 p = new P0869_ReorderedPowerof2();
        System.out.println(p.reorderedPowerOf2(4201));
    }
}
