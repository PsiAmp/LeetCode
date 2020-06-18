package problems;

import java.util.Collections;
import java.util.PriorityQueue;

public class FindMedianFromDataStream_295 {

    private PriorityQueue<Integer> large = new PriorityQueue<>();
    private PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
    private boolean even = true;

    /** initialize your data structure here. */
    public FindMedianFromDataStream_295() {

    }

    public void addNum(int num) {
        if (even) {
            large.add(num);
            small.add(large.poll());
        } else {
            small.add(num);
            large.add(small.poll());
        }
        even = !even;
    }

    public double findMedian() {
        if (even) {
            return (small.peek() + large.peek()) / 2.0;
        }
        return small.peek();
    }

}
