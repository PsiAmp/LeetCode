package grokking;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Part_15_KWayMerge {

    static class ListNode {
        int value;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    /**
     * Given an array of ‘K’ sorted LinkedLists, merge them into one sorted list.
     */
    public static ListNode mergeKSortedLists(ListNode[] lists) {
        ListNode result = new ListNode(-1);

        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.value - b.value);
        for (ListNode list : lists) {
            pq.add(list);
        }

        ListNode node = result;
        while (!pq.isEmpty()) {
            node.next = pq.poll();
            node = node.next;
            if (node.next != null)
                pq.add(node.next);
        }

        return result.next;
    }



    /**
     * Given ‘M’ sorted arrays, find the K’th smallest number among all the arrays.
     */
    static class ArrayNode {
        Integer[] nums;
        int index;

        public ArrayNode(Integer[] a, int index) {
            this.nums = a;
            this.index = index;
        }
    }

    public static int findKthSmallest(List<Integer[]> lists, int k) {
        PriorityQueue<ArrayNode> pq = new PriorityQueue<>((a, b) -> a.nums[a.index] - b.nums[b.index]);
        for (Integer[] nums : lists) {
            if (nums != null && nums.length > 0)
                pq.add(new ArrayNode(nums, 0));
        }

        while (!pq.isEmpty()) {
            k--;
            ArrayNode arrayNode = pq.poll();
            if (k == 0)
                return arrayNode.nums[arrayNode.index];

            arrayNode.index++;
            if (arrayNode.index < arrayNode.nums.length)
                pq.add(arrayNode);
        }

        return -1;
    }

    public static void main(String[] args) {
        Integer[] l1 = new Integer[] { 2, 6, 8 };
        Integer[] l2 = new Integer[] { 3, 6, 7 };
        Integer[] l3 = new Integer[] { 1, 3, 4 };
        List<Integer[]> lists = new ArrayList<Integer[]>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        int result = findKthSmallest(lists, 5);
        System.out.print("Kth smallest number is: " + result);
    }

}
