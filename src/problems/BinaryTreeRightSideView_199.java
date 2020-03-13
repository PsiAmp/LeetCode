package problems;

import java.util.*;

public class BinaryTreeRightSideView_199 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList();
        if (root == null) return result;
        result.add(root.val);

        Deque<TreeNode> d = new LinkedList();
        if (root.right != null) {
            d.push(root.right);
        }

        while(!d.isEmpty()) {
            TreeNode t = d.poll();
            result.add(t.val);
            if (t.left != null) d.add(t.left);
            if (t.right != null) d.add(t.right);
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(99);
        root.right = new TreeNode(55);

        root.right.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right.right = new TreeNode(44);
        root.right.left.left = new TreeNode(33);
        root.right.left.left.right = new TreeNode(333);

        BinaryTreeRightSideView_199 b = new BinaryTreeRightSideView_199();
        List<Integer> integers = b.rightSideView(root);

        for (int i = 0; i < integers.size(); i++) {
            System.out.println(integers.get(i));
        }
    }

}
