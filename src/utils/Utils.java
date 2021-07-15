package utils;

public class Utils {

    public static TreeNode createTree(int[] data) {
        return createTreeHelper(data, 0);
    }

    public static TreeNode createTreeHelper(int[] data, int index) {
        if (index > data.length - 1)
            return null;

        TreeNode currentNode = new TreeNode(data[index - 1]);
        currentNode.left = createTreeHelper(data, index * 2);
        currentNode.right = createTreeHelper(data, index * 2 + 1);

        return currentNode;
    }
}
