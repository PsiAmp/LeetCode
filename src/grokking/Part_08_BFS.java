package grokking;

import java.util.*;

public class Part_08_BFS {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode next;

        TreeNode(int x) {
            val = x;
        }

        void printLevelOrder() {
            TreeNode nextLevelRoot = this;
            while (nextLevelRoot != null) {
                TreeNode current = nextLevelRoot;
                nextLevelRoot = null;
                while (current != null) {
                    System.out.print(current.val + " ");
                    if (nextLevelRoot == null) {
                        if (current.left != null)
                            nextLevelRoot = current.left;
                        else if (current.right != null)
                            nextLevelRoot = current.right;
                    }
                    current = current.next;
                }
                System.out.println();
            }
        }
    }

    /**
     * Given a binary tree, populate an array to represent its level-by-level traversal.
     * You should populate the values of all nodes of each level from left to right in separate sub-arrays.
     */
    public static List<List<Integer>> levelOrderTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> currentLevel = new ArrayList<>(q.size());
            while (size > 0) {
                TreeNode node = q.poll();
                currentLevel.add(node.val);
                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);

                size--;
            }
            result.add(currentLevel);
        }

        return result;
    }

    /**
     * Given a binary tree, populate an array to represent its level-by-level traversal in reverse order,
     * i.e., the lowest level comes first.
     * You should populate the values of all nodes in each level from left to right in separate sub-arrays.
     */
    public static List<List<Integer>> reverseLevelOrderTraversal(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> currentLevel = new ArrayList<>(size);
            while (size > 0) {
                TreeNode node = q.poll();
                currentLevel.add(node.val);

                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);

                size--;
            }
            result.add(0, currentLevel);
        }

        return result;
    }

    /**
     * Given a binary tree, populate an array to represent its zigzag level order traversal.
     * You should populate the values of all nodes of the first level from left to right,
     * then right to left for the next level and keep alternating in the same manner for the following levels.
     */
    public static List<List<Integer>> zigzagTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean isForward = true;
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> currentLevel = new ArrayList<>(size);
            while (size > 0) {
                TreeNode node = q.poll();

                if (isForward){
                    currentLevel.add(node.val);
                } else {
                    currentLevel.add(0, node.val);
                }

                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }

                size--;
            }
            result.add(currentLevel);
            isForward = !isForward;
        }

        return result;
    }

    /**
     * Given a binary tree, populate an array to represent the averages of all of its levels.
     */
    public static List<Double> findLevelAverages(TreeNode root) {
        ArrayList<Double> result = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            double levelSum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
                levelSum += node.val;
            }
            result.add(levelSum / size);
        }

        return result;
    }

    /**
     * Find the minimum depth of a binary tree.
     * The minimum depth is the number of nodes along the shortest path from the root node to the nearest leaf node.
     * Memory complexity O(N/2) == O(N)
     * Time complexity O(N)
     */
    public static int getMinDeapth(TreeNode root) {
        int minDeapth = 0;

        //
        Queue<TreeNode> q = new LinkedList<>();
        if (root != null)
            q.add(root);

        while (!q.isEmpty()) {
            minDeapth++;

            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();

                if (node.left == null && node.right == null)
                    return minDeapth;
                q.add(node.left);
                q.add(node.right);
            }
        }

        return minDeapth;
    }

    /**
     * Given a binary tree and a node, find the level order successor of the given node in the tree.
     * The level order successor is the node that appears right after the given node in the level order traversal.
     */
    public static TreeNode findSuccessor(TreeNode root, int key) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node.val == key)
                break;

            if (node.left != null)
                q.add(node.left);
            if (node.right != null)
                q.add(node.right);
        }

        return q.peek();
    }


    /**
     * Given a binary tree, connect each node with its level order successor.
     * The last node of each level should point to a null node.
     *
     * Connect each node of the same level from left to right. Rightmost next item should be null
     */
    public static void connectLevelOrderSiblings(TreeNode root) {
        if (root == null)
            return;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            TreeNode nextNode = null;

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();

                node.next = nextNode;
                nextNode = node;

                if (node.right != null)
                    q.add(node.right);
                if (node.left != null)
                    q.add(node.left);
            }
        }
    }

    /**
     * Given a binary tree, connect each node with its level order successor.
     * The last node of each level should point to the first node of the next level.
     */
    public static void connectAllSiblings(TreeNode root) {
        if (root == null)
            return;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node.left != null)
                q.add(node.left);
            if (node.right != null)
                q.add(node.right);
            node.next = q.peek();
        }
    }

    public static void main(String[] args) {
        int size = 10;
        TreeNode r = new TreeNode(0);
        Queue<TreeNode> q = new LinkedList();
        q.add(r);
        for (int i = 0; i < size; i++) {
            int sz = q.size();
            while (sz > 0 && i < size) {
                TreeNode node = q.poll();

                node.left = new TreeNode(i);
                q.add(node.left);
                i++;

                if (i < size) {
                    node.right = new TreeNode(i);
                    q.add(node.right);
                }

                sz--;
                i++;
            }

        }

        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        connectLevelOrderSiblings(root);
        System.out.println("Level order traversal using 'next' pointer: ");
        root.printLevelOrder();
    }

}
