package problems;

import java.util.ArrayList;

public class BinarySubarraysWithSum_930 {

    public int numSubarraysWithSum(int[] A, int S) {

        if (S == 0) return A.length;

        ArrayList<Integer> integers = new ArrayList<Integer>();

        for (int i = 0; i < A.length; i++) {
            if (A[i] == 1) integers.add(i);
        }

        if (integers.size() < S) return 0;

        int l = integers.get(0);
        for (int i = S-1; i < integers.size(); i++) {

        }

        return 0;

    }

}
