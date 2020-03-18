package problems;

public class MaxConsecutiveOnes3_1004 {

    public int longestOnes(int[] A, int K) {
        int left = 0;
        int right = 0;
        int maxLength = 0;

        int[] zerosAndOnes = {0, 0};
        while (right < A.length) {
            if (K - zerosAndOnes[0] >= 0) {
                zerosAndOnes[A[right]]++;

                int len = zerosAndOnes[1] + Math.min(zerosAndOnes[0], K);
                if (len > maxLength) maxLength = len;

                right++;
            } else {
                zerosAndOnes[A[left]]--;

                left++;
            }
        }

        return maxLength;
    }

    // TODO implement algorithm above with precomputed array of lengths. Ones will be positive, zeroes will be negative
    public int longestOnesWithStack(int[] A, int K) {
        int left = 0;
        int right = 0;
        int maxLength = 0;

        return maxLength;
    }

    public static void main(String[] args) {
        MaxConsecutiveOnes3_1004 m = new MaxConsecutiveOnes3_1004();
        int[] data = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        int i = m.longestOnes(data, 3);
        System.out.println(i);
    }

}
