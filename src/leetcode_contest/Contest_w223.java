package leetcode_contest;

import java.util.*;

/**
 * https://leetcode.com/contest/weekly-contest-223
 */
public class Contest_w223 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public int[] decode(int[] encoded, int first) {
        int[] result = new int[encoded.length + 1];
        result[0] = first;

        for (int i = 0; i < encoded.length; i++) {
            result[i + 1] = result[i] ^ encoded[i];
        }

        return result;
    }

    public ListNode swapNodes(ListNode head, int k) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = dummy;

        ListNode left = null;
        // D-1-2-3-4-5-6-7-N
        //   ^       ^
        int count = 0;
        while (fast != null) {
            if (count == k - 1)
                left = fast;

            if (count > k)
                slow = slow.next;

            fast = fast.next;
            count++;
        }

        ListNode right = slow;

        ListNode ln = left.next;
        ListNode rn = right.next;

        left.next = rn;
        right.next = ln;

        ListNode lnn = ln.next;
        ListNode rnn = rn.next;

        ln.next = rnn;
        rn.next = lnn;

        return dummy.next;
    }

    /**
     * https://leetcode.com/contest/weekly-contest-223/problems/minimize-hamming-distance-after-swap-operations/
     */
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;

        // Create Union Find structure for all swaps
        int[] union = new int[n];
        for (int i = 0; i < union.length; i++) {
            union[i] = i;
        }

        for (int[] swap : allowedSwaps) {
            int indexA = swap[0];
            int indexB = swap[1];

            int rootA = findUFRoot(union, indexA);
            int rootB = findUFRoot(union, indexB);
            union[rootA] = rootB;
        }

        // Array of groups of values
        Map<Integer, Integer>[] groups = new Map[n];
        for (int i = 0; i < groups.length; i++) {
            groups[i] = new HashMap<>();
        }

        for (int i = 0; i < n; i++) {
            int groupId = findUFRoot(union, i);
            Map<Integer, Integer> group = groups[groupId];
            group.put(source[i], group.getOrDefault(source[i], 0) + 1);
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            int targetGroup = findUFRoot(union, i);
            if (groups[targetGroup].containsKey(target[i])) {
                int valueInGroupCount = groups[targetGroup].get(target[i]);
                if (valueInGroupCount > 0) {
                    count++;
                    groups[targetGroup].put(target[i], valueInGroupCount - 1);
                }
            }
        }

        return n - count;
    }

    private int findUFRoot(int[] union, int id) {
        while (union[id] != id) {
            union[id] = union[union[id]];
            id = union[id];
        }

        return id;
    }

    public static void main(String[] args) {
        Contest_w223 c = new Contest_w223();
        System.out.println(c.minimumHammingDistance(new int[]{1,2,3,4}, new int[]{2,1,4,5}, new int[][]{{0, 1}, {2, 3}}));
        System.out.println(c.minimumHammingDistance(new int[]{5, 1, 2, 4, 3}, new int[]{1, 5, 4, 2, 3}, new int[][]{{0, 4}, {4, 2}, {1, 3}, {1, 4}}));
        System.out.println(c.minimumHammingDistance(new int[]{67,71,32,48,71,12,64,20,29,47,90,13,17,94,81,62,24,20,22}, new int[]{67,6,32,48,36,97,70,29,29,15,90,73,32,94,38,61,24,20,22}, new int[][]{{16,17},{10,4},{6,4},{5,4},{13,15},{7,18},{4,13},{18,12},{14,15},{17,8},{7,11},{18,11},{6,15}}));
    }

}
