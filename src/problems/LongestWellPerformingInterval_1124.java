package problems;

/**
 * We are given hours, a list of the number of hours worked per day for a given employee.
 *
 * tst.A day is considered to be a tiring day if and only if the number of hours worked is (strictly) greater than 8.
 *
 * tst.A well-performing interval is an interval of days for which the number of tiring days is strictly larger than the number of non-tiring days.
 *
 * Return the length of the longest well-performing interval.
 *
 *
 *
 * Example 1:
 *
 * Input: hours = [9,9,6,0,6,6,9]
 * Output: 3
 * Explanation: The longest well-performing interval is [9,9,6].
 *
 *
 *
 * Constraints:
 *
 *     1 <= hours.length <= 10000
 *     0 <= hours[i] <= 16
 */

public class LongestWellPerformingInterval_1124 {

    public int longestWPI(int[] hours) {
        int l = 0;
        int r = 0;
        int s = 0;
        while (r < hours.length) {
            if (hours[r] > 8) {
                if (s < 0) s = 0;
                s++;
            } else {
                s--;
            }

            if (s <= 0)
                l++;

            r++;
        }

        return r-l;
    }

    public static void main(String[] args) {
        int[] h = {9,9,6,0,6,6,9};
        System.out.println(new LongestWellPerformingInterval_1124().longestWPI(h));

    }

}
