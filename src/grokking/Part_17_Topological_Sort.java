package grokking;

import java.util.*;

public class Part_17_Topological_Sort {

    public static List<Integer> topologicalSort(int vertices, int[][] edges) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            List<Integer> vs = adjList.getOrDefault(edges[i][0], new ArrayList<>());
            vs.add(edges[i][1]);
            adjList.put(edges[i][0], vs);

        }

        boolean[] visited = new boolean[vertices];
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < vertices; i++) {
            if (!visited[i])
                dfs(adjList, i, s, visited);
        }

        List<Integer> result = new ArrayList<>(s.size());
        while (!s.isEmpty())
            result.add(s.pop());

        return result;
    }

    public static void dfs(Map<Integer, List<Integer>> adjList, int v, Stack<Integer> s, boolean[] visited) {
        visited[v] = true;

        List<Integer> vTo = adjList.get(v);

        if (vTo != null) {
            for (int vs : vTo) {
                if (!visited[vs])
                    dfs(adjList, vs, s, visited);
            }
        }

        s.add(v);
    }

    public static boolean isSchedulingPossible(int tasks, int[][] prerequisites) {
        // Create adjacency list

        // Prepare data structure
        List<Integer>[] l = new List[tasks];
        for (int i = 0; i < l.length; i++)
            l[i] = new ArrayList<>();

        int[] c = new int[tasks];
        // Fill data structure
        for (int[] p : prerequisites) {
            l[p[0]].add(p[1]);
            c[p[1]]++;
        }

        boolean[] visited = new boolean[tasks];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 0)
                q.add(i);
        }

        List<Integer> order = new ArrayList<>();
        while (!q.isEmpty()) {
            Integer v = q.poll();
            if (!visited[v]) {
                visited[v] = true;
                order.add(v);
                for (Integer vx : l[v]) {
                    q.add(vx);
                }
            }
        }

        return tasks == order.size();
    }

    public static List<Integer> topoSortBFS(int vertices, int[][] edges) {
        List<Integer>[] a = new List[vertices];

        for (int i = 0; i < a.length; i++) {
            a[i] = new ArrayList<>();
        }

        int[] inCounter = new int[vertices];
        for (int[] e : edges) {
            inCounter[e[1]]++;
            a[e[0]].add(e[1]);
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < inCounter.length; i++) {
            if (inCounter[i] == 0) q.add(i);
        }

        List<Integer> topo = new ArrayList<>(vertices);
        while (!q.isEmpty()) {
            int v = q.poll();
            topo.add(v);
            for (int w : a[v]) {
                inCounter[w]--;
                if (inCounter[w] == 0)
                    q.add(w);
            }
        }

        return topo;
    }

    /**
     * There are ‘N’ tasks, labeled from ‘0’ to ‘N-1’.
     * Each task can have some prerequisite tasks which need to be completed before it can be scheduled.
     * Given the number of tasks and a list of prerequisite pairs, write a method to find the ordering of tasks we should pick to finish all tasks.
     */
    public static List<Integer> findOrder(int tasks, int[][] prerequisites) {
        List<Integer> sortedOrder = new ArrayList<>();

        List<Integer>[] adj = new ArrayList[tasks];
        for (int i = 0; i < adj.length; i++)
            adj[i] = new ArrayList<>();

        int[] freq = new int[tasks];
        for (int[] p : prerequisites) {
            adj[p[0]].add(p[1]);
            freq[p[1]]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] == 0)
                q.add(i);
        }

        while (!q.isEmpty()) {
            int v = q.poll();
            sortedOrder.add(v);
            for (int w : adj[v]) {
                if (--freq[w] == 0)
                    q.add(w);
            }
        }

        return sortedOrder;
    }

    /**
     * There are ‘N’ tasks, labeled from ‘0’ to ‘N-1’.
     * Each task can have some prerequisite tasks which need to be completed before it can be scheduled.
     * Given the number of tasks and a list of prerequisite pairs,
     * write a method to print all possible ordering of tasks meeting all prerequisites.
     */
    public static void printOrders(int tasks, int[][] prerequisites) {
        List<Integer>[] adj = new ArrayList[tasks];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }

        int[] inDegree = new int[tasks];
        for (int[] p : prerequisites) {
            adj[p[0]].add(p[1]);
            inDegree[p[1]]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0)
                q.add(i);
        }

        List<Integer> sortedOrder = new ArrayList<>();
        printOrdersRec(adj, inDegree, q, sortedOrder);
    }

    public static void printOrdersRec(List<Integer>[] adj, int[] inDegree, Queue<Integer> q, List<Integer> sortedOrder) {
        if (!q.isEmpty()) {
            for (Integer v : q) {
                sortedOrder.add(v);
                Queue<Integer> qc = cloneQ(q);
                qc.remove(v);

                for (int w : adj[v]) {
                    inDegree[w]--;
                    if (inDegree[w] == 0)
                        qc.add(w);
                }

                printOrdersRec(adj, inDegree, qc, sortedOrder);

                sortedOrder.remove(v);

                for (int w : adj[v])
                    inDegree[w]++;
            }
        }

        if (sortedOrder.size() == inDegree.length)
            System.out.println(sortedOrder);
    }

    public static Queue<Integer> cloneQ(Queue<Integer> q) {
        Queue<Integer> qc = new LinkedList<>();
        for (Integer v : q)
            qc.add(v);
        return qc;
    }

    /**
     * Alien Dictionary
     * There is a dictionary containing words from an alien language for which we don’t know the ordering of the alphabets.
     * Write a method to find the correct order of the alphabets in the alien language.
     * It is given that the input is a valid dictionary and there exists an ordering among its alphabets.
     */
    public static String findOrder(String[] words) {
        List<Integer>[] adj = new List[26];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }

        int[] inDegree = new int[26];
        for (int i = 0; i < inDegree.length; i++) {
            inDegree[i] = -1;
        }

        for (String w : words) {
            for (int i = 0; i < w.length(); i++) {
                inDegree[w.charAt(i) - 'a'] = 0;
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];

            int minLen = Math.min(w1.length(), w2.length());

            for (int j = 0; j < minLen; j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    int c1 = w1.charAt(j) - 'a';
                    int c2 = w2.charAt(j) - 'a';
                    adj[c1].add(c2);
                    inDegree[c2]++;
                    break;
                }
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0)
                q.add(i);
        }

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int c = q.poll();
            sb.append((char)(c + 'a'));
            for (int ch : adj[c]) {
                inDegree[ch]--;
                if (inDegree[ch] == 0)
                    q.add(ch);
            }
        }

        return sb.toString();
    }

    /**
     * Given a sequence originalSeq and an array of sequences, write a method to find if originalSeq can be uniquely reconstructed from the array of sequences.
     * Unique reconstruction means that we need to find if originalSeq is the only sequence such that all sequences in the array are subsequences of it.
     */
    public static boolean canConstruct(int[] originalSeq, int[][] sequences) {
        List<Integer>[] adj = new ArrayList[10];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }

        int[] inDegree = new int[10];

        for (int[] seq : sequences) {
            for (int i = 1; i < seq.length; i++) {
                adj[seq[i-1]].add(seq[i]);
                inDegree[seq[i]]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (adj[i].size() > 0 && inDegree[i] == 0) {
                q.add(i);
            }
        }

        int count = 0;
        while (!q.isEmpty()) {
            if (q.size() > 1)
                return false;

            int v = q.poll();
            if (count >= originalSeq.length || originalSeq[count] != v)
                return false;

            for (int w : adj[v]) {
                if (--inDegree[w] == 0)
                    q.add(w);
            }
            count++;
        }

        return count == originalSeq.length;
    }

    /**
     * Minimum Height Trees
     *
     * We are given an undirected graph that has characteristics of a k-ary tree.
     * In such a graph, we can choose any node as the root to make a k-ary tree.
     * The root (or the tree) with the minimum height will be called Minimum Height Tree (MHT).
     * There can be multiple MHTs for a graph. In this problem, we need to find all those roots which give us MHTs.
     * Write a method to find all MHTs of the given graph and return a list of their roots.
     *
     * Time complexity: O(V*(V+E))
     * Space complexity: O(V + E)
     */
    public static List<Integer> findTrees(int nodes, int[][] edges) {
        List<Integer> minHeightTrees = new ArrayList<>();

        List<Integer>[] adj = new ArrayList[nodes];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        int minHeight = nodes;

        for (int i = 0; i < nodes; i++) {
            boolean[] visited = new boolean[nodes];

            Queue<Integer> q = new LinkedList<>();
            q.add(i);
            int h = 0;

            while (!q.isEmpty()) {
                int n = q.size();
                for (int j = 0; j < n; j++) {
                    int v = q.poll();
                    visited[v] = true;

                    for (int w : adj[v]) {
                        if (!visited[w]) {
                            q.add(w);
                        }
                    }
                }
                h++;
            }

            if (h < minHeight) {
                minHeightTrees.clear();
                minHeight = h;
            }

            if (h == minHeight)
                minHeightTrees.add(i);
        }

        return minHeightTrees;
    }

    /**
     * Time complexity O(V+E)
     */
    public static List<Integer> findTrees2(int nodes, int[][] edges) {
        List<Integer>[] adj = new ArrayList[nodes];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }

        int[] inDegree = new int[nodes];

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
            inDegree[edge[0]]++;
            inDegree[edge[1]]++;
        }

        Queue<Integer> leaves = new LinkedList<>();
        for (int i = 0; i < nodes; i++) {
            if (inDegree[i] == 1)
                leaves.add(i);
        }
        int totalNodes = nodes;

        while (totalNodes > 2) {
            int leavesSize = leaves.size();
            for (int i = 0; i < leavesSize; i++) {
                int v = leaves.poll();
                inDegree[v] = 0;
                for (Integer w : adj[v]) {
                    inDegree[w]--;
                    if (inDegree[w] == 1)
                        leaves.add(w);
                }
            }
            totalNodes -= leavesSize;
        }

        return new ArrayList<>(leaves);
    }

    public static void main(String[] args) {
        List<Integer> result = findTrees2(5,
                new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 1, 3 }, new int[] { 2, 4 } });
        System.out.println("Roots of MHTs: " + result);

        result = findTrees2(4,
                new int[][] { new int[] { 0, 1 }, new int[] { 0, 2 }, new int[] { 2, 3 } });
        System.out.println("Roots of MHTs: " + result);

        result = findTrees2(4,
                new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 1, 3 } });
        System.out.println("Roots of MHTs: " + result);
    }

}
