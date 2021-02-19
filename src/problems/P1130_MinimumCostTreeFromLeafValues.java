package problems;

import java.util.Stack;

public class P1130_MinimumCostTreeFromLeafValues {

    public int mctFromLeafValues(int[] arr) {
        Stack<Integer> s = new Stack<>();
        s.add(Integer.MAX_VALUE);
        int result = 0;

        for (int v : arr) {
            while (s.peek() <= v) {
                int mid = s.pop();
                result += mid * Math.min(s.peek(), v);
            }
            s.add(v);
        }

        int[] a = new int[]{9,5,7,8,2,4,6,0,1,3};

        while (s.size() > 2)
            result += s.pop() * s.peek();

        return result;
    }

    public static void main(String[] args) {
        P1130_MinimumCostTreeFromLeafValues p = new P1130_MinimumCostTreeFromLeafValues();
        int sum = p.mctFromLeafValues(new int[]{6,2,4,3,5});
        System.out.println(sum);

        Math.random();
    }


}
