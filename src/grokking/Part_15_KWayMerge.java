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

    /**
     * Given an N∗N matrix where each row and column is sorted in ascending order, find the Kth smallest element in the matrix.
     *
     * N - number of rows
     * Time complexity: O(min(K, N) + K*logN)
     * Space complexity: O(N)
     */
    public static int findKthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> matrix[a[0]][a[1]] - matrix[b[0]][b[1]]);
        for (int i = 0; i < matrix.length && i < k; i++)
            pq.add(new int[]{i, 0});

        while (!pq.isEmpty()) {
            k--;
            int[] el = pq.poll();
            if (k == 0)
                return matrix[el[0]][el[1]];

            el[1]++;
            if (el[1] < matrix[el[0]].length)
                pq.add(el);
        }

        return -1;
    }

    public static int findKthSmallest2(int[][] matrix, int k) {
        int n = matrix.length;
        int l = matrix[0][0];
        int r = matrix[n-1][n-1];

        while (l < r) {
            int mid = l + (r - l) / 2;
            int[] smallLargePair = new int[]{matrix[0][0], matrix[n-1][n-1]};
            int count = countLessEqual(matrix, mid, smallLargePair);

            if (count < k)
                l = smallLargePair[1];
            else
                r = smallLargePair[0];
        }

        return l;
    }

    public static int countLessEqual(int[][] matrix, int mid, int[] smallLargePair) {
        int n = matrix.length;
        int row = n-1;
        int col = 0;
        int count = 0;

        while (row >= 0 && col < n) {
            if (mid < matrix[row][col]) {
                smallLargePair[1] = Math.min(smallLargePair[1], matrix[row][col]);
                row--;
            } else {
                smallLargePair[0] = Math.max(smallLargePair[0], matrix[row][col]);
                col++;
                count += row + 1;
            }
        }

        return count;
    }

    /**
     * Given ‘M’ sorted arrays, find the smallest range that includes at least one number from each of the ‘M’ lists.
     */
    public static int[] findSmallestRange(List<Integer[]> lists) {
        PriorityQueue<int[]> pqMin = new PriorityQueue<>((a, b) -> lists.get(a[0])[a[1]] - lists.get(b[0])[b[1]]);
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < lists.size(); i++) {
            Integer[] nums = lists.get(i);
            if (nums != null && nums.length > 0) {
                pqMin.add(new int[]{i, 0});
                max = Math.max(max, nums[0]);
            }
        }

        int[] result = new int[] { -1, -1 };
        int range = Integer.MAX_VALUE;

        while (pqMin.size() == lists.size()) {
            int[] nums = pqMin.poll();
            int min = lists.get(nums[0])[nums[1]];
            if (max - min < range) {
                result[0] = min;
                result[1] = max;
                range = max - min;
             }

            nums[1]++;

            if (nums[1] < lists.get(nums[0]).length) {
                pqMin.add(nums);
                max = Math.max(max, lists.get(nums[0])[nums[1]]);
            }
        }

        return result;
    }

    /**
     * Given two sorted arrays in descending order, find ‘K’ pairs with the largest sum where each pair consists of numbers from both the arrays.
     */
    public static List<int[]> findKLargestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] + a[1] - b[0] - b[1]);

        for (int i = 0; i < nums1.length && i < k; i++) {
            for (int j = 0; j < nums2.length && j < k; j++) {
                if (pq.size() < k) {
                    pq.add(new int[]{nums1[i], nums2[j]});
                } else {
                    if (pq.peek()[0] + pq.peek()[1] < nums1[i] + nums2[j]) {
                        pq.poll();
                        pq.add(new int[]{nums1[i], nums2[j]});
                    } else {
                        break;
                    }
                }
            }
        }

        return new ArrayList<>(pq);
    }

    public static void main(String[] args) {
        int[] l1 = new int[] { 9, 8, 2 };
        int[] l2 = new int[] { 6, 3, 1 };
        List<int[]> result = findKLargestPairs(l1, l2, 3);
        System.out.print("Pairs with largest sum are: ");
        for (int[] pair : result)
            System.out.print("[" + pair[0] + ", " + pair[1] + "] ");
    }

}