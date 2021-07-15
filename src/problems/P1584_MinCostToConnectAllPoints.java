package problems;

public class P1584_MinCostToConnectAllPoints {
    public class IndexMinPQ<Key extends Comparable<Key>> {
        private int maxN;        // maximum number of elements on PQ
        private int n;           // number of elements on PQ
        private int[] pq;        // binary heap using 1-based indexing
        private int[] qp;        // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
        private Key[] keys;      // keys[i] = priority of i

        public IndexMinPQ(int maxN) {
            this.maxN = maxN;
            n = 0;
            keys = (Key[]) new Comparable[maxN + 1];    // make this of length maxN??
            pq   = new int[maxN + 1];
            qp   = new int[maxN + 1];                   // make this of length maxN??
            for (int i = 0; i <= maxN; i++)
                qp[i] = -1;
        }

        public boolean isEmpty() {
            return n == 0;
        }

        public boolean contains(int i) {
            return qp[i] != -1;
        }

        public int size() {
            return n;
        }

        public void add(int i, Key key) {
            n++;
            qp[i] = n;
            pq[n] = i;
            keys[i] = key;
            swim(n);
        }

        public int peek() {
            return pq[1];
        }

        public Key peekKey() {
            return keys[pq[1]];
        }

        public int poll() {
            int min = pq[1];
            exch(1, n--);
            sink(1);

            qp[min] = -1;        // delete
            keys[min] = null;    // to help with garbage collection
            pq[n+1] = -1;        // not needed
            return min;
        }

        public void decreaseKey(int i, Key key) {
            keys[i] = key;
            swim(qp[i]);
        }

        private boolean greater(int i, int j) {
            return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
        }

        private void exch(int i, int j) {
            int swap = pq[i];
            pq[i] = pq[j];
            pq[j] = swap;
            qp[pq[i]] = i;
            qp[pq[j]] = j;
        }


        private void swim(int k) {
            while (k > 1 && greater(k/2, k)) {
                exch(k, k/2);
                k = k/2;
            }
        }

        private void sink(int k) {
            while (2*k <= n) {
                int j = 2*k;
                if (j < n && greater(j, j+1)) j++;
                if (!greater(k, j)) break;
                exch(k, j);
                k = j;
            }
        }
    }

    public int minCostConnectPoints(int[][] points) {
        int res = 0;
        int n = points.length;

        int[] dist = new int[n];
        for (int i = 0; i < dist.length; i++)
            dist[i] = Integer.MAX_VALUE;

        dist[0] = 0;
        boolean[] visited = new boolean[n];
        IndexMinPQ<Integer> pq = new IndexMinPQ<>(n);
        pq.add(0, 0);

        while (!pq.isEmpty()) {
            int v = pq.poll();
            visited[v] = true;

            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    int distance = Math.abs(points[v][0] - points[i][0]) + Math.abs(points[v][1] - points[i][1]);

                    if (distance < dist[i]) {
                        dist[i] = distance;
                        if (pq.contains(i))
                            pq.decreaseKey(i, distance);
                        else
                            pq.add(i, distance);
                    }
                }
            }
        }

        for (int d : dist)
            res += d;

        return res;
    }

}
