package problems;

public class SumOfNodesWithEvenGrandparent_1315 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int sumEvenGrandparent(TreeNode root) {
        return jumpOverAndGetSum(root, false, false);
    }

    public int jumpOverAndGetSum(TreeNode root, boolean sumNextLevel, boolean sumNextNextLevel) {
        int sum = 0;
        if (root != null) {
            sum += jumpOverAndGetSum(root.left, sumNextNextLevel, root.val % 2 == 0);
            sum += jumpOverAndGetSum(root.right, sumNextNextLevel, root.val % 2 == 0);

            if (sumNextLevel)
                sum += root.val;
        }
        return sum;
    }

}
