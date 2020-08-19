package grokking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part_09_DFS {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Given a binary tree and a number ‘S’,
     * find if the tree has a path from root-to-leaf such that the sum of all the node values of that path equals ‘S’.
     */
    public static boolean treePathSum(TreeNode root, int sum) {
        if (root == null)
            return false;

        if (root.val == sum && root.left == null && root.right == null)
            return true;

        sum -= root.val;
        return treePathSum(root.left, sum) || treePathSum(root.right, sum);
    }

    /**
     * Given a binary tree and a number ‘S’,
     * find all paths from root-to-leaf such that the sum of all the node values of each path equals ‘S’.
     *
     * Space complexity: O(N * logN) height * number of leaves (logN * N/2)
     */
    public static List<List<Integer>> findAllTreePaths(TreeNode root, int sum) {
        List<List<Integer>> allPaths = new ArrayList<>();
        if (root == null)
            return allPaths;

        sum -= root.val;

        if (root.left == null && root.right == null) {
            if (sum == 0) {
                List<Integer> list = new ArrayList<>();
                list.add(root.val);
                allPaths.add(list);
                return allPaths;
            }
            return allPaths;
        }

        List<List<Integer>> allPathsLeft = findAllTreePaths(root.left, sum);
        for (List<Integer> path : allPathsLeft) {
            path.add(0, root.val);
            allPaths.add(path);
        }

        List<List<Integer>> allPathsRight = findAllTreePaths(root.right, sum);
        for (List<Integer> path : allPathsRight) {
            path.add(0, root.val);
            allPaths.add(path);
        }

        return allPaths;
    }

    private static void findPathsRecursive(TreeNode currentNode, int sum, List<Integer> currentPath, List<List<Integer>> allPaths) {
        if (currentNode == null)
            return;

        currentPath.add(currentNode.val);
    }

    public static List<List<Integer>> findAllTreePaths2(TreeNode root, int sum) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        findAllTreePathsRecursive(root, allPaths, currentPath, sum);
        return allPaths;
    }

    public static void findAllTreePathsRecursive(TreeNode root, List<List<Integer>> allPaths, List<Integer> currentPath, int sum) {
        if (root == null)
            return;

        currentPath.add(root.val);
        sum -= root.val;
        if (root.left == null && root.right == null && sum == 0)
            allPaths.add(new ArrayList<>(currentPath));

        findAllTreePathsRecursive(root.left, allPaths, currentPath, sum);
        findAllTreePathsRecursive(root.right, allPaths, currentPath, sum);

        currentPath.remove(currentPath.size()-1);
    }

    /**
     * Given a binary tree where each node can only have a digit (0-9) value, each root-to-leaf path will represent a number.
     * Find the total sum of all the numbers represented by all paths.
     */
    public static int findSumOfPathNumbers(TreeNode root) {
        return findSumOfPathNumbersHelper(root, 0);
    }

    public static int findSumOfPathNumbersHelper(TreeNode root, int sum) {
        if (root == null) return 0;
        sum = sum * 10 + root.val;
        if (root.right == null && root.left == null) return sum;
        return findSumOfPathNumbersHelper(root.right, sum) + findSumOfPathNumbersHelper(root.left, sum);
    }

    public static boolean pathWithGivenSequence(TreeNode root, int[] sequence) {
        return pathWithGivenSequence(root, sequence, 0);
    }

    public static boolean pathWithGivenSequence(TreeNode root, int[] sequence, int level) {
        if (root == null)
            return false;

        if (level > sequence.length || sequence[level] != root.val)
            return false;

        if (root.left == null && root.right == null && sequence.length-1 == level)
            return true;

        return pathWithGivenSequence(root.left, sequence, level+1) || pathWithGivenSequence(root.right, sequence, level+1);
    }

    /**
     * Given a binary tree and a number ‘S’, find all paths in the tree such that the sum of all the node values of each path equals ‘S’.
     * Please note that the paths can start or end at any node but all paths must follow direction from parent to child (top to bottom).
     */
    public static int countAllPathSum(TreeNode root, int S) {
        return countAllPathSumHelper(root, S, new ArrayList<>());
    }

    public static int countAllPathSumHelper(TreeNode root, int targetSum, List<Integer> path) {
        if (root == null)
            return 0;

        path.add(root.val);

        int currentSum = 0;
        int counter = 0;

        for (int i = 0; i < path.size(); i++) {
            currentSum += path.get(path.size()-1 - i);
            if (currentSum == targetSum)
                counter++;
        }

        counter += countAllPathSumHelper(root.left, targetSum, path);
        counter += countAllPathSumHelper(root.right, targetSum, path);

        path.remove(path.size()-1);

        return counter;
    }

    public static int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, 1);
        return pathSumHelper(root, sum, 0, m);
    }

    public static int pathSumHelper(TreeNode root, int targetSum, int currentSum, Map<Integer, Integer> m) {
        if (root == null)
            return 0;

        currentSum += root.val;

        int count = m.getOrDefault(currentSum - targetSum, 0);

        m.put(currentSum, m.getOrDefault(currentSum, 0) + 1);

        count += pathSumHelper(root.left, targetSum, currentSum, m);
        count += pathSumHelper(root.right, targetSum, currentSum, m);

        return count;
    }


    /**
     * Given a binary tree, find the length of its diameter.
     * The diameter of a tree is the number of nodes on the longest path between any two leaf nodes.
     * The diameter of a tree may or may not pass through the root.
     */
    public static int findDiameter(TreeNode root) {
        treeDiameter = 0;
        findDiameterHelper(root);
        return treeDiameter;
    }

    private static int treeDiameter = 0;

    public static int findDiameterHelper(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = findDiameterHelper(root.left);
        int rightHeight = findDiameterHelper(root.right);

        treeDiameter = Math.max(leftHeight + rightHeight + 1, treeDiameter);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Find the path with the maximum sum in a given binary tree. Write a function that returns the maximum sum.
     * A path can be defined as a sequence of nodes between any two nodes and doesn’t necessarily pass through the root.
     */
    public static int findMaximumPathSum(TreeNode root) {
        maxPathSum = Integer.MIN_VALUE;
        findMaximumPathSumHelper(root);
        return maxPathSum;
    }

    private static int maxPathSum = Integer.MIN_VALUE;

    public static int findMaximumPathSumHelper(TreeNode root) {
        if (root == null)
            return 0;

        int l = findMaximumPathSumHelper(root.left);
        int r = findMaximumPathSumHelper(root.right);

        maxPathSum = Math.max(maxPathSum, l + r + root.val);

        return Math.max(Math.max(l, r) + root.val, 0);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println("Maximum Path Sum: " + findMaximumPathSum(root));

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        System.out.println("Maximum Path Sum: " + findMaximumPathSum(root));

        root = new TreeNode(-1);
        root.left = new TreeNode(-3);
        System.out.println("Maximum Path Sum: " + findMaximumPathSum(root));
    }

}
