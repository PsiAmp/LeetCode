package grokking;

import java.util.*;
import java.util.regex.Pattern;

public class Part_10_TwoHeaps {

    /**
     * Design a class to calculate the median of a number stream. The class should have the following two methods:
     *
     *     insertNum(int num): stores the number in the class
     *     findMedian(): returns the median of all numbers inserted in the class
     *
     * If the count of numbers inserted in the class is even, the median will be the average of the middle two numbers.
     */

    public PriorityQueue<Integer> large = new PriorityQueue<>();
    public PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());

    public void insertNum(int num) {
        if (large.isEmpty() || large.peek() > num) {
            large.add(num);
        } else {
            small.add(num);
        }

        // Rebalance
        if (large.size() > small.size() + 1) {
            small.add(large.poll());
        } else if (small.size() > large.size()) {
            large.add(small.poll());
        }
    }

    public double findMedian() {
        if (large.isEmpty()) {
            return 0;
        } if (large.size() == small.size()) {
            return (large.peek() + small.peek()) / 2.0;
        }
        return large.peek();
    }

    static final Pattern STARTS_WITH_VOWEL = Pattern.compile("^[aeiouy]", Pattern.CASE_INSENSITIVE);

    public static int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        int n = Capital.length;
        PriorityQueue<Integer> minCapital = new PriorityQueue<>(n, (i1, i2) -> Capital[i1] - Capital[i2]);
        PriorityQueue<Integer> maxProfit = new PriorityQueue<>(n, (i1, i2) -> Profits[i2] - Profits[i1]);


        for (int i = 0; i < n; i++) {
            minCapital.add(i);
        }

        while (k > 0) {
            while (!minCapital.isEmpty() && Capital[minCapital.peek()] <= W) {
                maxProfit.add(minCapital.poll());
            }
            if (!maxProfit.isEmpty()) {
                W += Profits[maxProfit.poll()];
            }
            k--;
        }

        return W;
    }

    static class Interval {
        int start = 0;
        int end = 0;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * Given an array of intervals, find the next interval of each interval.
     * In a list of intervals, for an interval ‘i’ its next interval ‘j’ will have the smallest ‘start’ greater than or equal to the ‘end’ of ‘i’.
     *
     * Write a function to return an array containing indices of the next interval of each input interval.
     * If there is no next interval of a given interval, return -1. It is given that none of the intervals have the same start point.
     * @param intervals
     * @return
     */
    public static int[] findNextInterval(Interval[] intervals) {
        int n = intervals.length;
        int[] result = new int[n];

        PriorityQueue<Integer> minStart = new PriorityQueue<>(n, (i1, i2) -> intervals[i1].start - intervals[i2].start);
        PriorityQueue<Integer> minEnd = new PriorityQueue<>(n, (i1, i2) -> intervals[i1].end - intervals[i2].end);

        for (int i = 0; i < n; i++) {
            minStart.add(i);
            minEnd.add(i);
        }

        while (!minEnd.isEmpty()) {
            int e = minEnd.poll();
            while (!minStart.isEmpty() && intervals[e].end > intervals[minStart.peek()].start) {
                minStart.poll();
            }
            if (!minStart.isEmpty()) {
                result[e] = minStart.peek();
            } else {
                result[e] = -1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Interval[] intervals = new Interval[] { new Interval(2, 3), new Interval(3, 4), new Interval(5, 6) };
        int[] result = findNextInterval(intervals);
        System.out.print("Next interval indices are: ");
        for (int index : result)
            System.out.print(index + " ");
        System.out.println();

        intervals = new Interval[] { new Interval(3, 4), new Interval(1, 5), new Interval(4, 6) };
        result = findNextInterval(intervals);
        System.out.print("Next interval indices are: ");
        for (int index : result)
            System.out.print(index + " ");
    }

    public static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

}
