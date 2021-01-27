package interview;

public class AlgoSessions {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int v) {
            val = v;
        }
    }

    public String serizlizeTree(TreeNode node) {
        StringBuilder sb = new StringBuilder();
        serializeDFS(node, sb);
        return sb.toString();
    }

    public void serializeDFS(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("n");
            return;
        }

        sb.append(node.val);
        serializeDFS(node.left, sb);
        serializeDFS(node.right, sb);
    }

    public TreeNode deserialize(String s) {
        return deserializeDFS(s, new int[]{0});
    }

    public TreeNode deserializeDFS(String s, int[] i) {
        if (i[0] >= s.length() || s.charAt(i[0]) == 'n') {
            return null;
        }

        TreeNode n = new TreeNode(s.charAt(i[0]) - '0');
        i[0]++;
        n.left = deserializeDFS(s, i);
        i[0]++;
        n.right = deserializeDFS(s, i);
        return n;
    }

    public static void main(String[] args) {
        AlgoSessions a = new AlgoSessions();
        TreeNode n = new TreeNode(1);
        n.left = new TreeNode(2);
        n.right = new TreeNode(3);
        n.right.left = new TreeNode(4);
        n.right.right = new TreeNode(5);

        String s = a.serizlizeTree(n);
        System.out.println(s);
        TreeNode deserializedTree = a.deserialize(s);
        System.out.println(deserializedTree);
    }

}
