package leetcode_contest;

import java.util.*;

public class Contest_w241 {

    public int subsetXORSum(int[] nums) {
        int res = 0;

        List<Integer> sets = new ArrayList<>();
        sets.add(0);

        for (int num : nums) {
            int n = sets.size();
            for (int i = 0; i < n; i++) {
                int set = sets.get(i);
                set ^= num;
                sets.add(set);
            }
        }


        for (Integer set : sets) {
            res += set;
        }

        return res;
    }

    static class FindSumPairs {

        Map<Integer, Integer> m1 = new HashMap<>();
        Map<Integer, Integer> m2 = new HashMap<>();

        int[] nums1;
        int[] nums2;
        private int[] nums1Unique;

        public FindSumPairs(int[] nums1, int[] nums2) {
            this.nums1 = nums1;
            this.nums2 = nums2;

            Set<Integer> s = new HashSet<>();
            for (int num : nums1) {
                m1.put(num, m1.getOrDefault(num, 0) + 1);
                s.add(num);
            }

            nums1Unique = new int[s.size()];
            int i = 0;
            for (Integer num : s) {
                nums1Unique[i++] = num;
            }
            Arrays.sort(nums1Unique);

            for (int num : nums2)
                m2.put(num, m2.getOrDefault(num, 0) + 1);
        }

        public void add(int index, int val) {
            m2.put(nums2[index], m2.get(nums2[index]) - 1);
            nums2[index] += val;
            m2.put(nums2[index], m2.getOrDefault(nums2[index], 0) + 1);
        }

        public int count(int tot) {
            int count = 0;

            //for (Integer m1Key : m1.keySet()) {
            for (int num1 : nums1Unique) {
                if (num1 >= tot)
                    break;
                int a = m1.get(num1);
                int b = m2.getOrDefault(tot - num1, 0);
                count += a * b;
            }

            return count;
        }
    }

    public int minSwaps(String s) {
        int n = s.length();
        boolean isEven = n % 2 == 0;

        int odd = 0;
        int even = 0;

        for (int i = 0; i < n; i++) {
            boolean isOne = s.charAt(i) - '0' == 1;

            if (isOne)
                if (i % 2 == 0)
                    even++;
                else
                    odd++;
        }

        int oddEvenSum = odd + even;
        int oddPositions = n / 2;
        int evenPositions = oddPositions;

        if (!isEven)
            evenPositions += 1;

        if (oddEvenSum != oddPositions && oddEvenSum != evenPositions)
            return -1;

        if (isEven)
            return Math.min(evenPositions - even, oddPositions - odd);
        else
            if (oddEvenSum == oddPositions)
                return even;
            else
                return odd;
    }

    public static void main(String[] args) {

        Contest_w241 c = new Contest_w241();
        c.minSwaps("111000");
        c.minSwaps("00011110110110000000000110110101011101111011111101010010010000000000000001101101010010001011110000001101111111110000110101101101001011000011111011101101100110011111110001100110001110000000001100010111110100111001001111100001000110101111010011001");
        c.minSwaps("1110");
        c.minSwaps("100");
        c.minSwaps("1100110");

        FindSumPairs f = new FindSumPairs(new int[]{1, 1, 2, 2, 2, 3}, new int[]{1, 4, 5, 2, 5, 4});
        System.out.println(f.count(7));
        f.add(3, 2);
        System.out.println(f.count(8));
        System.out.println(f.count(4));

        FindSumPairs f1 = new FindSumPairs(new int[]{9,70,14,9,76}, new int[]{26,26,58,23,74,68,68,78,58,26});
        f1.add(6, 10);
        f1.add(5, 6);
        System.out.println(f1.count(32));


    }
}
