package problems;

public class SlidingWindowMedian_480 {


    public static class BinaryTree<K extends Comparable<K>, V> {

        public class Node {
            private K key;
            private V value;

            private Node left;
            private Node right;

            Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private Node root;

        public BinaryTree() {
        }

        public BinaryTree(K key, V value) {
            Node node = new Node(key, value);
            root = node;
        }

        public BinaryTree(Node node) {
            this.root = node;
        }

        public void add(K key, V value) {
            root = add(root, key, value);
        }

        public Node add(Node node, K key, V value) {
            if (node == null) return new Node(key, value);
            if (key.compareTo(node.key) >= 0) {
                node.right = add(node.right, key, value);
            } else {
                node.left = add(node.left, key, value);
            }
            return node;
        }

//        private void swap(Node a, Node b) {
//            K tmp = a.;
//            a.e = b.e;
//            b.e = tmp;
//        }


    }


    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];



        return result;
    }

}
