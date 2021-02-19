package sandbox;

import java.util.*;

public class Sandbox {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // TODO parameter validation check

        // 1. Data structure that holds nodes sorted by x-position and by value
        // Map of x-position key to Priority Queue of node values
        Map<Integer, Queue<Integer>> positionToValues = new HashMap<>();


        // 2. Tree traversal to fill the data structure
        // DFS passing Node, x coordinate and data structure
        int minX = verticalTraversalDFS(root, 0, positionToValues);

        // 3. Converting data structure to result
        List<List<Integer>> result = new ArrayList<>();

        int x = minX;

        while (positionToValues.containsKey(x)) {
            Queue<Integer> q = positionToValues.get(x);

            List<Integer> l = new ArrayList<>();
            while (!q.isEmpty()) {
                l.add(q.poll());
            }
            result.add(l);

            x++;
        }

        return result;
    }

    private int verticalTraversalDFS(TreeNode root, int x, Map<Integer, Queue<Integer>> positionToValues) {
        if (root == null)
            return x + 1;

        Queue<Integer> q = null;
        if (positionToValues.containsKey(x)) {
            q = positionToValues.get(x);
        } else {
            q = new PriorityQueue<Integer>();
            positionToValues.put(x, q);
        }
        q.add(root.val);

        int x1 = verticalTraversalDFS(root.left, x - 1, positionToValues);
        int x2 = verticalTraversalDFS(root.right, x + 1, positionToValues);

        return Math.min(x1, x2);
    }

    public static int[] distribution(int div, int n) {
        int[] a = new int[div];

        while (n > 0)
            a[(n-- / div) % div]++;

        return a;
    }

    public static int findBestApartment(Map<String, Boolean>[] blocks, String[] requirements) {

        // DIST 2
        // BLOCKS_A 0 0 1 0 1 0 0 1

        // RESULT_A 2 1 0 1 0 1 1 0
        // RESULT_B 4 3 2 1 0 1 1 0
        // RESULT_C 2 1 0 1 0 1 1 0

        int n = blocks.length;
        int[] distances = new int[n];

        for (String req : requirements) {
            int[] currentReqDistances = new int[n];
            int currentDist = n;

            // TODO combine both for-loops into function???
            for (int i = 0; i < blocks.length; i++) {
                if (blocks[i].get(req))
                    currentDist = 0;

                currentReqDistances[i] = currentDist;
                currentDist++;
            }

            for (int i = n - 1; i >= 0; i--) {
                if (blocks[i].get(req))
                    currentDist = 0;

                currentReqDistances[i] = Math.min(currentDist, currentReqDistances[i]);
                currentDist++;
            }

            for (int i = 0; i < distances.length; i++) {
                distances[i] = Math.max(distances[i], currentReqDistances[i]);
            }
        }

        int result = n;

        for (int distance : distances) {
            result = Math.min(result, distance);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(distribution(3, 10000)));
        System.out.println(Arrays.toString(distribution(4, 10000)));
        System.out.println(Arrays.toString(distribution(5, 10000)));
        System.out.println(Arrays.toString(distribution(6, 10000)));
        System.out.println(Arrays.toString(distribution(7, 10000)));
        System.out.println(Arrays.toString(distribution(8, 10000)));
        System.out.println(Arrays.toString(distribution(9, 10000)));
        System.out.println(Arrays.toString(distribution(10, 10000)));
        System.out.println(Arrays.toString(distribution(11, 10000)));

        // а | 4 + с >> Ь & 7
        // (а | (((4 +с)» Ь) & 7))
        int a = 1;
        int b = 2;
        int c = 654;

        c = c + 4;
        c = c >> b;
        c = c & 7;
        a = a | c;

        List<String> ll = new ArrayList<>();
        ll.add("sd" + 'a');


    }

}
