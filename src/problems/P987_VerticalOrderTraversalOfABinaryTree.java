package problems;

import utils.TreeNode;

import java.util.*;

public class P987_VerticalOrderTraversalOfABinaryTree {

    class NodeWithPosition {
        TreeNode node;
        int x;

        public NodeWithPosition(TreeNode node, int x) {
            this.node = node;
            this.x = x;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null)
            return result;

        // DS
        Map<Integer, List<Integer>> m = new HashMap<>();

        // BFS
        Queue<NodeWithPosition> q = new PriorityQueue<>((a, b) -> a.node.val - b.node.val);
        q.add(new NodeWithPosition(root, 0));

        int minX = 0;

        while (!q.isEmpty()) {
            Queue<NodeWithPosition> tempQ = new PriorityQueue<>((a, b) -> a.node.val - b.node.val);

            while (!q.isEmpty()) {
                NodeWithPosition v = q.poll();
                int x = v.x;
                minX = Math.min(x, minX);

                List<Integer> list = m.getOrDefault(x, new ArrayList<>());
                list.add(v.node.val);
                m.put(x, list);

                if (v.node.left != null)
                    tempQ.add(new NodeWithPosition(v.node.left, x - 1));

                if (v.node.right != null)
                    tempQ.add(new NodeWithPosition(v.node.right, x + 1));
            }

            q = tempQ;
        }

        // DS to result
        int x = minX;
        while (m.containsKey(x)) {
            result.add(m.get(x));
            x++;
        }

        return result;
    }

}
