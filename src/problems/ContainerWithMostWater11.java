package problems;

public class ContainerWithMostWater11 {

    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length-1;
        int result = 0;
        while (l < r) {
            int a = Math.min(height[l], height[r]) * (r-l);
            if (a > result) result = a;
            if (height[l] < height[r]) l++; else r--;
        }
        return result;
    }
}
