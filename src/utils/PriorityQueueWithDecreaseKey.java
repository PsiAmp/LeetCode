package utils;

public class PriorityQueueWithDecreaseKey<Key extends Comparable<Key>> {

//    private static final int initialCapacity = 16;

    private int size;
    private Key[] pq;
    private int[] qp;

//    public PriorityQueueWithDecreaseKey() {
//        this(initialCapacity);
//    }

    public PriorityQueueWithDecreaseKey(int maxCapacity) {
        size = 0;
        pq = (Key[]) new Comparable[maxCapacity];
        qp = new int[maxCapacity];
        for (int i = 0; i < qp.length; i++) {
            qp[i] = -1;
        }
    }

    public void add(int i, Key v) {
        // Increase size
        size++;

//        if (pq.length < size) {
//            int newCapacity = pq.length << 1;
//            Key[] n = (Key[]) new Comparable[newCapacity];
//            System.arraycopy(pq, 0, n, 0, pq.length);
//            pq = n;
//        }

        pq[size - 1] = v;
        qp[i] = size - 1;
        swim(size - 1);
    }

    public int poll() {
        Key node = pq[0];
        int i = qp[1];

        swap(0, size - 1);

        size--;
        sink(0);

        pq[size] = null;

        return i;
    }

    public int peek() {
        return qp[0];
    }

    public void decreaseKey(int i, Key val) {
        pq[i - 1] = val;
        swim(i);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(int key) {
        return qp[key] != -1;
    }

    public int getSize() {
        return size;
    }

    private void sink(int i) {
        int child = (i + 1) * 2 - 1;

        if (child >= size)
            return;

        if (child + 1 < size && pq[child].compareTo(pq[child + 1]) > 0)
            child++;

        if (pq[i].compareTo(pq[child]) > 0) {
            swap(i, child);
            sink(child);
        }
    }

    private void swim(int i) {
        if (i == 0)
            return;

        int parent = (i - 1) / 2;

        if (pq[parent].compareTo(pq[i]) > 0) {
            swap(i, parent);
            swim(parent);
        }
    }

    private void swap(int a, int b) {
        Key tmp = pq[a];
        pq[a] = pq[b];
        pq[b] = tmp;

        int tmpi = qp[a + 1];
        qp[a + 1] = qp[b + 1];
        qp[b + 1] = tmpi;
    }

    public static void main(String[] args) {
        PriorityQueueWithDecreaseKey<Integer> q = new PriorityQueueWithDecreaseKey<>(10);
        q.add(1,59);
        q.add(2, 1);
        q.add(3, 55);
        q.add(4, 3);
        q.add(5, 99);
        q.add(6, 190);
        q.add(7, 1900);
        q.add(9, -4);

        q.decreaseKey(5, 11);
        q.decreaseKey(6, 4);

        while (!q.isEmpty())
            System.out.println(q.poll());
    }

}
